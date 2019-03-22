package factory;

import print.PersonPrinter;
import print.PersonPrinterEN;
import print.PersonPrinterKO;

public class PersonPrinterFactory {
	public static PersonPrinter getPrinter(String cmd) {
		if(cmd.equalsIgnoreCase("KO")) {
			return new PersonPrinterKO();
		}else if(cmd.equalsIgnoreCase("en")) {
			return new PersonPrinterEN();
		}else {
			return null;
		}
	}
}
