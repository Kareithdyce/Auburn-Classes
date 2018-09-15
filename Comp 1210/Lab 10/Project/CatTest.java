import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
* Test the cat object.
* Project 09.
*
* @author Kareith Dyce
* @version 11/02/16
*/

public class CatTest {


   /**    Fixture initialization (common initialization
    *  for all tests). **/

   @Before public void setUp() {
   }


   /** Tests setLives and getLives. **/
   @Test public void livesLeftTest()
   {
      Cat c = new Cat("Barb Jones", "Callie", "Siamese", 9.0, 7, 9);
      Assert.assertEquals(9, c.getLivesLeft(), 0);
      c.setLivesLeft(8);
      Assert.assertEquals(8, c.getLivesLeft(), 0);
   }
    
    /** Tests getOwner(). */
   @Test public void getOwner()
   {
      Cat c = new Cat("Barb Jones", "Callie", "Siamese", 9.0, 7, 9);
      Assert.assertEquals("Barb Jones", c.getOwner());
   }
   
   
    /** Tests getName(). */
   @Test public void getOwnerTest()
   {
      Cat c = new Cat("Barb Jones", "Callie", "Siamese", 9.0, 7, 9);
      Assert.assertEquals("Callie", c.getName());
   }


   /** Tests getBreed(). */
   @Test public void getBreedTest()
   {
      Cat c = new Cat("Barb Jones", "Callie", "Siamese", 9.0, 7, 9);
      Assert.assertEquals("Siamese", c.getBreed());
   }   
   
   /** Tests getWeight(). */
   @Test public void getWeightTest()
   {
      Cat c = new Cat("Barb Jones", "Callie", "Siamese", 9.0, 7, 9);
      Assert.assertEquals(9.0, c.getWeight(), 0.1);
   }
   
   
   /** Tests getDays(). */
   @Test public void getDaysTest()
   {
      Cat c = new Cat("Barb Jones", "Callie", "Siamese", 9.0, 7, 9);
      Assert.assertEquals(7, c.getDays(), 0.1);
   }   
   
   /** Tests getCount and resetCount. */
   @Test public void countTest()
   {
      Pet.resetCount();
      Assert.assertEquals(0, Pet.getCount(), 0);
      Cat c = new Cat("Barb Jones", "Callie", "Siamese", 9.0, 7, 9);
      Assert.assertEquals(1, Pet.getCount(), 0);
   }   
    
   /** Tests boardingCostTest() and the toString(). **/ 
   @Test public void boardingCostTest()
   {
      Cat c = new Cat("Barb Jones", "Callie", "Siamese", 9.0, 7, 9);
      Assert.assertEquals(76.30, c.boardingCost(), 0.1);
      Assert.assertEquals("Owner: Barb Jones   Pet: Callie   Days: 7" 
                          + "   Boarding Cost: $76.30\nCat: Siamese"
                          + "   Weight: 9.0 lbs   Lives Left: 9",
                          c.toString());    
   }
   
      
}
