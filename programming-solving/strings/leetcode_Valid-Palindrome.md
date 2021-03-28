# [JAVA/LeetCode] 125.Valid Palindrome

[problem link](https://leetcode.com/problems/valid-palindrome/)



## Source

```java
class Solution {
   public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;

        while (left<=right){
            char ch01 = s.charAt(left);
            char ch02 = s.charAt(right);

            if(!isAlpa(ch01)){
                left++;
                continue;
            }
            if(!isAlpa(ch02)){
                right--;
                continue;
            }

            int diff = Math.abs(ch01-ch02);
            if(diff==32&&(ch01<65||ch02<65))    return false;
            else if(diff!=0&&diff!=32)   return false;
            left++;
            right--;
        }

        return true;
    }

    public boolean isAlpa(char ch){
        if((ch>=48&&ch<=57)||(ch>=65&&ch<=90)||(ch>=97&&ch<=122)) return true;
        return false;
    }

}
```

