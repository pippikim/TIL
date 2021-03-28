# [JAVA/LeetCode] 155. Min Stack

[problem link](https://leetcode.com/problems/min-stack/)



## Source

```java
import java.util.*;
class MinStack {
private List<Integer> list;
    private List<Integer> minList;

    /** initialize your data structure here. */
    public MinStack() {
        list = new ArrayList<>();
        minList = new ArrayList<>();
    }

    public void push(int val) {
        list.add(val);
        if(minList.size()>0){
            int min = minList.get(list.size()-2);
            minList.add(Math.min(min, val));
        }else{
            minList.add(val);
        }
    }

    public void pop() {
        list.remove(list.size()-1);
        minList.remove(minList.size()-1);

    }

    public int top() {
        return list.get(list.size()-1);
    }

    public int getMin() {
        return minList.get(minList.size()-1);
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

