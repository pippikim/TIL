# [JAVA/프로그래머스] 소수찾기

- 순열을 이용해서 풀었음

## 소스

```java
import java.util.*;
class Solution {
  static Set<Integer> numSet = new HashSet<Integer>();
	static void isPrime(int num) {
		boolean isNot = false;
		for(int i=2; i<num; i++) {
			if(num%i==0) {
				isNot=true; 
				break;
			}
		}
		if(!isNot&&num>1) numSet.add(num);
		
	}
	static void dfs(char[] arr,char[] result, boolean[] v, int n, int r) {
		if(n==r) {
			int num = check(result);
			isPrime(num);	
		}else {
			for(int i=0; i<arr.length; i++) {
				if(!v[i]) {
					v[i]=true;
					result[n]=arr[i];
					dfs(arr,result, v, n+1, r);
					v[i]=false;
				}
			}
		}
		
	}
	
	static int check(char[] arr) {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<arr.length; i++) {
			sb.append(arr[i]);
		}
		
		return Integer.parseInt(sb.toString().trim());
	}
	
	
	public int solution(String numbers) {
        int answer = 0;
        char[] arr = numbers.toCharArray();
        int len = arr.length;
        boolean[] visited = new boolean[len];
        char[] result = new char[len];

        for(int i=1; i<=len; i++) {
        	 dfs(arr,result,visited, 0,i);
        }
       
        
        return numSet.size();
    }
}
```

