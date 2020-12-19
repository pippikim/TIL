# [프로그래머스/자바] 주식가격



## 소스

```java
import java.util.*;

class Solution {
    static class Node{
        int price;
        int idx;
        public Node(int price, int idx){
            this.price=price;
            this.idx = idx;
        }
        
    }
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(prices[0],0));
        while(!q.isEmpty()){
            Node nowNode = q.poll();
            int cnt = 0;
            for(int i = nowNode.idx+1; i<prices.length; i++){
                if(prices[i]>=nowNode.price)    cnt++;
                // else cnt--;
            }//for end
           answer[nowNode.idx] = cnt;
            if(nowNode.idx+1>=prices.length) continue;
            q.add(new Node(prices[nowNode.idx+1],nowNode.idx+1));
            
            
        }//while end 
            
        return answer;  
    }
}
```

