---
typora-copy-images-to: ipic
---

# [프로그래머스/자바] 가장 큰 수 

## 문제 설명

0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.

예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.

0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.



## 제한 사항

numbers의 길이는 1 이상 100,000 이하입니다.
numbers의 원소는 0 이상 1,000 이하입니다.
정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.



## 입출력 예

numbers	return
[6, 10, 2]	6210
[3, 30, 34, 5, 9]	9534330



## 문제 접근

<img src="https://tva1.sinaimg.cn/large/0082zybpgy1gc6s2g0vbij31jg0rs1ed.jpg" alt="image-20200224010106063" style="zoom:33%;" />



## 소스

```java
import java.util.*;

class Solution {
     
    public String solution(int[] numbers) {
        
       String[] output = new String[numbers.length];
        StringBuffer sb = new StringBuffer();
        
    	for(int i=0; i<numbers.length; i++) {
    		output[i]= String.valueOf(numbers[i]);
    	}
        Arrays.sort(output,(o1,o2)-> (o2+o1).compareTo(o1+o2));
        if(output[0].equals("0")){ return "0";}
        else{
            for(String o : output){
            sb.append(o);
        }
        
        return sb.toString();
        }
     
        
    }
}
```



## 해설

- numbers 배열의 원소에서 34 와 9 를 비교할 때 사전 순(34<9)으로 정렬을 하기 위하여 문자열 배열로 변환해 sort 
- Arrays.sort는 오름차순으로 정렬이 되기 때문에, Comparable를 구현해 내림차순으로 정렬 
- 메모리 효용성을 위해 Stringbuffer로 정렬된 output 배열의 원소를 append