      import java.util.Scanner;
/**
* Create ConeApp class with a main method that reads in values for 
* label, height, and radius.
* Project 04
*
* @author Kareith Dyce
* @version 9/13/16
*
*/
public class ConeApp
{
   /**
   *
   *@param args Command line arguments (not used).
   *
   */
   public static void main(String [] args)
   {
      String label;
      double height;
      double radius;
      
      Scanner userInput = new Scanner(System.in);
      
      System.out.print("Enter label, height, and radius for a cone."
         + "\n\tlabel: ");
      label = userInput.nextLine();
      
      System.out.print("\theight: ");   
      height = userInput.nextDouble();
      
      if (height <= 0)
      {
         System.out.print("Error: height must be greater than 0.");
         return;
      }
      
      System.out.print("\tradius: ");
      radius = userInput.nextDouble();
      
      if (radius <= 0)
      {
         System.out.print("Error: radius must be greater than 0.");
         return;
      }
   
      Cone c1 = new Cone(label, height, radius); 
      System.out.print(c1);
   }  
}