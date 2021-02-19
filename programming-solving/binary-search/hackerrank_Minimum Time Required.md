# [JAVA/HackerRank] Minimum Time Required

## 문제

- [링크](https://www.hackerrank.com/challenges/minimum-time-required/problem)
- 고정된 일수가 있는 여러 기계
- 모든 기계가 동시에 작동하는 경우 필요한 주문을 생산하는 데 필요한 최소 일수?

### 입력값

- 1 <= n <= 10만
- 1 <= goal <= 10억
- 1 <= machines[i] <= 10억 



## 접근

- 해당 기계의 고정 일수 * 주문 개수 = 주문을 생산하는 데 걸린 일수 

- 각 모든 기계 각각 위의 계산을 하면 (n * goal) 연산횟수가 필요하고 최악일 때는 10만 * 10억번이 된다. 

- 이분탐색을 이용해서 문제를 풀었다. 

- 수도 코드

  <img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gnt8jrn1bqj30rs0usdob.jpg" alt="image-20210219235722752" style="zoom:33%;" /> 



## 소스

```java
static long minTime(long[] machines, long goal) {
        Arrays.sort(machines);
        long left = machines[0];
        long right = machines[0]*goal;

        while (left<=right){
            long mid = (left+right)/2;
            long cnt = 0;
            for(int i=0; i<machines.length; i++){
                cnt+=(mid/machines[i]);
            }
            if(cnt>=goal)    right = mid-1;
            else left = mid+1;
        }

        return left;
    }
```

