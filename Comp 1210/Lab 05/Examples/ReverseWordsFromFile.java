import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

/**
 * Reads the lines of text from user; prints words in 
 * reverse order.
 * @author J. Cross
 */
public class ReverseWordsFromFile
{
/**
 * Reads lines of text from a file, store words in 
 * an ArrayList, print word in reverse order.
 
 * @param args Command line arguments (not used).
 */
   public static void main(String[] args) throws IOException      {
   //Scanner scan = new Scanner(System.in);
      String  line;
      ArrayList<String> inputList = new ArrayList<String>();         
      Scanner inFile = new Scanner(new File("test.dat"));
      
      while(inFile.hasNext())
      {   
         line = inFile.nextLine();
         System.out.println(line);  
      
      // Break line into words
         Scanner lineScan = new Scanner(line);
         while (lineScan.hasNext()) {
            inputList.add(lineScan.next());
         }     
      }
      inFile.close();
      
      System.out.println ("Input file \"test.dat\" has been read!");
      System.out.println("The ArrayList contains: \n" + inputList);
   
   // Print in order
      System.out.println ("\nList of words from file: ");
      int index = 0;
      while (index < inputList.size()) {
         System.out.print(inputList.get(index) + " ");
         index++;
      }
         
   // Print in reverse order
      System.out.println ("\n\nList of words from file in reverse order: ");
      index = inputList.size() - 1;
      while (index >= 0) {
         System.out.print(inputList.get(index) + " ");
         index--;
      }
         
      System.out.println("\n\nDone");
   }
}
