[TOC]

# [JAVA/프로그래머스] N으로 표현

- [문제링크](https://programmers.co.kr/learn/courses/30/lessons/42895)

## 문제

### 문제 설명

아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

12 = 5 + 5 + (5 / 5) + (5 / 5)
12 = 55 / 5 + 5 / 5
12 = (55 + 5) / 5

5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.

### 제한사항

- N은 1 이상 9 이하입니다.
- number는 1 이상 32,000 이하입니다.
- 수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
- 최솟값이 8보다 크면 -1을 return 합니다.

### 입출력 예

| N    | number | return |
| ---- | ------ | ------ |
| 5    | 12     | 4      |
| 2    | 11     | 3      |

#### 입출력 예 설명

예제 #1
문제에 나온 예와 같습니다.

예제 #2
`11 = 22 / 2`와 같이 2를 3번만 사용하여 표현할 수 있습니다.

[출처](https://www.oi.edu.pl/old/php/show.php?ac=e181413&module=show&file=zadania/oi6/monocyfr)



## 문제 접근

- 바로 풀지 못했다. 
  - 검색후 다른 분들이 올린 접근방법을 보고 나서야 생각보다 간단 문제인 것을 알 수 있었고 그제야 풀 수 있었다. 
  - dp문제라고 생각하면 머리가 굳는 기분인데 그냥 문제를 많이 풀어서 자신감을 올릴 수밖에 없는 것 같다. 

### 문제 간단화

- 입력값으로 정수 N(1<=N<=9)과 number(1<=number<=32,000)이 주어진다. 
- N을 n개 사용하여 정수 number를 만드는데, number를 만들 때 사용한 N의 최소 개수 n을 구한다. 
- 정수 number를 만들기 위해서는 다음과 같은 방법이 있다. 
  - NN(N*10+N)
  - 사칙연산

#### 케이스를 작게하기

- N=5 일 때, n=1 이면 나올 수 있는 숫자는 5뿐이다.

- N=5 일 때, n=2 이면 나올 수 있는 숫자는 0(5-5) 1(5/5) 55(NN) 25(5*5) 10(5+5) 

- N=5 일 때, n=3 이면 나올 수 있는 숫자는 대략 이렇다.

  - 555 
  - 15(5+5+5) 60(55+5)  
  - 0(5-5-5) 50(55-5) -50(5-55)
  - 125(5x5x5) 275(55x5)
  - 0(5/5/5) 11(55/5) 0(5/55)
  - 5(5+5-5) -5(-5-5+5)

- 보면 알겠지만, n=1인 케이스와 n=2인 케이스의 사칙연산과 N을 여러번 붙인 결과이다. 

- 즉, N을 n번 사칙연산 및 이어 붙이는 케이스 = 1~n-1인 모든 경우의 사칙연산과 Nx10^n+N 

- 그리고 n<8 이어야한다. 

  

## 소스

```java
import java.util.*;
class Solution {
     public int solution(int N, int number) {
        int answer = -1;
        ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();
        HashSet<Integer> set = new HashSet<Integer>();
        int cnt = 0;
        int repeatNum=N;
        
        set.add(N);
        list.add(set);
        
        while(cnt<8) {
        	if(list.get(cnt).contains(number)) {
        		answer = cnt+1;
        		break;
        	}//if end
        	cnt++;
        	set = new HashSet<Integer>();
        	
        	repeatNum*=(10);
        	repeatNum+=N;
        	set.add(repeatNum);
        	
        	for(int i=0; i<cnt; i++) {
        		Iterator<Integer> it1 = list.get(i).iterator();
        		while( it1.hasNext()) {
        			int num1 = it1.next();
        			Iterator<Integer> it2 = list.get(cnt-1-i).iterator();
        			while(it2.hasNext()) {
        				int num2 = it2.next();
        				set.add(num1+num2);
        				set.add(num1-num2);
        				set.add(num1*num2);
        				if(num2>0)	set.add(num1/num2);
        			}//while end 
        		}//while end 
        	}//for end 
        	
        	list.add(set);
        	
        	
        }
        
      
        return answer;
    }
    
}
```

