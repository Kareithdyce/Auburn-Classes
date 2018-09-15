import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
* Tests petNameComparator.
* Project 10
*
* @author Kareith Dyce
* @version 11/02/16
*/


public class PetNameComparatorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** Tests the PetNameComparator. **/
   @Test public void compareTest() {
      Pet [] pList = new Pet [4];
      pList[0] = new Cat("Barb Jones", "Callie", "Siamese", 9.0, 7, 9);
      pList[1] = new Dog("Jake Smith", "Pepper", "Great Dane", 60, 7);
      pList[2] = new ServiceDog("Jen Baker", "Pepper", "Sheppard", 60, 7,
         "guide dog",
         "sit", "down", "stay", "come", "around",
         "forward", "right", "left");
       
      pList[3] = new Horse("Jessie Rider", "King", "Quarter Horse", 
         1000, 7, 10.0);
      
      PetNameComparator p = new PetNameComparator();
      
      Assert.assertEquals(-1, p.compare(pList[0], pList[1]), 0);
      Assert.assertEquals(1, p.compare(pList[2], pList[3]), 0);
      Assert.assertEquals(0, p.compare(pList[1], pList[2]),  0);
   
   }
}
