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
		
		// 클래스 객체 생성
		ToDoList todo = new ToDoList();
		Notes notes = new Notes();
		
		System.out.println("Hello!");
		printMainMenu();
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		while (n != 5) {
			if (n == 1) ;
			else if (n == 2) todo.toDoListMenu();
			else if (n == 3) ;
			else if (n == 4) notes.notesMenu();
			printMainMenu();
		}
		scan.close();
	}

}
