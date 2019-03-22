package app;


import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.GenresService;
import vo.Genre;

public class GenreApp2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("config/applicationContext.xml");
	
		GenresService service = (GenresService)context.getBean("genresService");
		Scanner scan = new Scanner(System.in);
		System.out.println("추가할 장르를 입력하세요. ");
		String name = scan.nextLine();
		if(service.register(name)) {
			System.out.println("장르가 입력되었습니다.");
		}
		
	}//main() end

}
