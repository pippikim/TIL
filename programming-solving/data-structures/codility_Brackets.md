# [JAVA/Codility] Brackets

- Stacks And Queues



## 문제접근

- stack에 '(', '{', '[' 등 여는 괄호를 넣는다 
- 닫는 괄호가 나오면 stack을 pop하고 해당 짝이 맞는 괄호인지 확인한다. 
  - 아스키코드를 이용하여 닫는 괄호-2 <= 여는 괄호 < 닫는 괄호 범위를 충족해야한다.
    - [틀린원인-75%](###75%)
  - 맞다면 계속 진행하고 아니라면 0을 리턴
- 만약 닫는 괄호가 나왔는데 스택이 비어있다면 0을 리턴한다.
  - [틀린원인-75%](###75%)
  - [틀린원인-85%](###85%)
- 마지막 문자까지 확인한 뒤 스택이 비어져 있다면 1을 리턴하고 그렇지 않다면 0을 리턴한다. 



## 소스

### 75%

- [결과](https://app.codility.com/demo/results/trainingYNRPY4-HW3/)

```java
import java.util.*;
class Solution {
    public int solution(String S) {
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<S.length(); i++){
            char c = S.charAt(i);
            if(c=='('||c=='['||c=='{')  stack.add(c);
            else if(!stack.isEmpty()) {
                char target = stack.pop();
                if(target>c)    return 0; //
            }
        }
        
        if(stack.isEmpty()) return 1;
        else return 0;
    }
}
```



### 87%

- [결과](https://app.codility.com/demo/results/training4QCWWT-QUV/)
- "()())"을 넣으면 1을 리턴해서 틀렸다. 

```java
import java.util.*;
class Solution {
    public int solution(String S) {
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<S.length(); i++){
            char c = S.charAt(i);
            if(c=='('||c=='['||c=='{')  stack.add(c);
            else if(!stack.isEmpty()) {
                char target = stack.pop();
                if(target>c||target<c-2)    return 0;
            }
        }
        if(stack.isEmpty()) return 1;
        else return 0;
    }
}
```



### 100%

- [결과](https://app.codility.com/demo/results/trainingJDUS9R-4S3/)

```java
import java.util.*;
class Solution {
    public int solution(String S) {
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<S.length(); i++){
            char c = S.charAt(i);
            if(c=='('||c=='['||c=='{')  stack.add(c);
            else if(!stack.isEmpty()) {
                char target = stack.pop();
                if(target>c||target<c-2)    return 0;
            }else return 0;
        }
        if(stack.isEmpty()) return 1;
        else return 0;
    }
}
```

