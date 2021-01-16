# [JAVA/Codility] CountDiv

## 문제

- K로 나눌 수 있는 [A...B] 범위 내의 정수 개수를 구한다.
  - { i : A ≤ i ≤ B, i **mod** K = 0 }
- 입력 데이터의 범위
  - A and B are integers within the range [0..2,000,000,000];
  - K is an integer within the range [1..2,000,000,000];
  - A ≤ B.



## 문제접근

- 입력 데이터들의 값 범위는 최대 200억으로 꼭 시간복잡도를 선형적으로 설계해서 풀어야한다.
  - 처음에 그냥 혹시나 하는 생각에 O(N)으로 풀었다가 시간초과가 생겼다([시간초과가 발생한 소스](###시간초과 소스))
- 만약 A = 6, B = 11, K = 2 이면 i는 6, 8, 10이고 개수는 3이다.
  - 즉, 2 * 3 = 6, 2 * 4 = 8, 2 * 5 = 10 때문에 시작점 3과 끝점 5를 뺀 뒤 1을 더해주면 원하는 값인 3이 나온다.  
  - (끝점-시작점)+1
- A값이 K로 나눠지면 그 몫을 시작점으로 잡고 만약 나눠지지 않으면 나눈 값에 1을 더한다. 
- A*K가 B보다 작거나 같은 경우에만 B와 K를 나눈 값을 끝점으로 잡고 만약 B보다 크다면 시작점에 1을 빼준다.
- 그런다음 시작점과 끝점 사이의 개수를 연산한다.



## 소스

### 시간초과 소스

```java
class Solution {
    public int solution(int A, int B, int K) {
        // write your code in Java SE 8
        int answer = 0;
        int step = 1;

        if(K==B)    return 1;
        
        while(true){
            long result = step*K;
            if(result>B)    break;
            else if(result>=A&&result<=B)   answer++;
            step++;
        }

        return answer;

    }
}
```



### 100% 소스

- [결과](https://app.codility.com/demo/results/training22ZPPK-6RW/)

```java
class Solution {
    public int solution(int A, int B, int K) {
        int left = A%K==0?A/K:(A/K)+1;
        int right = K*left<=B?B/K:left-1;

        return right-left+1;
    }
}
```

