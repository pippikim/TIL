# [JAVA/LeetCode] Set Mismatch

## 문제

- [문제](https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/588/week-1-march-1st-march-7th/3658/)
- 1~n 개의 연속된 정수로 이루어진 배열이 주어지는데 문제가 생겨서 한 개의 숫자가 중복되어 하나의 숫자가 누락됨
- 중복되는 숫자와 누락된 숫자를 찾아서 리턴



## 소스

- [결과](https://leetcode.com/submissions/detail/462561285/?from=explore&item_id=3658)

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] arr = new int[nums.length+1];
        int[] result = new int[2];
        for(int num : nums){
            arr[num]++;
        }

        for(int i=1; i<arr.length; i++){
            if(arr[i]==2) result[0]=i;
            else if(arr[i]==0) result[1]=i;
        }

        return result;
    }
}
```

