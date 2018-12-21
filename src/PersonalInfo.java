import java.util.*;


public class PersonalInfo {
	public static void printMainMenu() {
		System.out.println("\n<Main Menu>\n1. Contact\n2. ToDoList\n3. Appointment\n4. Note\n5. Exit");
		System.out.print("Enter Number: ");
	}
	
	public static void printDetailMenu() {
		System.out.println("1. Create\n2. View\n3. Update\n4. Delete\n5. Back to Main Menu");
		System.out.print("Enter Number: ");
	}

	public static void main(String[] args) throws Exception{

		Contacts cont = new Contacts();
		ToDoList todo = new ToDoList();
		Appointments app = new Appointments();
		Notes note = new Notes();
		
		System.out.println("Hello!");
		printMainMenu();
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		while (n != 5) {
			if (n == 1) cont.contactMenu();
			else if (n == 2) todo.toDoListMenu();
			else if (n == 3) app.appointmentMenu();
			else if (n == 4) note.notesMenu();
			else if (n == 5) return;
			printMainMenu();
			n = scan.nextInt();
		}
		scan.close();
	}

}