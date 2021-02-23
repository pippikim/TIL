# [JAVA/Leet Code] Longest Word in Dictionary through Deleting

- #### [Longest Word in Dictionary through Deleting](https://leetcode.com/explore/item/3649)



## 소스

- [결과](https://leetcode.com/submissions/detail/459674368/?from=explore&item_id=3649)

```java
import java.util.*;
class Solution {
    public String findLongestWord(String s, List<String> d) {
        String result = "";
        d.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()==o2.length()? o1.compareTo(o2):(o1.length()-o2.length())*-1;
            }
        });

        for(String str : d){
            int idx =0;
            for(int i=0; i<s.length(); i++){
                if(idx<str.length()&&s.charAt(i)==str.charAt(idx))    idx++;
            }
            if(idx==str.length()) return str;
        }

        return  result;
    }
}
```

