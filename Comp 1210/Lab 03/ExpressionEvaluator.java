import java.util.Scanner;
import java.text.DecimalFormat;
/**
*
*Calculate the following expression for any value 
*of the variable x, and save the result in a variable
*of the type double.
*@author Kareith Dyce
*@version 9/6/16
*
*/


public class ExpressionEvaluator
{
   /**
   *
   *@param args Command line arguments (not used).
   *
   */

   public static void main(String [] args)
   {
      double x, square, powers, result;
      
      int index, length, leftOf, rightOf;
      
      String resultString; 
      String tempL; //for leftOf
      String tempR; //for righthOf
      
      DecimalFormat df = new DecimalFormat("#,###,##0.0###");
      Scanner userInput = new Scanner(System.in);
      
      System.out.print("Enter a value for x: ");
      x = userInput.nextDouble();
      
      powers = 5 * Math.pow(x, 7) - 4 * Math.pow(x, 6);
      square = Math.sqrt(Math.abs(3 * Math.pow(x, 5)));
      result = Math.sqrt(Math.pow(powers, 2) + square);
      
      index = Double.toString(result).indexOf(".");
      resultString = Double.toString(result);
      tempL = resultString.substring(0, index);
      tempR = resultString.substring(index + 1);
      leftOf = tempL.length();
      rightOf = tempR.length();
         
      System.out.print("Result: " + result
         + "\n# digits to left of decimal point: " + leftOf
         + "\n# digits to right of decimal point: " + rightOf 
         + "\nFormatted Result: " + df.format(result));
   }
}