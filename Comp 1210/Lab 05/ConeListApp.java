import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class ConeListApp
{
   public static void main(String [] args) throws IOException
   {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter file name: ");
      String filename = in.nextLine();
      
      Scanner file = new Scanner(new File(filename));
      ArrayList<Cone> coneList = new ArrayList<Cone>();
       
      String name = file.nextLine(); 
      while(file.hasNext())
      { 
         String label = file.nextLine();
         double height = file.nextDouble();
         double radius = file.nextDouble();
         Cone cone = new Cone(label, height, radius);
         coneList.add(cone);
      }   
         
        
         
   }
}
   
