# [JAVA/Codility] PassingCars

## 소스

### 90%

- 리턴 타입을 제대로 몰라서 틀림
- 10억개 초과여야 -1을 리턴
  - 난 10억개 이상으로 착각했음ㅠ
- 더한 값이 양의 범위를 초과할 경우가 생기면 음수가 되기 때문에 처리해줘야 함
  - long형으로 넘기던가 절대값을 확인하던가...

- [결과](https://app.codility.com/demo/results/training2DS7T3-HKT/)

```java
class Solution {
    public int solution(int[] A) {
        int N = A.length;
        int[] sumArr = new int[N];
        int answer = 0;

        sumArr[0] = A[0];
        for (int i =1; i<N; i++)    sumArr[i] = sumArr[i-1]+A[i];
        for(int i=0; i<N; i++){
            if(A[i]==0&&i<N-1) answer+=(sumArr[N-1]-sumArr[i]);
        }

        return answer>=1000000000?-1:answer;
    }
}
```



### 100%

- [결과](https://app.codility.com/demo/results/trainingTR5BZV-824/)

```java
class Solution {
    public int solution(int[] A) {
        int N = A.length;
        int[] sumArr = new int[N];
        int answer = 0;

        sumArr[0] = A[0];
        for (int i =1; i<N; i++)    sumArr[i] = sumArr[i-1]+A[i];
        for(int i=0; i<N; i++){
            if(A[i]==0) answer+=(sumArr[N-1]-sumArr[i]);
        }

        return Math.abs(answer)>1000000000?-1:answer;
    }
}
```

