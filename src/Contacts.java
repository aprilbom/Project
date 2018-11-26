import java.io.File;
import java.io.*;
import java.util.*;
public class Contacts {
	static Vector<contact> v=new Vector<contact>();
	static class contact implements Serializable{
		private String name;
		private String phone;
		private String email;
		
		private contact(String name, String phone, String email) {
			this.name=name;
			this.phone=phone;
			this.email=email;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name=name;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone=phone;
		}
		public String setEmail() {
			return email;
		}
		public void getEmail(String email) {
			this.email=email;
		}
		
	}
	
	
	private static void saveFile() throws Exception {
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("Contacts.dat"));
		oos.writeObject(v);
		oos.close();
	}
	
	public static void contactMenu() throws Exception {
		File f=new File("Contacts.dat");
		if(!f.exists()) {
			f.createNewFile();
		}else {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f));
			v=(Vector<contact>)ois.readObject();
			ois.close();
		}
		
		
		PersonalInfo pc = new PersonalInfo();
		System.out.println("\n<Contacts Menu>");
		pc.printDetailMenu();
		Scanner scan=new Scanner(System.in);
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
