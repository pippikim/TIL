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

public class GenreApp5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("config/applicationContext.xml");
		Scanner scan = new Scanner(System.in);
		
		MoviesService service = (MoviesService)context.getBean("moviesService");
		List<Movie> list = service.getList();
		int i =0;
		for(Movie movie: list) {
			if(i%4==0) {
				System.out.println();
			}
			System.out.print(movie.getNo()+")"+movie.getName()+"\t");
			i++;
		}
		System.out.println();
		System.out.println("Enter number of movie you want to delete: ");
		int no = scan.nextInt();
		
		if(service.remove(no)) System.out.println("success!");
		else System.out.println("fail");
		
	}//main() end

}
