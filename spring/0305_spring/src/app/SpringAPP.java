package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import printer.PersonPrinter;
import vo.Person;

public class SpringAPP {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		
		PersonPrinter pp = (PersonPrinter) context.getBean("printer");
		
		pp.print();
	}
}
