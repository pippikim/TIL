# [JAVA/Codility] MinAvgTwoSlice



## 문제접근

- 시작점과 끝점을 어떻게 정해야하는지 파악하지 못해서 시간초과가 발생했다. 



## 소스

### 시간초과(60%)

- [결과](https://app.codility.com/demo/results/trainingZU9PZ7-67B/)

```java
class Solution {
    public int solution(int[] A) {
        int[] arr = new int[A.length+1];
        double min = Double.MAX_VALUE;
        int minStart = 0;
        int[] minStartArr = new int[A.length];
        arr[1] = A[0];
        for(int i=2; i<=A.length; i++){
            arr[i] = arr[i-1]+A[i-1];
        }//for end

        for(int i = 1; i<=A.length; i++){
            for(int j=1; j<arr.length-i; j++){
                int p = j;
                int q = j+i;
                double div = (arr[q]-arr[p-1])/(i+1.0);
                if(div<min){
                    min = div;
                    minStart = p-1;
                }else if(div==min){
                    minStart = Math.min(minStart, p-1);
                }
            }
        }//for end

        return minStart;
    }
}
```

