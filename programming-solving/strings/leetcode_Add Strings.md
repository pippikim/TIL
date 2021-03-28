# [JAVA/LeetCode] 415. Add Strings

[문제링크](https://leetcode.com/problems/add-strings/)



## 소스

```java
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuffer sb = new StringBuffer();
        int carry = 0;
        int idx1 = num1.length()-1;
        int idx2 = num2.length()-1;

        while (idx1>=0||idx2>=0){
            int n1 = idx1<0?0:num1.charAt(idx1--)-'0';
            int n2 = idx2<0?0:num2.charAt(idx2--)-'0';
            int sum = n1+n2+carry;
            carry = sum/10;
            sb.append(sum%10);
        }
        if(carry>0) sb.append(carry);
        sb.reverse();
        return sb.toString();
    }
}
```

