import java.util.*;
import java.io.*;
import java.text.*;

public class Appointments {
	static Vector<appointment> v = new Vector<appointment>();
	
	static class appointment implements Serializable {

		private String createdate;
		private String address;
		private String person;
		
		private appointment(String createdate, String address, String person) {
			this.createdate = createdate;
			this.address = address;
			this.person = person;
		}

		public String getCreatedate() {
			return createdate;
		}

		public void setCreatedate(String createdate) {
			this.createdate = createdate;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPerson() {
			return person;
		}

		public void setPerson(String person) {
			this.person = person;
		}
	}
	
	private static void saveFile() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("To Do List.dat"));
		oos.writeObject(v);
		oos.close();
	}
	
	public static void appointmentMenu() throws Exception {
		// 프로그램 실행될때마다 파일 있는지 확인
		// 확인해서 파일 없으면 새로 만들어서 빈 백터 넣기
		// 확인해서 파일 있으면 파일 내용 새 백터에 읽어오기 
		
		File f = new File("To Do List.dat");
		if (!f.exists()) { // 파일 없으면
			f.createNewFile();
		} else { // 파일 있으면
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			v = (Vector<appointment>)ois.readObject();
			ois.close();
		}
		
		PersonalInfo pInfo = new PersonalInfo();
		System.out.println("\n<Appointment Menu>");
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
		
		System.out.print("Enter the address: ");
		String address = scan.next();
		
		System.out.print("Enter the person: ");
		String person = scan.next();
		
		v.add(new appointment(createdate, address, person));
		
	}
	
	public static void viewAppointment() throws Exception{
		
		System.out.println(" ");
		for (int i = 0; i < v.size(); i++) {
			System.out.println("---------- " + (i + 1) + " ----------");
			System.out.println("Create Date: " + v.get(i).createdate);
			System.out.println("Address: " + v.get(i).address);
			System.out.println("Person: " + v.get(i).person);
			System.out.println("------------------------");
		}
	}
	
	//updadte 수정
	public static void updateAppointment() throws Exception{
		viewAppointment();
		System.out.print("\nEnter To Do List Number you want to update: ");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		System.out.print("Enter updated date(YYMMDD): ");
		String createdate = scan.next();
		
		System.out.print("Enter updated Address: ");
		String address = scan.next();
		
		System.out.print("Enter updated Person: ");
		String person = scan.next();
		
		v.get(n).createdate = createdate;
		v.get(n).address = address;
		v.get(n).person = person;
		
		System.out.println("Complete!");

		
	}
	public static void deleteAppointment() throws Exception{
		viewAppointment();
		System.out.print("\nEnter Appointment Number you want to delete: ");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		v.remove(n - 1);
		System.out.println("Complete!");
	}


}
