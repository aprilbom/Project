import java.util.*;
import java.io.*;
import java.text.*;

public class Appointments {
	static Vector<appointment> v = new Vector<appointment>();
	static File f = new File("Appointment List.dat");
	
	static class appointment implements Serializable {

		private String createdate;
		private String where;
		private String with_who;
		
		private appointment(String createdate, String where, String with_who) {
			this.createdate = createdate;
			this.where = where;
			this.with_who = with_who;
		}

		public String getCreatedate() {
			return createdate;
		}

		public void setCreatedate(String createdate) {
			this.createdate = createdate;
		}

		public String getWhere() {
			return where;
		}

		public void setWhere(String where) {
			this.where = where;
		}

		public String getWithWho() {
			return with_who;
		}

		public void setWithWho(String with_who) {
			this.with_who = with_who;
		}
	}
	
	private static void saveFile() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Appointment List.dat"));
		oos.writeObject(v);
		oos.close();
	}
	
	//파일 없으면 트루 반환
	public static boolean fileNotExist() {
		if(f.exists())
			return true;
		else 
			return false;
	}
	
	public static void appointmentMenu() throws Exception {
		if (!fileNotExist()) { // 파일 없으면
			f.createNewFile();
		} else { // 파일 있으면
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			v = (Vector<appointment>)ois.readObject();
			ois.close();
		}
		
		PersonalInfo pInfo = new PersonalInfo();
		System.out.println("\n< Appointment Menu >");
		pInfo.printDetailMenu();
		
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		
		while (n != 5) {
			if (n == 1) createAppointment();
			else if (n == 2) viewAppointment();
			else if (n == 3) updateAppointment();
			else if (n == 4) deleteAppointment();
			else if (n == 5) {
				break;
			}
			saveFile();
			System.out.println("\n<Appointment Menu>");
			pInfo.printDetailMenu();
			n = scan.nextInt();
		}
	}
	

	public static void createAppointment() throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the date(YYMMDD): ");
		String createdate = scan.next();
		
		System.out.print("Enter the address where you meet: ");
		String where = scan.next();
		
		System.out.print("Enter the person who you meet: ");
		String with_who = scan.next();
		
		v.add(new appointment(createdate, where, with_who));
		
	}

	public static void viewAppointment() throws Exception{
		
		System.out.println(" ");
		for (int i = 0; i < v.size(); i++) {
			System.out.println("---------- " + (i + 1) + " ----------");
			System.out.println("Create Date: " + v.get(i).createdate);
			System.out.println("Address: " + v.get(i).where);
			System.out.println("Person: " + v.get(i).with_who);
			System.out.println("------------------------");
		}
	}
	

	public static void updateAppointment() throws Exception{
		viewAppointment();
		System.out.print("\nEnter To Do List Number you want to update: ");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		System.out.print("Enter updated date(YYMMDD): ");
		String createdate = scan.next();
		
		System.out.print("Enter updated Address: ");
		String where = scan.next();
		
		System.out.print("Enter updated Person: ");
		String with_who = scan.next();
		
		v.get(n).createdate = createdate;
		v.get(n).where = where;
		v.get(n).with_who = with_who;
		
		System.out.println("Complete!");

		
	}
	public static void deleteAppointment() throws Exception{
		if (vEmpty()) viewAppointment();
		else {
			viewAppointment();
			System.out.print("\nEnter Appointment Number you want to delete: ");
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
	
	public static boolean vEmpty() {	
		if (v.isEmpty())
			return true ;
		else 
			return false;
	}

}