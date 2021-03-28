# [JAVA/LeetCode] 49. Group Anagrams

[문제링크](https://leetcode.com/problems/group-anagrams/)

## 소스

### #1

```java
import java.util.*;
class Solution {
   public String getKey(String str){
        int[] cnt = new int[26];
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<str.length();i++){
            cnt[str.charAt(i)-97]++;
        }

        for(int i=0; i<cnt.length; i++){
            if(cnt[i]>0){
                char ch = (char)(i+97);
                sb.append(ch+""+cnt[i]);
            }
        }
        return sb.toString();
    }
}
```



### #2

```java
import java.util.*;
class Solution {
   int[] alphabets = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    public long getKey(String str){
        long key = 1;
        for(int i=0; i<str.length();i++){
            key*=alphabets[str.charAt(i)-97];
        }
        return key;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<Long, List<String>> map = new HashMap<>();

        for(String str : strs){
            long key = getKey(str);

            if(map.containsKey(key)){
                map.get(key).add(str);
            }else{
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            }
        }

        for(long key : map.keySet())  result.add(map.get(key));

        return result;
    }
}
```

