import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
* Tests the Horse object.
* Project 09.
*
* @author Kareith Dyce
* @version 11/02/16
*/

public class HorseTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   /** Tests getExerciseFee() and setExerciseFee().*/
   @Test public void livesLeftTest()
   {
      Horse h = new Horse("Jessie Rider", "King", "Quarter Horse", 
         1000, 7, 10.0);
      Assert.assertEquals(10, h.getExerciseFee(), 0);
      h.setExerciseFee(8);
      Assert.assertEquals(8, h.getExerciseFee(), 0);
   }

   
   /** Tests boardingCostTest() and the toString(). **/
   @Test public void boardingCostTest()
   {
      Horse h = new Horse("Jessie Rider", "King", "Quarter Horse", 
         1000, 7, 10.0);
      Assert.assertEquals(245.00, h.boardingCost(), 0.1);
      Assert.assertEquals("Owner: Jessie Rider   Pet: King   Days: 7" 
                          + "   Boarding Cost: $245.00\nHorse: Quarter Horse"
                          + "   Weight: 1000.0 lbs   Exercise Fee: $10.00", 
                          h.toString());
         
   }
}

