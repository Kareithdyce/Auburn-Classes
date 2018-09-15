import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
* Tests BoardingCostComparator.
* Project 10
*
* @author Kareith Dyce
* @version 11/02/16
*/



public class BoardingCostComparatorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** Tests the BoardingCostComparator. **/
   @Test public void compareTest() {
      Pet [] pList = new Pet [4];
      pList[0] = new Cat("Barb Jones", "Callie", "Siamese", 9.0, 7, 9);
      pList[1] = new Dog("Jake Smith", "Honey", "Great Dane", 60, 7);
      pList[2] = new Dog("Jen Baker", "Pepper", "Sheppard", 60, 7); 
      pList[3] = new Horse("Jessie Rider", "King", "Quarter Horse", 
         1000, 7, 10.0);
      
      BoardingCostComparator b = new BoardingCostComparator();
      Assert.assertEquals(1, b.compare(pList[0], pList[1]), 0);
      Assert.assertEquals(-1, b.compare(pList[3], pList[1]), 0);
      Assert.assertEquals(0, b.compare(pList[2], pList[1]), 0);
   }
}
