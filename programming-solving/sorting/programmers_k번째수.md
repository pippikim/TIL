---
typora-copy-images-to: ipic
---

# [프로그래머스/자바]K번째 수



## 문제 설명

배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.

예를 들어 array가 [1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3이라면

array의 2번째부터 5번째까지 자르면 [5, 2, 6, 3]입니다.
1에서 나온 배열을 정렬하면 [2, 3, 5, 6]입니다.
2에서 나온 배열의 3번째 숫자는 5입니다.
**배열 array, [i, j, k]를 원소로 가진 2차원 배열 commands**가 매개변수로 주어질 때, commands의 모든 원소에 대해 앞서 설명한 연산을 적용했을 때 나온 결과를 배열에 담아 return 하도록 solution 함수를 작성해주세요.

## 제한사항

array의 길이는 1 이상 100 이하입니다.
array의 각 원소는 1 이상 100 이하입니다.
commands의 길이는 1 이상 50 이하입니다.
commands의 각 원소는 길이가 3입니다.

## 입출력 예

array	commands	return
[1, 5, 2, 6, 3, 7, 4]	[[2, 5, 3], [4, 4, 1], [1, 7, 3]]	[5, 6, 3]

## 입출력 예 설명

[1, 5, 2, 6, 3, 7, 4]를 2번째부터 5번째까지 자른 후 정렬합니다. [2, 3, 5, 6]의 세 번째 숫자는 5입니다.
[1, 5, 2, 6, 3, 7, 4]를 4번째부터 4번째까지 자른 후 정렬합니다. [6]의 첫 번째 숫자는 6입니다.
[1, 5, 2, 6, 3, 7, 4]를 1번째부터 7번째까지 자릅니다. [1, 2, 3, 4, 5, 6, 7]의 세 번째 숫자는 3입니다.



## 문제 접근

<img src="https://tva1.sinaimg.cn/large/0082zybpgy1gc6rqdxu4vj31yu0rstvl.jpg" alt="image-20200224004930404" style="zoom:25%;" />

## 소스

```java
import java.util.*;
class Solution {
    
    public static int getK(int[] arr, int kidx){
         Arrays.sort(arr);
        return arr[ kidx-1]; 
    }
    
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int idx = 0;
        for(int[] command: commands){
            int i = command[0];
            int j = command[1];
            int k = command[2];
            answer[idx] = getK(Arrays.copyOfRange(array,i-1,j),k);
            idx++;
        }
        return answer;
    }
}
```



## 해설

- command[0]: i / command[1]:j / command[2]: k
- Arrays.copyOfRange 메소드는 필요한 배열의 부분을 복사해서 새로운 배열에 넣을 수 있음
- 복사된 배열(tmp)을 오름차순으로 정렬
- answer 배열에 k번째 수를 넣음

