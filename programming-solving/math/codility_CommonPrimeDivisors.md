# [JAVA/Codility] CommonPrimeDivisors



## 소스

### 76%

- [결과](https://app.codility.com/demo/results/training657CUU-A9Z/)

```java
class Solution {
    public int gcd(int a, int b, int res){
        if(a==b)    return res*a;
        else if(((a&1)==0) && ((b&1)==0))   return gcd(a/2, b/2, 2*res);
        else if((a&1)==0) return gcd(a/2, b, res);
        else if((b&1)==0) return gcd(a, b/2, res);
        else if(a>b)    return gcd(a-b, b, res);
        else    return gcd(a,b-a, res);
    }
    public int solution(int[] A, int[] B) {
        int result = 0;
        for(int i=0; i<A.length; i++){
            int n = Math.max(A[i],B[i]);
            int m = Math.min(A[i],B[i]);
            if(n==m){
                result++;
                continue;
            }else if(n%m==0){
                int res = gcd(n/m,m,1);
                if(res>1) result++;
            }

        }
        return result;
    }
}
```

