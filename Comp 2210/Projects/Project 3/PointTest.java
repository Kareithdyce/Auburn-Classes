import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PointTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   @Test public void compareSlopeTest1() {
      Point a = new Point(0,0);
      Point b = new Point(5,5);
      Point c = new Point(4,3);
      int actual = a.slopeOrder.compare(b, c);
      assertEquals(1, actual);
   }
   
   @Test public void compareSlopeTest2() {
      Point a = new Point(0,0);
      Point b = new Point(5,5);
      Point c = new Point(4,3);
      int actual = a.slopeOrder.compare(c, b);
      assertEquals(-1, actual);
   }
   
   @Test public void compareSlopeTest3() {
      Point a = new Point(0,0);
      Point b = new Point(5,5);
      Point c = new Point(5,5);
      int actual = a.slopeOrder.compare(b, c);
      assertEquals(0, actual);
   }
   @Test public void compareSlopeTest4() {
      Point a = new Point(7,7);
      Point b = new Point(7,7);
      Point c = new Point(9,10);
      int actual = a.slopeOrder.compare(b, c);
      assertEquals(-1, actual);
   
   }
   @Test public void compareSlopeTest5() {
      Point a = new Point(2,2);
      Point b = new Point(7,2);
      Point c = new Point(5,4);
      int actual = a.slopeOrder.compare(b, c);
      assertEquals(0, actual);
   }
}
