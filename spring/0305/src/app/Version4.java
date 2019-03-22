package app;
import factory.PersonPrinterFactory;
import print.PersonPrinter;
import vo.Person;

public class Version4 {

	public static void main(String[] args) {
	
		Person person = new Person();
		person.setName("kimdabin");
		person.setHeight(159);
		person.setWeight(52);
		
		PersonPrinter pp = PersonPrinterFactory.getPrinter("en");
		
		pp.print(person);
	}

}
