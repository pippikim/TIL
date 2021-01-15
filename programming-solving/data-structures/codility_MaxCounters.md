# [JAVA/Codility] MaxCounters

## 문제

- N개의 카운터들이 0으로 초기화 되어있음
- 연산
  - x증가 : 카운터x는 1씩 증가 
  - 최대 카운터(*max counter*): 어떤 카운터에 의해 모든 카운터를 최대값으로 설정
- A[N]
  - A[K] = x (1<= x <= N) 일 때, x증가로 연산
  - A[K] = N+1 일 때, 최대 카운터(*max counter*)로 연산
- 1<=N<=100,000
- 1<=A[K]<=N+1



## 문제접근

### 초반 접근

처음에는 단순하게 생각해서 카운터들의 중복횟수를 배열에 담고, *max counter* 인 경우에 정수형 배열을 max값으로 리셋 시켰다.

하지만 이 문제에서 주어지는 배열의 크기는 최대 100,000이기 때문에 시간복잡도 O(n^2)로 풀게 되면 시간 초과가 발생하여, 정답률 77%를 받게 된다([틀린소스](#틀린 소스 (정답률 77%)))

### 다시 접근 

*max counter* 연산을 하게 되면 이제까지 카운트한 횟수 중 가장 큰 값으로 배열의 요소들이 리셋된다. 

즉, 배열 안의 횟수를 증가시킬 때, *max counter* 에 의해 설정된 max값이 기본값이 되는 것이다. 

초반 접근과 토대는 같지만, 차이점은  *max counter* 이 발생할 때마다 모든 카운터(1~N개)를 전부 바꾸지 않고 다음으로 오는 카운터들의 횟수만 비교해주는 것이다. 

```
anwser[N]이 카운터들의 횟수를 세는 배열일때, 
(anwser[A[K]-1] < max counter 에 의해 설정된 기본값) == true 이면, 
anwser[A[K]-1]의 원소를 max counter에 의해 설정된 기본값으로 변경하는 것이다. 
```

마지막으로 anwser 배열안을 점검해서 *max counter* 에 의해 설정된 기본값보다 작은 원소를 변경해준다.



## 소스

### 틀린 소스

- 시간초과 발생
- 정답률 77%

```java
import java.util.*;
class Solution {
    public int[] solution(int N, int[] A) {
        int[] anwser = new int[N];
        int max = 0;
        for(int a : A){
            if(a>N){
                Arrays.fill(anwser, max);
            }else{
               anwser[a-1]++;
               max = Math.max(max, anwser[a-1]);
            }
        }

        return anwser;
    }
}
```



### 맞은 소스

```java
import java.util.*;
class Solution {
    public int[] solution(int N, int[] A) {
        // write your code in Java SE 8
        int[] anwser = new int[N];
        int max = 0;
        int maxCounter = 0;
        for(int a : A){
            if(a>N){
                maxCounter = max;
            }else{
                if(anwser[a-1]<maxCounter)  anwser[a-1] = maxCounter; 
                anwser[a-1]++;
                max = Math.max(max, anwser[a-1]);
            }
        }

        for(int i=0; i<N; i++){
            if(anwser[i]<maxCounter)  anwser[i] = maxCounter; 
        }


        return anwser;
    }
}
```

- [결과링크](https://app.codility.com/demo/results/trainingWWC5YE-3TV/)

- Detected time complexity: **O(N + M)**

<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gmnq6te8r2j30u00seq6s.jpg" alt="image-20210115021403054" style="zoom:50%;" /> 