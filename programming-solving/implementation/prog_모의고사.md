# [JAVA/프로그래머스] 모의고사

## 문제

- [문제링크](https://programmers.co.kr/learn/courses/30/lessons/42840)



## 소스

```java
import java.util.*;
class Solution {
 static class Person{
        int[] answers;
        int answerCnt;
    }
    public int[] solution(int[] answers) {
        List<Integer> answer = new ArrayList<>();
        List<Person> people = new ArrayList<>();
       
        Person first = new Person();
        Person second = new Person();
        Person third = new Person();
        first.answers = new int[]{1,2,3,4,5};
        second.answers = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        third.answers = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        people.add(first);
        people.add(second);
        people.add(third);
        
        
        int max = 0;
        for(int i=0; i<answers.length; i++){
            for(Person p : people) {
            	int idx = i==0? i: i%p.answers.length;
            	p.answerCnt+=answers[i]==p.answers[idx]?1:0;
            	max = Math.max(max, p.answerCnt);
            }
        }
        
        for(int i =0; i<people.size(); i++) {
        	if(people.get(i).answerCnt==max) answer.add(i+1);
        }
        
        int[] arr = new int[answer.size()];
        for(int i=0; i<arr.length; i++) {
        	arr[i]=answer.get(i);
        }
        return arr;
    }
}
```

