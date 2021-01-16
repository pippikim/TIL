# [JAVA/Codility] MissingInteger

[결과](https://app.codility.com/demo/results/trainingYVDR3D-NH9/)



## 소스

```java
class Solution {
    public int solution(int[] A) {
        boolean[] existed = new boolean[1000001];
        int answer = 0;
        for(int a : A){
            if(a>0&&!existed[a])    existed[a]=true;
        }//for end 
        for(int i=1; i<existed.length; i++){
            if(!existed[i]){
                answer = i;
                break;
            }
        }//for end 

        return answer;
    }
}
```

