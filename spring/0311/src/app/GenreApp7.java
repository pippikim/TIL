package app;


import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.MoviesDAO;
import service.GenresService;
import service.MoviesService;
import vo.Genre;
import vo.Movie;

public class GenreApp7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("config/applicationContext.xml");
		Scanner scan = new Scanner(System.in);
		
		MoviesService service = (MoviesService)context.getBean("moviesService");
		System.out.println("Enter the name of movie you want to search");
		String name = scan.nextLine();
		List<Movie> list = service.search(name);
		if(list.isEmpty()) {
			System.out.println("검색된 영화가 없습니다. ");
			
		}else {
	
		for(Movie movie : list) {
			System.out.println(movie.toString());
		}}
		
	}//main() end

}
