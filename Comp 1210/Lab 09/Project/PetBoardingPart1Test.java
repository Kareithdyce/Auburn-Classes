import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
* Tests the driver for pet.
* Project 09.
*
* @author Kareith Dyce
* @version 11/02/16
*/


public class PetBoardingPart1Test {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** Test count. **/
   @Test public void countTest() {
      Pet.resetCount();
      PetBoardingPart1 p = new PetBoardingPart1();
      PetBoardingPart1.main(null);
      Assert.assertEquals("Pet.count should be 4. ",
         4, Pet.getCount());
   }
}
