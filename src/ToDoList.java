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
	
	public static void toDoListMenu() throws Exception {
		PersonalInfo pInfo = new PersonalInfo();
		System.out.println("<To Do List Menu>");
		pInfo.printDetailMenu();
		
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		
		while (n != 5) {
			if (n == 1) {
				createToDoList();
				fileStream();
			}
			else if (n == 2) viewToDoList();
			else if (n == 3) deleteToDoList();
			else if (n == 4) updateToDoList();
			System.out.println("<To Do List Menu>");
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
		
		System.out.print("Enter the Description: ");
		String description = scan.next();
		
		v.add(new todo(createdate, duedate, description));
	}
	
	private static void fileStream() throws Exception {
		File f = new File("ToDoList.dat");
		if (!f.exists()) {
			FileOutputStream fout = null;
			ObjectOutputStream oos = null;
			try {
				fout = new FileOutputStream("ToDoList.dat");
				oos = new ObjectOutputStream(fout);
				oos.writeObject(v);
				oos.reset();
			} catch(Exception e) {
				System.out.println(e);
			} finally {
				try {
					fout.close();
					oos.close();
				} catch (IOException ie) {
					System.out.println(ie);
				}
			}
		} else {
			FileInputStream fin = null;
			ObjectInputStream ois = null;
			try {
				fin = new FileInputStream("ToDoList.dat");
				ois = new ObjectInputStream(fin);
				Vector<todo> inputv = (Vector<todo>)ois.readObject();
				v.addAll(inputv);
			} catch(Exception e) {
				System.out.println(e);
			} finally {
				try {
					fin.close();
					ois.close();
				} catch (IOException ie) {
					System.out.println(ie);
				}
			}
		}
	}
	
	public static void viewToDoList() throws Exception{
		Vector<todo> viewv = new Vector<todo>();
		/*FileInputStream fin = null;
		ObjectInputStream ois = null;
		try {
			fin = new FileInputStream("ToDoList.dat");
			ois = new ObjectInputStream(fin);
			viewmap = (HashMap<String, todo>)ois.readObject();
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			try {
				fin.close();
				ois.close();
			} catch (IOException ie) {
				System.out.println(ie);
			}
		}*/
		
		int n = 1;
		Iterator<todo> iter = viewv.iterator();
		while(iter.hasNext()) {
			todo temp = iter.next();
			System.out.println("---------- " + n + " ----------");
			System.out.println("Create Date: " + temp.createdate);
			System.out.println("Due Date: " + temp.duedate);
			System.out.println("Description: " + temp.description);
			System.out.println("------------------------");
			n++;
		}
	}
	
	public static void deleteToDoList() {
		
	}
	
	public static void updateToDoList() {
		
	}
}
