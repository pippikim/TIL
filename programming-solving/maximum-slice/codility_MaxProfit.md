# [JAVA/Codility] MaxProfit

## 문제

- A[N]: 주식의 일일 가격을 표시
- 단일 주식이 P일에 매수, Q일에 매각(0<=P<=Q<N)
- 거래 이익 : A[Q]-A[P] (A[Q]>=A[P])
- 손실 : A[P]-A[Q]
- OUTPUT : 가능한 최대 이익을 반환, 수익이 없다면 0을 반환
- INPUT
  - N (0<=N<=400,000)
  - A[N] (0<=A[i]<=200,000)



## 문제접근

- 정수형 변수 end를 가장 적게 매수할 수 있는 주식 가격이라고 하고
- 정수형 변수 maxProfit는 얻을 수 있는 가장 큰 이익이라고 한다면,
- A[i]-end이 0보다 크다면 수익이 났기 때문에 maxProfit과 비교해서 더 크다면 저장한다.
- A[i]-end이 0보다 작다면 가장 적게 매수할 수 있는 주식가격은 A[i]이기 때문에 end 변수에 저장한다.
- 시간복잡도는 O(N)이다. 



## 소스

- [결과](https://app.codility.com/demo/results/trainingNKE6Q4-DEY/)

```java
class Solution {
    public int solution(int[] A) {
        try{
        int maxProfit = 0;
        int end = A[0];

        for(int i=1; i<A.length; i++){
            if(A[i]-end<0)  end = A[i];
            else maxProfit = Math.max(maxProfit, A[i]-end);
        }
        
        return maxProfit;
        }catch(Exception e){
            return 0; //A안에 주식이 하나 있거나 아무것도 없어서 수익이 0일 때
        }
    }
}
```

