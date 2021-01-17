# [JAVA/Codility] GenomicRangeQuery





## 소스

### 시간초과 발생(62%)

- [결과](https://app.codility.com/demo/results/training7XQAJG-C4Z/)

```java
import java.util.*;
class Solution {
    public int[] solution(String S, int[] P, int[] Q) {
        Map<Character, Integer> map = new HashMap<>();
        char[] charr = S.toCharArray();
        int[] result = new int[P.length];
        
        map.put('A',1);
        map.put('C',2);
        map.put('G',3);
        map.put('T',4);
        
        for(int i=0; i<P.length; i++){
            int left = P[i];
            int right = Q[i];
            int min = 5;
            for(int j = left; j<=right; j++){
                min = Math.min(min, map.get(charr[j]));
            }

            result[i] = min;


        }
        

        return result;

    }
}
```



### 모든 케이스 통과(100%)

- [결과](https://app.codility.com/demo/results/training3PKWCZ-YPW/)

```java
import java.util.*;

class Solution {
    public int[] solution(String S, int[] P, int[] Q) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('A',0); map.put('C',1); map.put('G',2); map.put('T',3);
        int[] count = new int[4];
        int[][] impactFactorArr = new int[S.length()+1][4];
        int[] answer = new int[P.length];
        for(int i=0; i<S.length(); i++){
            int idx = map.get(S.charAt(i));
            count[idx]++;
            for(int j =0; j<4; j++){
                impactFactorArr[i+1][j]=count[j];
            }//for end
        }//for end

        for(int i=0; i<P.length; i++){
            int start = P[i];
            int end = Q[i]+1;
            for(int j=0; j<4; j++){
                if(impactFactorArr[end][j]-impactFactorArr[start][j]>0){
                    answer[i] = j+1;
                    break;
                }
            }
        }
        return answer;

    }
}
```

