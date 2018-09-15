import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
/**
* Tests petNameComparator.
* Project 10
*
* @author Kareith Dyce
* @version 11/02/16
*/

public class PetBoardingPart2Test 
{


   /** Fixture initialization (common initialization
    * for all tests).
    */
   @Before public void setUp()
   {
   }


    /**
    * Checks empty String[].
    *
    * @throws IOException from scanning input file.
    */
   @Test public void emptyTest() throws IOException
   {
      PetBoardingList.resetListCount();
      PetBoardingPart2 app = new PetBoardingPart2();
      String[] args1 = {}; // an empty String[]
      PetBoardingPart2.main(args1);
      Assert.assertEquals("Pet Boarding List count should be 0. ",
         0, PetBoardingList.getListCount());
   }

   /** 
   *Checks filled String[] . 
   *
   * @throws IOException from scanning input file.
      */
   @Test public void filledTest() throws IOException
   {
      PetBoardingList.resetListCount();
      String[] args2 = {"pet_boarding_data.csv"};
      PetBoardingPart2.main(args2);
      Assert.assertEquals("Pet Boarding List count should be 1. ",
         1, PetBoardingList.getListCount());
   }
      


}
