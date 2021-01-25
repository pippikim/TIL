# [JAVA/Codility] Dominator

- 배열 A의 지배자는 A 요소의 절반 이상에서 발생하는 값
- N 개의 정수로 구성된 배열 A가 주어지면 A의 지배자가 발생하는 배열 A의 모든 요소의 인덱스를 반환
- 배열 A에 지배자가 없으면 함수는 −1을 반환



## 문제 접근

1. 스택에 배열 A요소를 하나씩 넣는다.
2. 스택의 사이즈가 2이상이면 2개씩 pop
3. 2번의 두 값이 같은 지 비교
4. 3번 결과가 true이면 두 값을 다시 add
5. A배열의 마지막 원소까지(즉, N번까지) 1~4를 반복
6. 스택의 사이즈가 1보다 크면 해당 번호를 리더 후보로 둔다 
7. A배열에서 6번에 정한 후보와 같은 원소를 찾는다
8. 7번에서 찾으면 개수를 세고 해당 인덱스를 저장한다
9. A배열이 끝날 때까지 7~8번을 반복한다
10. 8번의 개수가 n/2+1 이상이면 인덱스를 리턴한다
11. 10번이 false이면 -1을 리턴한다



## 소스

- [결과](https://app.codility.com/demo/results/trainingWKXN38-56B/)
- 처음에 100%가 안나왔는데([91% 결과](https://app.codility.com/demo/results/trainingD9BDBS-PYA/)) 설명을 제대로 이해하지 못해서... 틀렸다
- 리더 조건을 A배열 요소들의 절반 이상이란 말이 과반수(즉 절반 보다 많아야함)...였고 횟수가 절반이면 리더가 될 수 없다...
  - (아니 리더 조건이 A배열의 절반 이상에서 발생하는 값이라고 적혀었는데?) 

```java
import java.util.*;
class Solution {
    public int getCandidate(int[] A){
        Stack<Integer> stack = new Stack<>();
        int candidate = -1;

        for(int a : A){
            stack.add(a);
            if(stack.size()>1){
                int v1 = stack.pop();
                int v2 = stack.pop();
                if(v1==v2){
                    stack.add(v1);
                    stack.add(v2);
                }//if end
            }
        }//for end

        if(stack.size()>0)  candidate = stack.peek();

        return candidate;
    }
    public int solution(int[] A) {
        int candidate = getCandidate(A);
        int cnt = 0;
        double leaderNum = A.length/2.0;
        int idx = -1;

        for(int i = 0; i<A.length; i++){
            if(A[i]==candidate){
                cnt++;
                idx = i;
            }//if end
        }//for end

        if(cnt > leaderNum){
            return idx;
        }else {
            return -1;
        }
    }
}

```

