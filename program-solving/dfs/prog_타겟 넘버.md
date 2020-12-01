[TOC]

# [JAVA/프로그래머스] 타겟 넘버

- [문제링크](https://programmers.co.kr/learn/courses/30/lessons/43165)

## 문제

### 문제 설명

n개의 음이 아닌 정수가 있습니다. 이 수를 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.

```
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
```

사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.

### 제한사항

- 주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
- 각 숫자는 1 이상 50 이하인 자연수입니다.
- 타겟 넘버는 1 이상 1000 이하인 자연수입니다.

### 입출력 예

| numbers         | target | return |
| --------------- | ------ | ------ |
| [1, 1, 1, 1, 1] | 3      | 5      |

#### 입출력 예 설명

문제에 나온 예와 같습니다.



## 문제접근

- 평이한 dfs문제였다. 
- 입력값으로 정수형 numbers 배열과 정수형 target을 받는다.
- numbers안에 있는 원소값들을 더하기 또는 빼기를 이용해 target을 만들 수 있는데 그 방법의 수(n)를 출력한다. 
- char형 배열을 만들어서 numbers의 원소와 가능한 모든 +,- 를 넣는다
- char 배열을 완성하면 연산을 하고 target이 나오면 +1 카운트한다.   



## 소스

```java
class Solution {
   static int cnt;
	
	public int sum(char[] arr) {
		int result = 0;
		char op = '\u0000';
		
		for(char a : arr) {
			if(a=='+'||a=='-')	op=a;
			else {
				int num = a-'0';
				if(op=='+')	result+=num;
				else if(op=='-')	result-=num;
			}
		}
		return result;
	}
	
	 public void findSign(int n, char[] arr, int t, int max) {
		 if(n==max) {
			 if(sum(arr)==t) {
				 cnt++;
			 }
			 return;
		 }
		 arr[n*2] = '+';
		 findSign(n+1, arr, t, max);
		 arr[n*2] = '-';
		 findSign(n+1, arr, t, max);
		 
	 }
	 public int solution(int[] numbers, int target) {
	        int answer = 0;
	        int n = numbers.length;
	        char[] arr = new char[n*2];
	        cnt = 0;
	        int idx = 1;
	        for(int nu : numbers) {
	        	arr[idx] = (char) (nu+'0');
	        	idx+=2;	        	
	        }
     
	        findSign(0, arr, target, n);
	        return cnt;
	    }
}
```

