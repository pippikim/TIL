package app;


import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.GenresService;
import vo.Genre;

public class GenreApp3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("config/applicationContext.xml");
	
		GenresService service = (GenresService)context.getBean("genresService");
		Scanner scan = new Scanner(System.in);
		Genre genre = new Genre();
		
		System.out.println("변경할 장르번호를 입력하세요. ");
		int no = scan.nextInt();
		scan.nextLine();
		genre.setNo(no);
		
		System.out.println("변경될 장르명을 입력하세요. ");
		
		String name = scan.nextLine();	
		genre.setName(name);
		
		if(service.update(genre)) {
			System.out.println("장르명이 변경 되었습니다. ");
		}
		
		
	}//main() end

}
