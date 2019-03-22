package app;


import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.MoviesDAO;
import service.GenresService;
import service.MoviesService;
import vo.Genre;
import vo.Movie;

public class GenreApp6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("config/applicationContext.xml");
		Scanner scan = new Scanner(System.in);
		
		System.out.println("*Movie input Program*");
		GenresService genreService = (GenresService)context.getBean("genresService");
		List<Genre> genreList = genreService.getList();
		for(Genre genre : genreList) {
			System.out.print(genre.getNo()+")"+genre.getName()+"\t");
		}
		
		MoviesService moviesService = (MoviesService)context.getBean("moviesService");
		Movie info = new Movie();
		System.out.println("gener's number? ");
		int genre = scan.nextInt();
		scan.nextLine();
		info.setGenre(genre);
		
		System.out.println("movie's name? ");
		String name = scan.nextLine();
		info.setName(name);
		
		System.out.println("movie's director");
		String director = scan.nextLine();
		info.setDirector(director);
		
		System.out.println("release date?\n 2019-03-13");
		String releaseDate = scan.nextLine();
		info.setReleaseDate( Date.valueOf(releaseDate));
		
		System.out.println("end date?\n 2019-03-13");
		String endDate = scan.nextLine();
		info.setEndDate( Date.valueOf(endDate));
		
		System.out.println("Audience Number?");
		int audienceNum = scan.nextInt();
		info.setAudienceNum(audienceNum);
		
		if(moviesService.input(info)) {
			System.out.println("success!");
		}
		
	
	}//main() end

}
