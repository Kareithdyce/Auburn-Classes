import java.util.Scanner;
import java.util.ArrayList;

public class TemperatureInfo
{
   public static void main(String [] args)
   {
      ArrayList<Integer> tempsList = new ArrayList<Integer>();
      Scanner in = new Scanner(System.in); 
      String input = "";
      do{
         System.out.print("Enter a temperature (or nothing to end list): ");
         input = in.nextLine().trim();
         if(!input.equals(""))
         {
            tempsList.add(Integer.parseInt(input));
         }   
      }
      while(!input.equals(""));
      
      Temperatures temps = new Temperatures(tempsList);
        
      char choice = 'E';
      do{
         System.out.print("Enter choice - [L]ow temp, [H]igh temp, [P]rint, [E]nd: ");
         choice = in.nextLine().toUpperCase().charAt(0);
         switch(choice){
            case 'L': 
               System.out.println("\tLow is " + temps.getLowTemp());
               break;
            case 'H': 
               System.out.println("\tHigh is " + temps.getHighTemp());
               break;
            case 'P': 
               System.out.println(temps);
               break;
            case 'E': 
               System.out.println("\tDone");
               break;
            default: 
               System.out.println("\tInvalid choice!");
               break;
         
         
              
         }
      }while(choice != 'E');
   }
}