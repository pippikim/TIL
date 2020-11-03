# [JAVA/프로그래머스] 카펫



## 소스

```java
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int num = brown+yellow;
        for(int i=3; i<=num; i++){
            if(num%i==0){
                int x = (num/i)-2;
                int y = i-2;
                if(x*y==yellow){
                    answer[0]=x+2;
                    answer[1]=y+2;
                    break;
                }
                
            }
        }
        return answer;
    }
}
```

