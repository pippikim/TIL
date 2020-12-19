

## WebSocket



## STOMP

- Streaming Text Oriented Messaging Protocol의 약자로 스트리밍 텍스트 기반의 메시징 프로토콜이다.

<img src="https://tva1.sinaimg.cn/large/0081Kckwgy1glnh4sg6mcj30s10b476c.jpg" alt="image-20201214174020213" style="zoom:67%;" /> 











## 실습

- [공식문서](https://spring.io/guides/gs/messaging-stomp-websocket/) 를 참고 했다. 

### Create a Resource Representation Class

- STOMP 메시지 시스템을 만든다
- 시스템 상호작용에 대해 고려하면서 프로세스를 시작하자 
- 이 서비스는 JSON 형태의 STOMP 메세지로 된 이름을 포함한다
- 만약 name이 Fred이면 아래와 같이 나타낼 수 있다

```json
{
    "name": "Fred"
}
```



### Create a Message-handling Controller

- GreetingController

```java
package com.example.messagingstompwebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws  Exception{
        Thread.sleep(1000);
        return new Greeting("Hello, "+ HtmlUtils.htmlEscape(message.getName())+"!");
    }
}
```

- @MessageMapping: 만약 메세지가 /hello 목적지로 보내지면 greeting() 메소드가 호출된다. 

- 메세지의 페이로드는 greeting() 메서드에 인자값으로 주어지는 HelloMessage 객체에 바인딩 된다. 

  > 페이로드(payload): 데이터들 중에 "관심이 있는" 데이터
  >
  >  ```json
  > >>> SEND
  > destination:/app/hello
  > content-length:15
  > 
  > {"name":"pepe"}
  >  ```
  >
  > 여기서 클라이언트가 관심을 가지는 페이로드는 "pepe"
  >
  > 나머지는 프로토콜 오버헤드

- 1초의 지연 뒤 greeting()메서드는 Greeting 객체를 만들어 리턴한다. 
- @SendTo: 리턴값은 모든 /topic/greetings 구독자들(subscribers)에게 브로드캐스팅 된다. 



### Configure Spring for STOMP messaging

- WebSocketConfig

```java
package com.example.messagingstompwebsocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

     @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //메세지 브로커 구성
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
}

```

- @Configuration : Spring configuration class를 나타낸다
- @EnableWebSocketMessageBroker: message broker에 의해 지원되는 WebSocket 메세지를 핸들링할 수 있게 한다
- configureMessageBroker(): message broker 환경을 설정하는 메소드 
  - enableSimpleBroker(): 간단한 메모리 기반의 메세지 브로커가 /topic으로 시작하는 목적지로 메세지를 클라이언트에게 돌려주는 메서드
  - setApplicationDestinationPrefixes(): /app 접두어는 **@MessageMapping** 어노테이션과 결합(/app/hello)되어 모든 메세지 매핑을 정의하는데 사용된다. 
    - 예를 들어 /app/hello는 GreetingController.greeting() 메서드가 처리되도록 매핑된 엔드포인트다. 
- registerStompEndpoints(): 
  - /gs-guide-websocket 엔드포인트를 등록하고 SockJS 예비 옵션을 활성화 시켜서 만약 WebSocket이 작동하지 않을 때, 대신 데이터를 이동시키게 한다. 
  - SockJS 클라이언트는 /gs-guide-websocket로 연결하려고 시도할 것이고 최상으로 작동되는 이동수단(websocket, xhr-streaming, xhr-polling, and so on)을 사용할 수 있다. 

