# [JAVA/LeetCode] Arithmetic Slices

- #### [Arithmetic Slices](https://leetcode.com/explore/item/3644)



## 소스

- [결과](https://leetcode.com/submissions/detail/459685897/?from=explore&item_id=3644)

```java
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int[] dp = new int[A.length];
        int sum = 0;
        for(int i = 2; i<A.length; i++){
            if(A[i]-A[i-1]==A[i-1]-A[i-2]){
                dp[i] = dp[i-1]+1;
                sum+=dp[i];
            }
        }

        return sum;
    }
}
```

