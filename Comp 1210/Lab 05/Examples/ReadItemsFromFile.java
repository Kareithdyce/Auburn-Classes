import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

/**
 * Reads the lines of text from user; prints words in 
 * reverse order.
 */
public class ReadItemsFromFile
{
/**
 * Reads line of text from a file, store words in 
 * an ArrayList, print word in reverse order.
 
 * @param args Command line arguments (not used).
 */
   public static void main(String[] args) throws IOException     
   {
   //Scanner scan = new Scanner(System.in);
      String  line;
      ArrayList inputList = new ArrayList();         
      Scanner inFile = new Scanner(new File("testItems.dat"));
      
      while(inFile.hasNext())
      {   
         inputList.add(inFile.nextInt());
         inputList.add(inFile.nextDouble());
         inputList.add(inFile.nextLine());
      }
      inFile.close();
      
      System.out.println ("Input file \"testItems.dat\" has been read!");
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
