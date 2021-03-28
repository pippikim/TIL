# [JAVA/LeetCode] 1047. Remove All Adjacent Duplicates In String

[problem link](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/)

stack 

## Source

```java
import java.util.*;
class Solution {
    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i<S.length(); i++){
            char c = S.charAt(i);
            if(!stack.isEmpty()&&stack.peek()==c)   stack.pop();
            else stack.add(c);
        }

        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
       return sb.reverse().toString();
    }
}
```

