# [JAVA/Codility] PermCheck

## 문제

- 정수형 배열 A가 주어진다. 
- 배열 A에는 순열의 원소가 들어가 있다. 
- 순열의 조건은 다음과 같다.
  - 순열은 1~N이 들어가 있다.
  - 순열의 숫자는 중복되지 않는다. 
- 만약 배열A의 안의 원소들이 순열의 조건을 만족한다면 1을 리턴하고 그렇지 않다면 0을 리턴한다.



## 문제접근

- 숫자의 유무를 확인하기 위해 배열을 이용해도 되지만, 원소의 값 범위가 1~10억개까지라 최악의 숫자가 1만있으면 남은 배열의 메모리 공간이 아까웠다.
- 순열은 무조건 1부터 시작하고 끝(N)은 배열안에서 가장 큰 값이다. 
- 가우스 뎃셈을 활용해, A 배열의 원소를 모두 더한 값과 1~N(순열일 때만)을 모두 더한 값을 비교해서 같으면 순열, 틀리면 순열이 아니라고 판단한다. 
  - 가우스 덧셈 
    - 마지막 값이 짝수 : (시작값+마지막값)*(시작값+마지막값/2) 
    - 마지막 값이 홀수 : (시작값+마지막값)*(시작값+마지막값/2) - 중간값
- 또, A배열 안의 원소의 수가 중복되면 무조건 순열이 아니라고 판단한다.
  - hash set은 add, contains 모두 O(1)이라 사용함
  - 이거 때문에 처음에 틀림([소스](###88%)) 
  - 제발 문제 조건 잘보고 테스트케이스 꼼꼼하게 생각하기...
  - 테스트케이스는 각 조건과 부합 또는 부합하지 않는 것 모두 생각해야함ㅠ 

   

## 소스

### 88%

- [결과](https://app.codility.com/demo/results/trainingMHGMSF-WUN/)
- 문제점 : [9, 5, 7, 3, 2, 7, 3, 1, 10, 8] 이렇게 숫자가 중복되면 무조건 0으로 처리해야함 

```java
class Solution {
    
    public int getTotal(int n){
        int gauss = (1+n)*((1+n)/2);
        if(n%2==0)  return gauss;
        else return gauss - ((1+n)/2);
    }

    public int solution(int[] A) {
        int sum = 0;
        int max = 0;
        
        for(int a : A){
            sum+=a; 
            max = Math.max(max, a);
        }//for end

        if(sum==getTotal(max))  return 1;
        else return 0;

    }
}
```



### 100%

- [결과](https://app.codility.com/demo/results/trainingDA9WVE-4HN/)

```java
import java.util.*;
class Solution {
  
    public int getTotal(int n){
        int gauss = (1+n)*((1+n)/2);
        if(n%2==0)  return gauss;
        else return gauss - ((1+n)/2);
    }

    public int solution(int[] A) {
        int sum = 0;
        int max = 0;
        Set<Integer> set = new HashSet<>();

        for(int a : A){
            if(set.contains(a)) return 0;
            
            set.add(a);
            sum+=a;
            max = Math.max(max, a);
        }//for end

        if(sum==getTotal(max))  return 1;
        else return 0;

    }
}
```

