import java.util.Scanner;
/**
*
*You will create a program that exchanges letters in a 
*String to encode a message. 
*@author Kareith Dyce
*@version 9/6/16
*
*/
public class MessageConverter
{
   /**
   *
   *@param args Command line arguments (not used).
   *
   */
   public static void main(String [] args)
   {
      Scanner userInput = new Scanner(System.in);
      String message = "";
      String result = "";
      int outputType;
      
      System.out.print("Type in a messagae and press enter: \n\t> ");
      message = userInput.nextLine();
      
      System.out.print("\nOutput types: "
         + "\n\t1: As is"
         + "\n\t2: lower case"  
         + "\n\t3: UPPER CASE"
         + "\n\t4: v_w_ls r_pl_c_d"
         + "\n\t5: Without first and last character"
         + "\nEnter your choice: ");
         
      outputType = Integer.parseInt(userInput.nextLine());
      
      
      
      if (outputType == 1)
      {     
         //as is
         result = message;
      }
      
      else if (outputType == 2)
      {     
         //lower case
         result = message.toLowerCase();
         
      }
      else if (outputType == 3)
      {     
         //uppercase
         result = message.toUpperCase();
          
      }
      else if (outputType == 4)
      {     
         //replace vowels
         result = message.replace('a', '_');
         result = message.replace('e', '_');
         result = message.replace('i', '_');
         result = message.replace('o', '_');
         result = message.replace('u', '_'); 
      }
      else if (outputType == 5)
      {     
         //without first and last character
         result = message.substring(1, message.length() - 1);
      } 
      else
      {
         //invalid input
         result = "Error: Invalid choice input.";
      }
      
      System.out.println("\n" + result);
   }
}