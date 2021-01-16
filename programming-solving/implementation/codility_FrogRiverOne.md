# [JAVA/Codility] FrogRiverOne

[FrogRiverOne](https://app.codility.com/demo/results/training3FE6XM-NE8/#)



## 문제 접근

- 문제 이해를 잘못해서 처음에 잘못 풀었음 
- hash set을 이용

## 소스

```java
import java.util.*;
class Solution {
    
    public int solution(int X, int[] A) {
        int minTime = -1;
        Set<Integer> set = new HashSet<>();

        for(int i=0; i<A.length; i++){
            set.add(A[i]);
            if(set.size()==X){
                minTime = i;
                break;
            }
        }

        return minTime;
    }
}
```

