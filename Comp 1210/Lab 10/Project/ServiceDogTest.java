import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
* Tests the ServiceDog object.
* Project 09.
*
* @author Kareith Dyce
* @version 11/02/16
*/
public class ServiceDogTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** Tests service dog. **/
   @Test public void serviceTest() 
   {
      ServiceDog d2 = new ServiceDog("Jen Baker", "Pepper", "Sheppard", 60, 7,
         "guide dog",
         "sit", "down", "stay", "come", "around",
         "forward", "right", "left");
      Assert.assertEquals("guide dog", d2.getService());
      d2.setService("fire dog");
      Assert.assertEquals("fire dog", d2.getService());  
   }
   
   /**
   *Tests setCommands and getCommands.
   */
  
   @Test public void commandsTest() 
   {
      ServiceDog d2 = new ServiceDog("Jen Baker", "Pepper", "Sheppard", 60, 7,
         "guide dog",
         "sit", "down", "stay", "come", "around",
         "forward", "right", "left");
      Assert.assertEquals("sit", d2.getCommands()[0]);
      Assert.assertEquals("down", d2.getCommands()[1]);
      Assert.assertEquals("stay", d2.getCommands()[2]);
      Assert.assertEquals("come", d2.getCommands()[3]);
      Assert.assertEquals("around", d2.getCommands()[4]);
      Assert.assertEquals("forward", d2.getCommands()[5]);
      Assert.assertEquals("right", d2.getCommands()[6]);
      Assert.assertEquals("left", d2.getCommands()[7]);
      d2.setCommands("run", "jump");
      Assert.assertEquals("run", d2.getCommands()[0]);
      Assert.assertEquals("jump", d2.getCommands()[1]);  
   }
  /**
  *Tests boardingCost() and toString().
  */
   @Test public void boardingCostTest()
   {
      ServiceDog d2 = new ServiceDog("Jen Baker", "Pepper", "Sheppard", 60, 7,
         "guide dog",
         "sit", "down", "stay", "come", "around",
         "forward", "right", "left");
      Assert.assertEquals(168.00, d2.boardingCost(), 0.1);
      Assert.assertEquals("Owner: Jen Baker   Pet: Pepper   Days: 7" 
                        + "   Boarding Cost: $168.00\nServiceDog: Sheppard"
                        + "   Weight: 60.0 lbs   Service: guide dog\n"
                        + "Commands: sit down stay come around forward right"
                        + " left", d2.toString());
         
   }
  
}
