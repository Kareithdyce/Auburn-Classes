//********************************************************************
//  CountInfinite.java       
//********************************************************************

/**
 * Demonstrates an infinite loop. 
 */
public class CountInfinite
{
/**
* Demonstrates the range of infinite while loop. 
* Change type of count from int to byte to see
* the program halt due to underflow.
* @param args Standard command line arguments.
*/
   public static void main(String[] args)
   {
      byte count = 1; // change int to byte to see underflow
      
      while (count <= 25) 
      {
         System.out.println(count);
         count--;
      }
      System.out.println(count);
      System.out.println("Done.");
   } 
}
