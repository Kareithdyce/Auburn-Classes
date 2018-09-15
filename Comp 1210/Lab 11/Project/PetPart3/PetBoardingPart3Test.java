import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
* Tests Pet Boarding Part3.
* Project 10
*
* @author Kareith Dyce
* @version 11/02/16
*/

public class PetBoardingPart3Test 
{


   /** Fixture initialization (common initialization
    * for all tests).
    */
   @Before public void setUp()
   {
   }


    /**
    * Checks empty String[].
    */
   @Test public void emptyTest() 
   {
      PetBoardingList.resetListCount();
      PetBoardingPart3 app = new PetBoardingPart3();
      String[] args1 = {}; // an empty String[]
      PetBoardingPart3.main(args1);
      Assert.assertEquals("Pet Boarding List count should be 0. ",
         0, PetBoardingList.getListCount());
   }

   /** 
   *Checks filled String[] . 
   */
   @Test public void filledTest() 
   {
      PetBoardingList.resetListCount();
      String[] args2 = {"pet_boarding_data.csv"};
      PetBoardingPart3.main(args2);
      Assert.assertEquals("Pet Boarding List count should be 1. ",
         1, PetBoardingList.getListCount());
   }
   

}
