import java.io.IOException;
import java.util.Scanner;
import java.io.File;
public class help
{
   public static void main(String[] args)throws IOException 
   {
      Scanner read = new Scanner (new File("datafile.txt"));
      read.useDelimiter(",");
      String title, category, runningTime, year, price;
   
      while (read.hasNext())
      {
         title = read.next();
         category = read.next();
         runningTime = read.next();
         year = read.next();
         price = read.next();
         System.out.println(title + " " + category + " " + runningTime + " " +
                      year + " " + price + "\n"); // just for debugging
      }
      read.close();
   }
}