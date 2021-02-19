# [JAVA/LeetCode] Minimum Remove to Make Valid Parentheses



## 소스 

```java
import java.util.*;

class Solution {
    public char[] isValidCloseParentheses(char[] chars){
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<chars.length; i++){
            char c = chars[i];
            if(c=='(')  stack.add(c);
            else if(c==')'){
                if(stack.isEmpty()) chars[i]= '\0';
                else stack.pop();
            }
        }

        return chars;
    }

    public char[] isValidOpenParentheses(char[] chars){
        Stack<Character> stack = new Stack<>();
        for(int i=chars.length-1; i>=0; i--){
            char c = chars[i];
            if(c==')')  stack.add(c);
            else if(c=='('){
                if(stack.isEmpty()) chars[i]= '\0';
                else stack.pop();
            }
        }

        return chars;
    }

    public String minRemoveToMakeValid(String s) {
        char[] chars = s.toCharArray();
        StringBuffer sb = new StringBuffer();

        chars = isValidCloseParentheses(chars);
        chars = isValidOpenParentheses(chars);

        for(char c : chars){
            if(c!='\0') sb.append(c);
        }
        return sb.toString();
    }
}
```

