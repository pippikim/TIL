# [JAVA/LeetCode] Intersection of Two Linked Lists

## 문제

- [문제](https://leetcode.com/problems/intersection-of-two-linked-lists/)

- 두 개의 linkedList가 처음으로 겹치는 노드를 찾음

- 뒤에도 모두 같아야 함



## 소스

### 1

- Runtime: 9 ms, faster than 12.54% of Java online submissions for Intersection of Two Linked Lists.

  Memory Usage: 53.2 MB, less than 6.36% of Java online submissions for Intersection of Two Linked Lists.

- [Detail](https://leetcode.com/submissions/detail/463484793/)

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

import java.util.*;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
      
        while(headA!=null){
            set.add(headA);
            headA = headA.next;
        }

        while (headB!=null){
            if(set.contains(headB))  return headB;
            headB = headB.next;
        }

        return null;
    }
}
```



### 2

- Runtime: 1 ms, faster than 97.80% of Java online submissions for Intersection of Two Linked Lists.

  Memory Usage: 41.6 MB, less than 87.83% of Java online submissions for Intersection of Two Linked Lists.

- [Detail](https://leetcode.com/submissions/detail/463485066/)

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

import java.util.*;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        
        if(headA==null||headB==null)    return null;
        
        while(nodeA.next!=null){
            nodeA = nodeA.next;
            lenA++;
        }
        while(nodeB.next!=null){
            nodeB = nodeB.next;
            lenB++;
        }
        if(nodeA!=nodeB)    return null;
        
        nodeA = headA; 
        nodeB = headB;
        
        if(lenA>lenB){
            int diff = lenA-lenB;
            while(diff>0){
                nodeA = nodeA.next;
                diff--;
            }
        }else if(lenA<lenB){
            int diff = lenB-lenA;
            while(diff>0){
                nodeB = nodeB.next;
                diff--;
            }
        }//if~else if end
        
        while(nodeA!=nodeB){
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        
        return nodeA;
    }
}
```

