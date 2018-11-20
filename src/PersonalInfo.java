import java.*;

public class PersonalInfo {
	
	public static void printMainMenu() {
		System.out.println("1. Contacts \n2. To do list \n3. Appointment \n4. Notes \n");
	}
	
	public static void printDetailMenu() {
		System.out.println("1. Contacts \n2. To do list \n3. Appointment \n4. Notes \n");
	}

	public static void main(String[] args) {
		// printMainMenu();
		Contacts c = new Contacts();
		c.contactmenu();

	}
	
	

}
