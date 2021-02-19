# [JAVA/LeetCode] Container With Most Water

## 문제

- a1, a2, ..., an (ai >-1)
- 선 i의 두 끝점이 (i, ai) 및 (i,0) => 수직선이 그려진다 
- 컨테이너가 가장 많은 물을 포함하도록 x축과 함께 컨테이너를 형성하는 두 개의 선을 찾아 너비를 구해 리턴 



## 소스

```java
class Solution {
        public int maxArea(int[] height) {
            int max = 0;
            int start = 0;
            int last = height.length-1;

            while (last-start>0){
                int standard = Math.min(height[start], height[last]);
                max = Math.max(max, (last-start)*standard);

                if(height[start]==standard)  start++;
                else if(height[last]==standard)  last--;
            }

            return max;
        }
}
```

