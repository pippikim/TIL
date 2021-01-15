# [JAVA/Codility] PermMissingElem

- Lesson 3. Time Complexity

## 소스

### for문을 두 개 사용

```java
class Solution {
    public int solution(int[] A) {
        int anwser = 0;
        boolean[] isExisted = new boolean[A.length+2];
        
        for(int a : A)  isExisted[a] = true;
        for(int i=1; i<isExisted.length; i++){
            if(!isExisted[i]){
                anwser=i;
                break;
            }
        }//for end
      
        return anwser;
    }
}
```

- **O(N) or O(N \* log(N))**

<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gmnily4gvdj30u012e77r.jpg" alt="image-20210114215145826" style="zoom: 33%;" /> <img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gmnima7zx8j30u00roack.jpg" alt="image-20210114215207852" style="zoom:33%;" />



### 연산 사용

```java
class Solution {
    public int solution(int[] A) {
        int sum = 0;
        int total = A.length+1;
        for(int i=0; i<A.length; i++){
            sum+=A[i];
            total+=i+1;            
        }//for end 
        return total-sum;
    }
}
```

- **O(N) or O(N \* log(N))**

<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gmnisfoa1rj30u012iq6f.jpg" alt="image-20210114215802097" style="zoom:33%;" /> <img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gmnistcsn2j30u00rsdic.jpg" alt="image-20210114215824756" style="zoom:33%;" />