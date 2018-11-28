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
			saveFile();
			System.out.println("\n<Contacts Menu>");
			pc.printDetailMenu();
			n = scan.nextInt();
		}
	}
	public static void createContact() throws Exception {
		//create contact
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the name: ");
		String name=scan.nextLine();
		
		System.out.print("Enter the phone number: ");
		String phone=scan.nextLine();
		
		System.out.print("Enter the email: ");
		String email=scan.nextLine();
		
		v.add(new contact(name,phone,email));
	}
	public static void viewContact() {
		//view contact
		System.out.println(" ");
		if (v.isEmpty())
			System.out.println("No Contact List Yet!");
		else {
			for(int i=0;i<v.size();i++) {
				System.out.println("---------- " + (i + 1) + " ----------");
				System.out.println("name: " + v.get(i).name);
				System.out.println("phone number: " + v.get(i).phone);
				System.out.println("email: " + v.get(i).email);
				System.out.println("------------------------");
			}
		}
	}

	public static void updateContact() {
		//update contact
		viewContact();
		System.out.print("\nEnter Contact name you want to update: ");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		System.out.print("Enter updated name: ");
		String name = scan.next();

		System.out.print("Enter updated phone: ");
		String phone = scan.next();
		
		System.out.print("Enter updated email: ");
		String email = scan.next();

		v.get(n - 1).name = name;
		v.get(n - 1).phone = phone;
		v.get(n - 1).email = email;

		System.out.println("Complete!");
	}
	public static void deleteContact() {
		//delete contact
		if (v.isEmpty())
			viewContact();
		else {
			viewContact();
			System.out.print("\nEnter Contact name you want to delete: ");
			Scanner scan = new Scanner(System.in);
			int n = scan.nextInt();
			
			System.out.print("Want to delete " + n + "? (y/n): ");
			String ch = scan.next();
			
			if (ch.equals("y")) {
				v.remove(n - 1);
				System.out.println("Complete!");
			} else 
				System.out.println("Not deleted!");

		}
	}
}
