package printer;

import vo.Person;

public class PersonPrinterEN implements PersonPrinter {
	
	private Person person;
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	@Override
	public void print() {
		System.out.println("name: "+person.getName());
		System.out.println("height: "+person.getHeight());
		System.out.println("weight: "+person.getWeight());
	}
}
