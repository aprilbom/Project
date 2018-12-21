import java.util.*;
import java.io.*;
import java.text.*;

public class ToDoList {
	static Vector<todo> v = new Vector<todo>();
	
	static class todo implements Serializable {

		private String createdate;
		private String duedate;
		private String description;
		
		private todo(String createdate, String duedate, String description) {
			this.createdate = createdate;
			this.duedate = duedate;
			this.description = description;
		}

		public String getCreatedate() {
			return createdate;
		}

		public void setCreatedate(String createdate) {
			this.createdate = createdate;
		}

		public String getDuedate() {
			return duedate;
		}

		public void setDuedate(String duedate) {
			this.duedate = duedate;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}
	
	public static Vector<todo> saveFile() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("To Do List.dat"));
		oos.writeObject(v);
		oos.close();
		return v;
	}
	
	public static void toDoListMenu() throws Exception {
		File f = new File("To Do List.dat");
		if (!f.exists()) {
			f.createNewFile();
		} else {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			v = (Vector<todo>)ois.readObject();
			ois.close();
		}
		
		PersonalInfo pInfo = new PersonalInfo();
		System.out.println("\n<To Do List Menu>");
		pInfo.printDetailMenu();
		
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		
		while (n != 5) {
			if (n == 1) createToDoList();
			else if (n == 2) viewToDoList();
			else if (n == 3) updateToDoList();
			else if (n == 4) deleteToDoList();
			else if (n == 5) {
				return;
			}
			saveFile();
			System.out.println("\n<To Do List Menu>");
			pInfo.printDetailMenu();
			n = scan.nextInt();
		}
	}
	
	public static void createToDoList() throws Exception {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the Duedate(YYMMDD): ");
		String duedate = scan.next();

		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		Calendar c1 = Calendar.getInstance();
		String createdate = format.format(c1.getTime());
		
		scan.nextLine();
		System.out.print("Enter the Description: ");
		String description = scan.nextLine();
		
		v.add(new todo(createdate, duedate, description));
	}

	public static boolean viewToDoList() throws Exception{
		System.out.println(" ");
		if (v.isEmpty()) {
			System.out.println("No To Do List Yet!");
			return true;
		}
		else {
			for (int i = 0; i < v.size(); i++) {
				System.out.println("---------- " + (i + 1) + " ----------");
				System.out.println("Create Date: " + v.get(i).createdate);
				System.out.println("Due Date: " + v.get(i).duedate);
				System.out.println("Description: " + v.get(i).description);
				System.out.println("------------------------");
			}
			return true;
		}
	}
	
	public static void deleteToDoList() throws Exception{
		if (v.isEmpty()) viewToDoList();
		else {
			viewToDoList();
			System.out.print("\nEnter To Do List Number you want to delete: ");
			Scanner scan = new Scanner(System.in);
			int n = scan.nextInt();
			
			System.out.print("Want to delete " + n + "? (y/n): ");
			String ch = scan.next();
			
			if (ch.equals("y")) {
				v.remove(n - 1);
				System.out.println("Complete!");
			} else {
				System.out.println("Not deleted!");
			}
		}
	}
	
	public static void updateToDoList() throws Exception{
		if (v.isEmpty()) viewToDoList();
		else {
			viewToDoList();
			System.out.print("\nEnter To Do List Number you want to update: ");
			Scanner scan = new Scanner(System.in);
			int n = scan.nextInt();
		
			System.out.print("Enter updated Duedate(YYMMDD): ");
			String duedate = scan.next();

			SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
			Calendar c1 = Calendar.getInstance();
			String createdate = format.format(c1.getTime());

			scan.nextLine();
			System.out.print("Enter updated Description: ");
			String description = scan.nextLine();
		
			v.get(n - 1).createdate = createdate;
			v.get(n - 1).duedate = duedate;
			v.get(n - 1).description = description;
		
			System.out.println("Complete!");
		}
	}
}