import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.text.*;
import java.util.Calendar;

import org.junit.jupiter.api.*;

public class TestNotes {
	@Test
	void testSave() {
		Notes notes = new Notes();
		assertTrue(notes.saveFile());
	}
	
	@Test
	void testDate() {
		Notes notes = new Notes();
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		Calendar c1 = Calendar.getInstance();
		String createdate = format.format(c1.getTime());
		assertEquals(Notes.getDate(),createdate);
	}

}