# [프로그래머스/JAVA] H-Index

## 문제

- [문제링크](https://programmers.co.kr/learn/courses/30/lessons/42747)



### 문제접근

- **함정** {10, 100} 일 경우, H-index는 0 이 아니라, 2!

## 소스

```java
class Solution {
    static int getHidx(int c, int[] arr){
        int cnt =0;
        for(int a : arr){
            if(a>=c)    cnt++;
        }
        return cnt;
    }
    public int solution(int[] citations) {
        int answer = 0; 
        for(int c:citations){
        	int cnt = getHidx(c, citations);
            answer = Math.max(answer,cnt>=c?c:cnt);
        }
        
        return answer;
    }
}
```

