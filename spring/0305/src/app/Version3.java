package app;

import print.PersonPrinterKO;
import print.PersonPrinter;
import print.PersonPrinterEN;
import vo.Person;

public class Version3 {

	public static void main(String[] args) {
	
		Person person = new Person();
		person.setName("kimdabin");
		person.setHeight(159);
		person.setWeight(52);
		
		PersonPrinter pp = new PersonPrinterKO();
		
		pp.print(person);
	}

}
