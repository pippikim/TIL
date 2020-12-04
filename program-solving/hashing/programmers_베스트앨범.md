# [프로그래머스/자바] 베스트앨범

- [문제링크](https://programmers.co.kr/learn/courses/30/lessons/42579)



## 문제

###### 문제 설명

스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.

노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

##### 제한사항

- genres[i]는 고유번호가 i인 노래의 장르입니다.
- plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
- genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
- 장르 종류는 100개 미만입니다.
- 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
- 모든 장르는 재생된 횟수가 다릅니다.

##### 입출력 예

| genres                                | plays                      | return       |
| ------------------------------------- | -------------------------- | ------------ |
| [classic, pop, classic, classic, pop] | [500, 600, 150, 800, 2500] | [4, 1, 3, 0] |

##### 입출력 예 설명

classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.

- 고유 번호 3: 800회 재생
- 고유 번호 0: 500회 재생
- 고유 번호 2: 150회 재생

pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.

- 고유 번호 4: 2,500회 재생
- 고유 번호 1: 600회 재생

따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.

※ 공지 - 2019년 2월 28일 테스트케이스가 추가되었습니다.





## 소스 구현



```java
import java.util.*;
class Solution {
  	
	class Song{
		String genre;
		int idx;
		int playNum;
		
		public Song(int idx, String genre, int playNum) {
			this.idx = idx;
			this.genre = genre;
			this.playNum = playNum;
		}
		
	}
	
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        int len = plays.length;
        Map<String, Integer> map = new HashMap<>();
        List<Song> songList = new ArrayList<>();
        
        for(int i=0; i<len; i++) {
        	Song song = new Song(i, genres[i], plays[i]);
        	songList.add(song);
        	map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        
        //hashmap value 기준으로 내림차순 정렬 
        List<String> keySetList = new ArrayList<>(map.keySet());
        Collections.sort(keySetList, new Comparator<String>() {
        	@Override
        	public int compare(String o1, String o2) {
        		return map.get(o2).compareTo(map.get(o1));
        	}
        });
        
        //songList 를 재생횟수 기준으로 내림차순 정렬 
        Collections.sort(songList, new Comparator<Song>() {
        	@Override
        	public int compare(Song o1, Song o2) {
        		// TODO Auto-generated method stub
        		return Integer.compare(o2.playNum, o1.playNum);
        	}
        
        });
        
        for(String skey : keySetList) {
        	int cnt = 0;
        	
        	for(Song song : songList) {
        		if(cnt==2) break;
        		else if(song.genre.equals(skey)) {
        			cnt++;
        			list.add(song.idx);
        		}
        	}//for end
        	
        }//for end 
        
        answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
        	answer[i]=list.get(i);
        }
        
        
        return answer;
    }
}
```





```java
import java.util.*;
class Solution {
    public static class Music{
        int idx, play;
        String genre;
        
        public Music(int idx, int play, String genre){
            this.idx = idx;
            this.play = play;
            this.genre = genre;
        }
        
    }//Music end
     public static List sortByValue(Map map) {
         List<String> list = new ArrayList();

        list.addAll(map.keySet());
        
        list.sort(new Comparator<String>() {
        	
        	@Override
        	public int compare(String o1, String o2) {
        	    Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                 
                return ((Comparable) v2).compareTo(v1);
        	}
		});
        
        return list;
    } 
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String, Integer> map = new HashMap<>();
        List<String> sortedGenre = new ArrayList<>();
        List<Music> musicList = new ArrayList<>();
        int check = 0;
        List<Integer> tmpList = new ArrayList<>();
        
        for(int i=0; i<genres.length; i++){
            map.put(genres[i], map.getOrDefault(genres[i],0)+plays[i]);
            musicList.add(new Music(i,plays[i],genres[i]));
        }

        sortedGenre = sortByValue(map);
        musicList.sort(new Comparator<Music>(){
            @Override
        	public int compare(Music m1, Music m2) {
        		int p1 = m1.play;
        		int p2 = m2.play;
        		
        		return ((Comparable) p2).compareTo(p1);
        	}
        
        });
        
        for(String genre: sortedGenre ){
            check = 0;
            for(int i=0; i<musicList.size(); i++){
                if(musicList.get(i).genre.equals(genre)){
                    tmpList.add(musicList.get(i).idx);
                    check++;
                }
                if(check==2){
                    break;
                }
            }
        }
        answer = new int[tmpList.size()];
        for(int i =0; i<tmpList.size(); i++){
            answer[i]= tmpList.get(i);
        }
        
        
        return answer;
    }
}
```



