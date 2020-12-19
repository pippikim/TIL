# [JAVA/HR] Sherlock and the Valid String

## 문제설명

- [문제링크](https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem)





## 코드

```java
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

 // Complete the isValid function below.
    static String isValid(String s) {
        char[] arr = s.toCharArray();
        int[] checked = new int[123];
        int[] freq = new int[100000];
        int cnt = 0;
        int max = -9999999;//빈도수 큰 
        int min = 9999999;//빈도수 적은 
        for (char c : arr) {
            ++checked[c];
        }

        for (int i = 97; i <= 122; i++) {
            if (checked[i] > 0) {
                max = Math.max(max, checked[i]);
                min = Math.min(min, checked[i]);
            }

        }

        int maxs = 0;//빈도수가 큰 알파벳들 
        int mins = 0;//빈도수가 적은 알파벳들 
//        System.out.println(max+" "+min);
        if (max == min)
            return "YES";
        else  {
            for (int i = 97; i <= 122; i++) {
                if (checked[i] > 0) {
                    if (checked[i] == max)
                        maxs+=max;
                    else if (checked[i] == min)
                        mins+=min;
                }
                
            }
            
//            System.out.println(maxs/max+" "+mins/min);    
            
            if (maxs + mins != arr.length)    return "NO"; 
            else if(max-min==1&&maxs/max==1||mins/min==1)    return "YES";
            else if(maxs/max==1&&max==1||mins/min==1&&min==1)    return "YES";
            else return "NO";
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

```

