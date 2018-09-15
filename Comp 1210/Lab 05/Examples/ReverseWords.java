import java.util.Scanner;
import java.util.ArrayList;
/**
 * Reads the lines of text from user; prints words in 
 * reverse order.
 * @author J. Cross
 */
public class ReverseWords
{
/**
 * Reads words of text from user, store words in 
 * an ArrayList, print words in reverse order; 
 * repeat as appropriate.
 * @param args Standard command line arguments.
 */
   public static void main(String[] args)
   {
      Scanner scan = new Scanner(System.in); 
      String  input;
      ArrayList<String> inputList;
      boolean getData = true;
      while (getData) {
         System.out.print("Enter a line of text: ");
         input = scan.nextLine();
      
      // Break line into words using a second Scanner
         Scanner lineScan = new Scanner(input);
         inputList = new ArrayList<String>();
         while (lineScan.hasNext()) {
            inputList.add(lineScan.next());
         }
         System.out.println(inputList);
         
      // Print in order
         int index = 0;
         while (index < inputList.size()) {
            System.out.print(inputList.get(index) + " ");
            index++;
         }
         
      // Print in reverse order
         System.out.print("\n");
         index = inputList.size() - 1;
         while (index >= 0) {
            System.out.print(inputList.get(index) + " ");
            index--;
         }
      
      // More data?
         System.out.print("\n\nEnter more data? (Y/N): ");
         String moreData = scan.nextLine();
         getData = moreData.equalsIgnoreCase("Y");
      }
      System.out.println("\nDone");
   }
}
