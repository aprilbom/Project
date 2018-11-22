import java.util.*;
public class Contacts {
	static Scanner scan=new Scanner(System.in);
	public static void contactmenu() {
		PersonalInfo pc = new PersonalInfo();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n<Contacts Menu>");
		pc.printDetailMenu();
		int n = scan.nextInt();
		
		while (n != 5) {
			if (n == 1) createContact();
			else if (n == 2) viewContact();
			else if (n == 3) updateContact();
			else if (n == 4) deleteContact();
			else if (n == 5) {
				return;
			}
			//saveFile();
			System.out.println("\n<Contacts Menu>");
			pc.printDetailMenu();
			n = scan.nextInt();
		}
	}
	public static void createContact() {
		//create contact
	}
	public static void viewContact() {
		//view contact
	}
	public static void updateContact() {
		//update contact
	}
	public static void deleteContact() {
		//delete contact
	}
}
