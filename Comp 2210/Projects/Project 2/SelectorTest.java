import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class SelectorTest {
   static Comparator<Integer> ascendingInteger =
         new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
               return i1.compareTo(i2);
            }
         };


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
      
       
      Collection<Integer> c1 = Arrays.<Integer>asList(new Integer[]{-4,-4});
      int k = 2;
      int low = 0;
      int high = 0;
      Comparator comp = ascendingInteger;
      //int[] wrong = {7}; 
      try {
         int actual = Selector.kmin(c1, k, comp);
      }
      catch(NoSuchElementException e) { 
         Assert.assertTrue(true);
      }   
   
   }
   @Test public void defaultTest2() {
      
       
      Collection<Integer> c1 = Arrays.<Integer>asList(new Integer[]{3,7,3,3,1,9,1,1,1,5});
      int k = 2;
      int low = 0;
      int high = 0;
      Comparator comp = ascendingInteger;
      //int[] wrong = {7}; 
      int actual = Selector.kmin(c1, k, comp);
      Assert.assertEquals(actual, 3);    
   
   }
   @Test public void testKMax_4() {
      
       
      Collection<Integer> c1 = Arrays.<Integer>asList(new Integer[]{-4,-4});
      int k = 2;
      int low = 0;
      int high = 0;
      Comparator comp = ascendingInteger;
      //int[] wrong = {7}; 
      try {
         int actual = Selector.kmax(c1, k, comp);
      }
      catch(NoSuchElementException e) { 
         Assert.assertTrue(true);
      }
   }
   
   @Test public void testKMax_5() {
      
       
      Collection<Integer> c1 = Arrays.<Integer>asList(new Integer[]{3,7,3,3,1,9,1,1,1,5});
      int k = 5;
      int low = 0;
      int high = 0;
      Comparator comp = ascendingInteger;
      //int[] wrong = {7}; 
      int expected = Selector.kmax(c1, k, comp);
      Assert.assertEquals(expected, 1);    
   
   }
   
   @Test public void testKMin_5() {
      Collection<Integer> c1 = Arrays.<Integer>asList(new Integer[]{1,3,5,7,9});
      int k = 2;
      int low = 0;
      int high = 0;
      Comparator comp = ascendingInteger;
      //int[] wrong = {7}; 
      int expected = Selector.kmin(c1, k, comp);
      Assert.assertEquals(expected, 3);    
   
   }
   
   @Test public void testKMin_4() {
      Collection<Integer> c1 = Arrays.<Integer>asList(new Integer[]{5,7});
      int k = 2;
      int low = 0;
      int high = 0;
      Comparator comp = ascendingInteger;
      //int[] wrong = {7}; 
      int expected = Selector.kmin(c1, k, comp);
      Assert.assertEquals(expected, 7);    
   
   }


}
