import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
* Reads in the name of the
   * input file entered by the user and then reads list name and Cone data from
   * the file, creates Cone objects, stores them in a local ArrayList, creates
   * an ConeList object with the name of the list and
   * the ArrayList of Cone objects, and then prints the ConeList object
   * followed summary information about the ConeList object.
*
* Project 05
*
* @author Kareith Dyce
* @version 9/26/16
*/

public class ConeListApp
{
   /**
   *
   *@param args - is not used.
       *@throws IOException from scanning input file
   */
   public static void main(String [] args) throws IOException
   {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter file name: ");
      String filename = in.nextLine();
      String label = "";
      double height = 0;
      double radius = 0;
      
      
      ArrayList<Cone> coneList = new ArrayList<Cone>();
   
      Scanner file = new Scanner(new File(filename));
                
      String name = file.nextLine(); 
      
      while (file.hasNext())
      { 
         label = file.nextLine();
         height = Double.parseDouble(file.nextLine());
         radius = Double.parseDouble(file.nextLine());
         
         Cone c = new Cone(label, height, radius);
         coneList.add(c);
      }   
      ConeList cList = new ConeList(name, coneList);
    
      System.out.println(cList);
      
      System.out.println(cList.summaryInfo());
        
         
   }
}
   
