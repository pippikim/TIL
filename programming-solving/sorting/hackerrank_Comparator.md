# [JAVA/HR] Comparator

## 문제설명

- [문제링크](https://www.hackerrank.com/challenges/ctci-comparator-sorting/problem)
- score는 내림차순으로 정렬
- score가 중복된다면 name을 사전 기준으로(오름차순) 정렬



## 소스

```java
import java.util.*;

class Player {
	String name;
	int score;

	Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
}

class Checker implements Comparator<Player> {
  	// complete this method
	public int compare(Player a, Player b) {
        if(a.score==b.score)    return a.name.compareTo(b.name);
        else    return Integer.compare(b.score,a.score);
    }
}


public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Player[] player = new Player[n];
        Checker checker = new Checker();
        
        for(int i = 0; i < n; i++){
            player[i] = new Player(scan.next(), scan.nextInt());
        }
        scan.close();

        Arrays.sort(player, checker);
        for(int i = 0; i < player.length; i++){
            System.out.printf("%s %s\n", player[i].name, player[i].score);
        }
    }
}
```

