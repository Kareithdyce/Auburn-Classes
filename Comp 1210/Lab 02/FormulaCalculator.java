import java.util.Scanner;
/**
*
*A program is needed that inputs values for x, y,
*and z and calculates a result for the indicated formula. 
*If the product of x, y, and z is zero, the result is zero.
*@author Kareith Dyce
*@version 8/30/16
*
*/
public class FormulaCalculator
{

/**
*
*A program is needed that inputs values for x, y,
 *and z and calculates a result for the indicated formula. 
*If the product of x, y, and z is zero, the result is zero.
*@param args Command line arguments (not used).
*
*/

   public static void main(String[] args)
   {  
      Scanner userInput = new Scanner(System.in);
      
      double x;
      double y;
      double z;
      double result;
      
      System.out.println("result = (3x + 10.5) (5y - 6.2) (7z + 1.5) / xyz");
      
      System.out.print("\tEnter x: ");
      x = userInput.nextDouble(); 
      
      System.out.print("\tEnter y: ");
      y = userInput.nextDouble();
      
      System.out.print("\tEnter z: ");
      z = userInput.nextDouble();
   
      if (x * y * z == 0)
      {
         result = 0;
      }
      else
      {
         result = (3 * x + 10.5) * (5 * y - 6.2) * (7 * z + 1.5) / (x * y * z);
      }
      System.out.println("result = " + result);
   }
}