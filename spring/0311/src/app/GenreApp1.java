package app;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.GenresService;
import vo.Genre;

public class GenreApp1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("config/applicationContext.xml");
	
		GenresService service = (GenresService)context.getBean("genresService");
		List<Genre> list = service.getList();
		
		for(Genre genre:list) {
			System.out.println(genre.toString());
			
		}
		
	}//main() end

}
