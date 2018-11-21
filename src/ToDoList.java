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
	
	private static void saveFile() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("To Do List.dat"));
		oos.writeObject(v);
		oos.close();
	}
	
	public static void toDoListMenu() throws Exception {
		// 프로그램 실행될때마다 파일 있는지 확인
		// 확인해서 파일 없으면 새로 만들어서 빈 백터 넣기
		// 확인해서 파일 있으면 파일 내용 새 백터에 읽어오기 
		
		File f = new File("To Do List.dat");
		if (!f.exists()) { // 파일 없으면
			f.createNewFile();
		} else { // 파일 있으면
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
				break;
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
		
		// 현재 날짜 받아오기
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		Calendar c1 = Calendar.getInstance();
		String createdate = format.format(c1.getTime());
		
		scan.nextLine();
		System.out.print("Enter the Description: ");
		String description = scan.nextLine();
		
		v.add(new todo(createdate, duedate, description));
	}

	public static void viewToDoList() throws Exception{
		System.out.println(" ");
		if (v.isEmpty()) System.out.println("No To Do List Yet!");
		else {
			for (int i = 0; i < v.size(); i++) {
				System.out.println("---------- " + (i + 1) + " ----------");
				System.out.println("Create Date: " + v.get(i).createdate);
				System.out.println("Due Date: " + v.get(i).duedate);
				System.out.println("Description: " + v.get(i).description);
				System.out.println("------------------------");
			}
		}
	}
	
	// 수정해야함
	public static void deleteToDoList() throws Exception{
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
	
	public static void updateToDoList() throws Exception{
		viewToDoList();
		System.out.print("\nEnter To Do List Number you want to update: ");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		System.out.print("Enter updated Duedate(YYMMDD): ");
		String duedate = scan.next();
		
		// 현재 날짜 받아오기
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
