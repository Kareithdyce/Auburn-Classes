import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MinOfThreeTest {




   /** all numbers are different **/
   @Test public void min1Search1Test() {
      int actual = MinOfThree.min1(1,2,3);
      int expected = 1;
   
      assertEquals(expected, actual);
      
   }
   
   /** a and b are equal c is less **/
   @Test public void min1Search2Test() {
      int actual = MinOfThree.min1(2,2,1);
      int expected = 1;
   
      assertEquals(expected, actual);
   }
   
   /** a and b are equal c is greater **/
   @Test public void min1Search3Test() {
      int actual = MinOfThree.min1(1,1,2);
      int expected = 1;
   
      assertEquals(expected, actual);
   }
   
      /** a and c are equal b is less **/
   @Test public void min1Search4Test() {
      int actual = MinOfThree.min1(2,1,2);
      int expected = 1;
   
      assertEquals(expected, actual);
   }
   
   /** a and c are equal b is greater **/
   @Test public void min1Search5Test() {
      int actual = MinOfThree.min1(1,2,1);
      int expected = 1;
   
      assertEquals(expected, actual);
   }
   
/** b and c are equal a is greater **/
   @Test public void min1Search6Test() {
      int actual = MinOfThree.min1(2,1,1);
      int expected = 1;
   
      assertEquals(expected, actual);
   }
   
   /** b and c are equal a is less **/
   @Test public void min1Search7Test() {
      int actual = MinOfThree.min1(1,2,2);
      int expected = 1;
   
      assertEquals(expected, actual);
   }

     /** all numbers are different **/
   @Test public void min2Search1Test() {
      int actual = MinOfThree.min2(1,2,3);
      int expected = 1;
   
      assertEquals(expected, actual);
      
   }
   
   /** a and b are equal c is less **/
   @Test public void min2Search2Test() {
      int actual = MinOfThree.min2(2,2,1);
      int expected = 1;
   
      assertEquals(expected, actual);
   }
   
   /** a and b are equal c is greater **/
   @Test public void min2Search3Test() {
      int actual = MinOfThree.min2(1,1,2);
      int expected = 1;
   
      assertEquals(expected, actual);
   }
   
      /** a and c are equal b is less **/
   @Test public void min2Search4Test() {
      int actual = MinOfThree.min2(2,1,2);
      int expected = 1;
   
      assertEquals(expected, actual);
   }
   
   /** a and c are equal b is greater **/
   @Test public void min2Search5Test() {
      int actual = MinOfThree.min2(1,2,1);
      int expected = 1;
   
      assertEquals(expected, actual);
   }
   
/** b and c are equal a is greater **/
   @Test public void min2Search6Test() {
      int actual = MinOfThree.min2(2,1,1);
      int expected = 1;
   
      assertEquals(expected, actual);
   }
   
   /** b and c are equal a is less **/
   @Test public void min2Search7Test() {
      int actual = MinOfThree.min2(1,2,2);
      int expected = 1;
   
      assertEquals(expected, actual);
   }

   
}
