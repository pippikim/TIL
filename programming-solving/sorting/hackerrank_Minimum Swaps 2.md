# [JAVA/HR] Minimum Swaps 2

## 문제설명

- [문제링크](https://www.hackerrank.com/challenges/minimum-swaps-2/problem)

- 연속배열이 주어짐
- 배열의 원소는 중복되지 않음 
- 배열은 정렬되지 않음
- 주어진 배열을 오름차순으로 정렬할 때, 최소 연산횟수를 구해야함



## 문제접근

- 선택정렬을 사용하려고 했으나 오답
- 연속된 배열, 즉 배열의 크기가 7이면 배열은 1~7 숫자들이 중복되지 않고 들어간다는 것을 처음에 몰랐음(문제를 다시 읽으니 눈에 들어옴ㅠ) 

- 정수 정렬 이론을 바탕으로 정렬함(그냥 직관적으로...)



## 소스

```java
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

      static int[] swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x]=arr[y];
        arr[y]=tmp;
        return arr;
    }
static int minimumSwaps(int[] arr) {
        int cnt =0;
        for(int i=0; i<arr.length; i++) {
            while(arr[i]!=i+1) {
                arr=swap(arr, i, arr[i]-1);
                cnt++;
            }
            
        }
        
        return cnt;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

```

