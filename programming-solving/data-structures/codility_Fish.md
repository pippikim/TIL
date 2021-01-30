# [JAVA/Codility] Fish



## 소스

- [결과](https://app.codility.com/demo/results/training8SQ5WJ-DHC/)

```java
import java.util.*;
class Solution {
    class MovingFish{
        int dir;
        int size;
        public MovingFish(int size, int dir){
            this.size = size;
            this.dir = dir;
        }
    }
    public int solution(int[] A, int[] B) {
        Queue<MovingFish> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        for(int i=0; i<A.length; i++){
            queue.add(new MovingFish(A[i],B[i]));
        }

        while (!queue.isEmpty()){
            MovingFish fish = queue.peek();
            if(fish.dir==1) stack.add(fish.size);
            else if(!stack.isEmpty()){
                if(stack.peek()<fish.size){
                    stack.pop();
                    continue;
                }
            }else cnt++;
            queue.poll();
        }
        return cnt+queue.size()+stack.size();
    }
}
```

