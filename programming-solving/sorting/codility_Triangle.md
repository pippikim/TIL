# [JAVA/Codility] Triangle

## 문제

N 개의 정수로 구성된 배열 A가 제공됩니다. 삼중 선 (P, Q, R)은 0 ≤ P <Q <R <N이고 다음과 같은 경우 삼각형입니다.

- A[P] + A[Q] > A[R],
- A[Q] + A[R] > A[P],
- A[R] + A[P] > A[Q].

예를 들어, 다음과 같은 배열 A를 고려하십시오.

A[0] = 10    A[1] = 2    A[2] = 5  A[3] = 1     A[4] = 8    A[5] = 20

삼중 선 (0, 2, 4)은 삼각형입니다.

N 개의 정수로 구성된 배열 A가 주어지면이 배열에 대한 삼각 삼중 항이 있으면 1을 반환하고 그렇지 않으면 0을 반환합니다.

A[0] = 10    A[1] = 2    A[2] = 5  A[3] = 1     A[4] = 8    A[5] = 20

함수는 위에서 설명한대로 1을 반환해야합니다. 주어진 배열 A는 다음과 같습니다.

A[0] = 10    A[1] = 50    A[2] = 5  A[3] = 1

함수는 0을 반환해야합니다.

다음 가정에 대한 효율적인 알고리즘을 작성하십시오.

- N은 [0..100,000] 범위 내의 정수이고; 

- 배열 A의 각 요소는 [-2,147,483,648..2,147,483,647] 범위 내의 정수입니다.



## 문제 접근

### 아이디어

- A[R] >= A[Q] >= A[P] 일 때, A[R] < A[P] + A[Q] 라면 삼각형이 될 수 있다. 
- 최대힙을 만들어서 위의 가정을 만족시키면 1을 리턴한다. 

### 입력값 주의

- 배열 A는 -2,147,483,648 ~ 2,147,483,648 범위내에 있어서 두 수의 합을 구할 때, long형으로 바꿔서 계산한다.
- 만약 가장 큰 수가 음수(r<0)이면 삼각형이 될 수 없기 때문에(마이너스는 더할 수록 수가 작아진다) 무조건 0을 리턴한다.
  - [이 부분 때문에 틀린 소스](###81%) 
  - [이 부분을 고쳐서 맞은 소스](###100%)



## 소스

### 81%

- [결과](https://app.codility.com/demo/results/trainingFCH74G-4JM/)

```java
import java.util.*;

class Solution {
    public int solution(int[] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int a : A){
            pq.add(a);
        }//for end

        while (!pq.isEmpty()&&pq.size()>2){
            int r = pq.poll();
            int q = pq.poll();
            int p = pq.poll();

            if(((long)p+(long)q)>r) return 1;
            else{
                pq.add(q);
                pq.add(p);
            }

        }//while end

        return 0;
    }
}
```



### 100%

- [결과](https://app.codility.com/demo/results/trainingNUCD9Y-YNR/)

```java
import java.util.*;

class Solution {
    public int solution(int[] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int a : A){
            pq.add(a);
        }//for end

        while (!pq.isEmpty()&&pq.size()>2){
            int r = pq.poll();
            int q = pq.poll();
            int p = pq.poll();
            if(r<0) return 0;
            else if(((long)p+(long)q)>r) return 1;
            else{
                pq.add(q);
                pq.add(p);
            }

        }//while end

        return 0;
    }
}
```

