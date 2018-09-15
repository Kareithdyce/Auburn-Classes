import java.util.Scanner;
/**
*
*Converts ounces to tons and pounds that do not exceed 500 million  
*ounces.
*@author Kareith Dyce
*@version 8/30/16
*
*/
public class WeightConversion
{
   
   /**
   *
   *Converts ounces to tons and pounds that do not exceed 500 million  
   *ounces.
   *@param args Command line arguments (not used).
   *
   */
   
   public static void main(String[] args)
   {  
      int weight = 0;
      int tons = 0; 
      int pounds = 0;
      int ounces = 0;
      int temp = 0;
      
      Scanner userInput = new Scanner(System.in);
      
      System.out.print("Enter weight in ounces: ");
      weight = userInput.nextInt();
      
      if (weight > 500000000)
      {
         System.out.print("Limit of 500,000,000 ounces exceeded!"); 
      }
      else
      {
         tons = weight / 32000;
         temp = weight % 32000;
         
         pounds = temp / 16;
         ounces = temp % 16;
         
         System.out.println("Combined Number of Tons, Pounds, and Ounces: "
                         + "\n\tTons: " + tons + "\n\tPounds: " + pounds 
                         + "\n\tOunces: " + ounces);
         
         System.out.println(weight +  " ounces = " + tons + " tons + " + pounds
                         + " pounds + " + ounces + " ounces");
      }
   }   
}