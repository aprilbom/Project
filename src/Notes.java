import java.util.*;
import java.io.*;
import java.text.*;

public class Notes {
	static Vector<notes> v = new Vector<notes>();

	static class notes implements Serializable {
		private String title;
		private String contents;
		private String createdate;

		private notes(String title, String contents, String createdate) {
			this.title = title;
			this.contents = contents;
			this.createdate = createdate;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContents() {
			return contents;
		}

		public void setContents(String contents) {
			this.contents = contents;
		}

		public String getCreatedate() {
			return createdate;
		}

		public void setCreatedate(String createdate) {
			this.createdate = createdate;
		}
	}

	private static void saveFile() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Notes.dat"));
		oos.writeObject(v);
		oos.close();
	}

	public static void notesMenu() throws Exception {
		// ���α׷� ����ɶ����� ���� �ִ��� Ȯ��
		// Ȯ���ؼ� ���� ������ ���� ���� �� ���� �ֱ�
		// Ȯ���ؼ� ���� ������ ���� ���� �� ���Ϳ� �о����

		File f = new File("Notes.dat");
		if (!f.exists()) { // ���� ������
			f.createNewFile();
		} else { // ���� ������
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			v = (Vector<notes>) ois.readObject();
			ois.close();
		}

		PersonalInfo pInfo = new PersonalInfo();
		System.out.println("\n<1. Notes Menu>");
		pInfo.printDetailMenu();

		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();

		while (n != 5) {
			if (n == 1)
				createNote();
			else if (n == 2)
				viewNote();
			else if (n == 3)
				updateNote();
			else if (n == 4)
				deleteNote();
			else if (n == 5)
				break;

			saveFile();
			System.out.println("\n<2. Notes Menu>");
			pInfo.printDetailMenu();
			n = scan.nextInt();
		}
	}

	public static void createNote() throws Exception {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the Title: ");
		String title = scan.next();

		// ���� ��¥ �޾ƿ���
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		Calendar c1 = Calendar.getInstance();
		String createdate = format.format(c1.getTime());

		System.out.print("Enter the Contents: ");
		String contents = scan.next();

		v.add(new notes(title, contents, createdate));
	}

	public static void viewNote() throws Exception {
		System.out.println(" ");
		for (int i = 0; i < v.size(); i++) {
			System.out.println("---------- " + (i + 1) + " ----------");
			System.out.println("Title: " + v.get(i).title);
			System.out.println("Contents: " + v.get(i).contents);
			System.out.println("Create Date: " + v.get(i).createdate);
			System.out.println("------------------------");
		}
	}

	// �����ؾ���
	public static void deleteNote() throws Exception {
		viewNote();
		System.out.print("\nEnter Notes Number you want to delete: ");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		System.out.print("\nWant to delete?(Y/N): ");
		String reply = scan.next();

		if (reply.equals("Y")) {
			v.remove(n - 1);
			System.out.println("Complete!");
		}else 
			System.out.println("Canceled!");
	}

	public static void updateNote() throws Exception {
		viewNote();
		System.out.print("\nEnter Notes Number you want to update: ");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		System.out.print("Enter updated Title: ");
		String title = scan.next();

		// ���� ��¥ �޾ƿ���
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		Calendar c1 = Calendar.getInstance();
		String createdate = format.format(c1.getTime());

		System.out.print("Enter updated Contents: ");
		String contents = scan.next();

		v.get(n - 1).createdate = createdate;
		v.get(n - 1).title = title;
		v.get(n - 1).contents = contents;

		System.out.println("Complete!");
	}
}
