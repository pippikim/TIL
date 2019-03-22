package print;

import vo.Person;

public class PersonPrinterKO implements PersonPrinter{
	
	private Person person;
	
	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public void print() {
		System.out.println("이름: "+person.getName());
		System.out.println("키: "+person.getHeight());
		System.out.println("몸무게: "+person.getWeight());
	}
}
