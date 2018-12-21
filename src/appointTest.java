import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class appointTest {
	
	@Test
	void testFileExist() {
		Appointments appTest = new Appointments();
		assertTrue(appTest.fileNotExist());
	}
	
	@Test
	void testvEmpty() {
		Appointments appTest = new Appointments();
		assertTrue(appTest.vEmpty());
	}	
}