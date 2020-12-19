# [JAVA/프로그래머스] 프린터

## 문제 

- [문제링크](https://programmers.co.kr/learn/courses/30/lessons/42587)



## 소스

```java
import java.util.*;
class Solution {
    static class Docs{
		int priority, loc;
		public Docs(int p, int l) {
			priority = p;
			loc = l;
		}
	}
	
    public int solution(int[] priorities, int location) {
        int answer = 0;
    	Queue<Docs> que = new LinkedList<>();
        
    	for(int i=0; i<priorities.length; i++) {
    		que.add(new Docs(priorities[i], i));
    		
        }//for end 
    	
    
    	while(!que.isEmpty()) {
    		Docs d = que.poll();
    		boolean flag = false;
    		Iterator<Docs> iter =  que.iterator();
    		
    		while(iter.hasNext()) {
    			if(d.priority<iter.next().priority) {
    				flag = true;
    				break;
    			}
    		}//while end 
    		
    		if(flag) {
    			que.add(d);
    		}else{
    			answer++;
    			if(d.loc==location)	break;
    		}
    		
    	}
        
        return answer;
    }
}
```

