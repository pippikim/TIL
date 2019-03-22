package vo;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;


public class Movie implements Serializable {
	private int no, audienceNum, genre;
	private String name, director;
	private Date releaseDate, endDate;
	private SimpleDateFormat sdf;
	public Movie() {
		sdf = new SimpleDateFormat("yyyy년 MM월 d일");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return no+") "+name+" / director : "+director+" / start : "+sdf.format(releaseDate)
		+" / AN : "+String.format("%,d", audienceNum)+"명"+" / genre : "+genre;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getAudienceNum() {
		return audienceNum;
	}
	public void setAudienceNum(int audienceNum) {
		this.audienceNum = audienceNum;
	}
	
	public int getGenre() {
		return genre;
	}
	public void setGenre(int genre) {
		this.genre = genre;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
