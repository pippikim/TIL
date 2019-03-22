package app;

import print.PersonPrinterKO;
import vo.Person;

public class Version1 {

	public static void main(String[] args) {
	
		Person person = new Person();
		person.setName("kimdabin");
		person.setHeight(159);
		person.setWeight(52);
		
		PersonPrinterKO pp = new PersonPrinterKO();
		
		pp.print(person);
	}

}
