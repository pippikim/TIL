package app;

import print.PersonPrinterKO;
import print.PersonPrinterEN;
import vo.Person;

public class Version2 {

	public static void main(String[] args) {
	
		Person person = new Person();
		person.setName("kimdabin");
		person.setHeight(159);
		person.setWeight(52);
		
		PersonPrinterEN pp = new PersonPrinterEN();
		
		pp.print(person);
	}

}
