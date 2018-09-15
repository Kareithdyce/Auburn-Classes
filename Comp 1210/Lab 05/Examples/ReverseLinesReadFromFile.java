import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;   // required for input file
import java.io.IOException;  // required for "throws" in main header

/**
 * Example of reading lines from a file.
 * Reads the lines of text from file.
 * Prints lines in reverse order.
 * @author J. Cross
 */
public class ReverseLinesReadFromFile
{
/**
 * Reads file name from user.
 * Reads line of text from file; store lines in 
 * an ArrayList; print lines in reverse order; 
 * repeat as appropriate.
 * @param args Standard command line arguments.
 */
   public static void main(String[] args) throws IOException // throws required
   {
      Scanner scan = new Scanner(System.in);
      String  input;
      ArrayList<String> inputList;
      boolean getData = true;
      while (getData) {
         System.out.print("\nEnter file name: ");
         input = scan.nextLine();
         if (input.trim().length() == 0) {
            break;  // no file name entered
         }
      
      // Open Scanner on input file
         Scanner fileScan = new Scanner(new File(input)); 
         inputList = new ArrayList<String>();
      // Read each line from file and store in ArrayList
         while (fileScan.hasNext()) {
            inputList.add(fileScan.nextLine());
         }
         fileScan.close();  // close input file
         
      // Print the ArrayList   
         System.out.println(inputList + "\n");
         
      // Print lines in order
         int index = 0;
         while (index < inputList.size()) {
            System.out.println(inputList.get(index) + " ");
            index++;
         }
         
      // Print lines in reverse order
         System.out.print("\n");
         index = inputList.size() - 1;
         while (index >= 0) {
            System.out.println(inputList.get(index) + " ");
            index--;
         }
      
      }
      System.out.println("\nDone");
   }
}
