import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.text.*;

class ToDoTest {
	
	
	@Test
	static void viewtest() throws Exception{
		ToDoList todotest = new ToDoList();
		boolean check = todotest.viewToDoList();
		assertTrue(check);
	}
	
	@Test
	static void checkreturn() throws Exception {
		ToDoList todotest = new ToDoList();
		assertNotNull(todotest.saveFile());
	}
	
	

}