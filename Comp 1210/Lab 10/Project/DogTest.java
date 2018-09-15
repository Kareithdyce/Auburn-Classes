import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
* Tests the Dog object.
* Project 09.
*
* @author Kareith Dyce
* @version 11/02/16
*/


public class DogTest {


   /**    Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   /** Tests boardingCostTest() and the toString(). **/
   @Test public void boardingCostTest()
   {
      Dog d = new Dog("Jake Smith", "Honey", "Great Dane", 60, 7);
      Assert.assertEquals(105.00, d.boardingCost(), 0.1);
      Assert.assertEquals("Owner: Jake Smith   Pet: Honey   Days: 7" 
                          + "   Boarding Cost: $105.00\nDog: Great Dane"
                          + "   Weight: 60.0 lbs", d.toString());
         
   }
}
