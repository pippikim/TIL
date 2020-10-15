# [프로그래머스/자바] 베스트앨범



## 소스 구현

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



