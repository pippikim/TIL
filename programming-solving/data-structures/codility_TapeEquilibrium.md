# [JAVA/Codility] TapeEquilibrium

## 문제

- int A[N] : 테이프의 숫자를 나타냄
  - 2<=N<=100,000
  - -1000<=A[i]<=1000
- 정수형 변수 P: A배열을 2개의 파트로 나누는 구획 
  - 0<P<N

- 절대값[(A[0]+...+A[P-1])-(A[P]+...+A[N-1])] <= 최소값을 구하는 문제



## 문제접근

시간효율을 고려해야하는 문제이다.

절대값{(A[0]+...+A[i]) - [(A배열의 원소들을 다 더한 값) - (A[0]+...+A[i])]}을 계산하되, 연산한 값들 중 최소값만 저장하는 방식으로 풀었다.



## 소스

```java
class Solution {
    public int solution(int[] A) {
        int total = 0;
        int sum = 0;
        int answer = Integer.MAX_VALUE;
        for(int a : A){
            total+=a;
        }//for end
        
        for(int i=0; i<A.length-1; i++){
            sum+=A[i];
            answer = Math.min(answer, Math.abs(sum-(total-sum)));
        }        
        return answer;
    }
}
```

- Detected time complexity: **O(N)**

[결과링크](https://app.codility.com/demo/results/trainingUA3SU9-HC8/)

