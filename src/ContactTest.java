import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class ContactTest {

   @Test
   void test() {
      Contacts ct=new Contacts();
      if(!ct.v.isEmpty())
      assertTrue(ct.compareName(0,0)==true);
   }
   
   @Test
   void testchecktoDelete() {
      Contacts ct=new Contacts();
      assertTrue(ct.checktoDelete("y"));
   }
}