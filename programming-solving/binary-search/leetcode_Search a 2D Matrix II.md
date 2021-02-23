# [JAVA/LeetCode] Search a 2D Matrix II

- #### [Search a 2D Matrix II](https://leetcode.com/explore/item/3650)



## 코드

- [결과](https://leetcode.com/submissions/detail/459662944/?from=explore&item_id=3650)

```java
class Solution {
    public boolean searchCol(int[] m, int target){
        int left = 0;
        int right = m.length-1;

        while(left<=right){
            int mid = (left+right)/2;
            if(m[mid]==target)  return true;
            else if(m[mid]<target) left = mid+1;
            else right = mid-1;
        }

        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length-1;

        for(int i=0; i<matrix.length; i++){
            if(matrix[i][0]<target){
                if(searchCol(matrix[i],target))   return true;
            }else if(matrix[i][0]==target)  return true;
        }

        return false;
    }
}
```

