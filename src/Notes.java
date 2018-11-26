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
		// 프로그램 실행될때마다 파일 있는지 확인
		// 확인해서 파일 없으면 새로 만들어서 빈 백터 넣기
		// 확인해서 파일 있으면 파일 내용 새 백터에 읽어오기

		File f = new File("Notes.dat");
		if (!f.exists()) { // 파일 없으면
			f.createNewFile();
		} else { // 파일 있으면
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

		// 현재 날짜 받아오기
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

	// 수정해야함
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

		// 현재 날짜 받아오기
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
