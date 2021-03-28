# [JAVA/LeetCode] 1209. Remove All Adjacent Duplicates in String II

[problem link](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/)



## Source

```java
import java.util.*;
class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Character> charStack = new Stack<>();
        Stack<Integer> cntStack = new Stack<>();
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            int cnt = 0;
            if(!charStack.isEmpty()&&!cntStack.isEmpty()&&charStack.peek()==ch){
                cnt = cntStack.pop();
                int count = k-1;
                if(cnt==count){
                    while (!charStack.isEmpty()&&count>0){
                        charStack.pop();
                        count--;

                    }
                    continue;
                }
            }
            cntStack.add(cnt+1);
            charStack.add(ch);
        }//for end

        while (!charStack.isEmpty())    sb.append(charStack.pop());

        return sb.reverse().toString();
    }
}
```

