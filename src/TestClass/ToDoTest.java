import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Vector;

import org.junit.jupiter.api.*;

import ToDoList.todo;

class ToDoTest {
	@BeforeAll
	static void initAll() {
		ToDoList todotest = new ToDoList();
	}
	
	@Test
	static void viewtest() {
		assertTrue(todotest.viewToDoList());
	}
	
	@Test
	static void test() {
		static Vector<todo> v = new Vector<todo>();
		static Vector<todo> v2 = new Vector<todo>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		v = (Vector<todo>)ois.readObject();
		ois.close();
		v2 = todotest.saveFile();
		assertEquals(v, v2);
	}
	
	

}
