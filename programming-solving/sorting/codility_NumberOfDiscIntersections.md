# [JAVA/Codility] NumberOfDiscIntersections



## 소스

### 62%

- 시간초과 때문에 감점 받음... 
- [결과](https://app.codility.com/demo/results/trainingGQKSB7-EZ3/)

```java
class Solution {
     public int solution(int[] A) {
        int cnt =0;
        for(int i=0; i<A.length; i++){
            long standard = (long) i+A[i];
            for (int j=i+1; j<A.length; j++){
                if(standard>=j-A[j]){
                    cnt++;
                }
            }//for end
            if(cnt>10000000)    return -1;

        }//for end

        return cnt;
    }
}
```

