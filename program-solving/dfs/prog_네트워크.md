[TOC]

# [JAVA/프로그래머스] 네트워크

- [문제링크](https://programmers.co.kr/learn/courses/30/lessons/43162#)

## 문제

### 문제 설명

네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다. 예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고, 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다. 따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.

컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.

### 제한사항

- 컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
- 각 컴퓨터는 0부터 `n-1`인 정수로 표현합니다.
- i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
- computer[i][i]는 항상 1입니다.

### 입출력 예

| n    | computers                         | return |
| ---- | --------------------------------- | ------ |
| 3    | [[1, 1, 0], [1, 1, 0], [0, 0, 1]] | 2      |
| 3    | [[1, 1, 0], [1, 1, 1], [0, 1, 1]] | 1      |

#### 입출력 예 설명

예제 #1
아래와 같이 2개의 네트워크가 있습니다.
<img src="https://tva1.sinaimg.cn/large/0081Kckwgy1gl8jpnw02aj30ju0ha0td.jpg" alt="image0.png" style="zoom:33%;" />

예제 #2
아래와 같이 1개의 네트워크가 있습니다.
<img src="https://tva1.sinaimg.cn/large/0081Kckwgy1gl8jpoh9mqj30kc0ggt9c.jpg" alt="image1.png" style="zoom:33%;" />



## 문제접근

- 그래프의 노드와 간선의 관계에 대한 문제이고 dfs를 이용해 풀었다.
  - 깊이가 정해져 있는 문제는 dfs로 푸는 게 편하다. 
- 처음에는 문제를 잘못이해하고 1이 모여져있는 곳을 하나로 카운트 했다. 
  - [bfs를 이용해 인접한 1의 집합을 카운트한 소스](#틀린소스)
  - 아주 바보같은 일이다ㅠ
- 인접리스트를 만들어서 dfs를 이용해 문제를 풀었다가 다시 생각해보니 이미 배열 자체에 인접한 노드들의 관계가 1로 표시된 인접행렬이었다 그래서 인접행렬을 이용해 dfs를 풀었다. 
  - [인접리스트를 만들어서 푼 소스](#소스#1)
  - [배열을 이용해서 푼 소스](#소스2)
  - 입력값을 정확하게 인지하는 것은 정말 중요하다!!!



## 소스

### 틀린소스

```java
import java.util.*;

class Solution {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static boolean[][] visited;
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public void bfs(int n,int x, int y, int[][] coms){
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(x,y));
        while(!que.isEmpty()){
            Node now = que.poll();
            for(int i=0; i<4; i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];
                if(nx<0||nx>=n||ny<0||ny>=n)    continue;
                if(!visited[nx][ny]&&coms[nx][ny]==1){
                    visited[nx][ny] = true;
                    que.add(new Node(nx,ny));
                }
            }
        }//while end
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n][n];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j]&&computers[i][j]==1){
                    visited[i][j]=true;
                    answer++;
                    bfs(n,i, j, computers);
                }
            }
        }
        
        
        return answer;
    }
}
```





### 소스#1

```java
import java.util.*;

class Solution {
static ArrayList<ArrayList<Integer>> networks;
	static boolean[] isOk;
	public int dfs(int idx) {
		isOk[idx] = true;
		ArrayList<Integer> list = networks.get(idx);
		for(Integer item : list) {
			if(!isOk[item]) {
				isOk[item]=true;
				dfs(item);
			}
		}
		
		return 1;
	}

	public int solution(int n, int[][] computers) {
		int answer = 0;
		networks = new ArrayList<>();
		isOk = new boolean[n];
		for (int i = 0; i < n; i++) {
			networks.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (computers[i][j] == 1) {
					networks.get(i).add(j);
				}
			}
		}
		for(int i=0; i<n; i++) {
			if(!isOk[i])	answer+=dfs(i);
		}
		
		return answer;
	}
	    
}
```

<img src="https://tva1.sinaimg.cn/large/0081Kckwgy1gl8jcpb3gaj30j40ow77n.jpg" alt="image-20201201193342612" style="zoom:50%;" />  



### 소스#2

```java
import java.util.*;

class Solution {
static boolean[] isOk;
	public int dfs(int[][] coms, int vertex, int n) {
		isOk[vertex]=true;
		for(int i=0; i<n; i++ ) {
			if(!isOk[i]&&coms[vertex][i]==1) {
				isOk[i]=true;
				dfs(coms, i, n);
			}
		}
		
		return 1;
	}
	
	public int solution(int n, int[][] computers) {
		int answer = 0;
		isOk = new boolean[n];
		for(int i=0; i<n; i++) {
			if(!isOk[i])	answer+=dfs(computers, i, n);
		}
		
		return answer;
	}
}
```

<img src="https://tva1.sinaimg.cn/large/0081Kckwgy1gl8jc67tedj30j40owwhs.jpg" alt="image-20201201193309069" style="zoom:50%;" />  