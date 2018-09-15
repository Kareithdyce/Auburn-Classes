import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void kmaxTest() {
      int [] a = {-4,-4};
      int k = 1; 
      int actual = -4;
      int expected = Selector.kmax(a, k);
      Assert.assertEquals(actual, expected);  
     
   }
   @Test public void kminTest1() {
      int [] a = {-3, -7, -3, -3, -1, -9, -1, -1, -1, -5};
      int k = 1; 
      int actual = -9;
      int expected = Selector.kmin(a, k);
      Assert.assertEquals(actual, expected);  
     
   }

}
