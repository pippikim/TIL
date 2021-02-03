# [JAVA/Codility] MaxProductOfThree



## 소스

- [결과](https://app.codility.com/demo/results/training6A5TMX-R95/)

```java
import java.util.*;
class Solution {
    public int solution(int[] A) {
        int n = A.length;
        int max = 0;
        Arrays.sort(A);//O(N * log(N))
        max = Math.max(A[n-1]*A[n-2]*A[n-3], A[n-1]*A[0]*A[1]);
        return max;
    }
}
```



