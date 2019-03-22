package app;


import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.GenresService;
import vo.Genre;

public class GenreApp4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("config/applicationContext.xml");
	
		GenresService service = (GenresService)context.getBean("genresService");
		Scanner scan = new Scanner(System.in);
		Genre genre = new Genre();
		
		System.out.println("insert name of genre you want to delete: ");
				
		String name = scan.nextLine();	
		genre.setName(name);
		
		if(service.remove(name))	System.out.println("success!");
		
		
	}//main() end

}
