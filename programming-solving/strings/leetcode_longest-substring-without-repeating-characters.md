# [JAVA/LeetCode] 3. Longest Substring Without Repeating Characters

[problem link](https://leetcode.com/problems/longest-substring-without-repeating-characters/)



## source

```java
import java.util.*;
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int begin = 0;

        //Two pointer with sliding window
        for(int i=0; i<s.length(); i++){
            char key = s.charAt(i);

            if(map.containsKey(key)&&map.get(key)>=begin){
                // 알파벳이 있고 지금 확인해보는 서브문자열의 시작점안에 중복되는 알파벳이 존재한다면
                begin = map.get(key)+1; //시작점을 중복되는 알파벳 자리 다음부터로 바꿔줘야함
            }
            map.put(key, i);
            max = Math.max(max, i-begin+1);
        }
        return max;
    }
}
```

