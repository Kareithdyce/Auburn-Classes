import java.io.IOException;
/**
* Holds the file and prints out the reports. 
* Project 10
*
* @author Kareith Dyce
* @version 11/02/16
*/
public class PetBoardingPart3
{

/**
* Holds the file and prints out the reports. 
*
* @param args - holds the file;
*/
   public static void main(String [] args)
   {
      try {
         if (args.length != 0)
         {
            PetBoardingList pList = new PetBoardingList();
            pList.readPetFile(args[0]);
            System.out.println(pList.generateReport());
            System.out.println(pList.generateReportByOwner());
            System.out.println(pList.generateReportByPetName());
            System.out.println(pList.generateReportByBoardingCost());
            System.out.println(pList.generateExcludedRecordsReport());
         
         } 
         else {
            System.out.println("File name expected as command line argument."
               + "\nProgram ending.");
         }
         
      }
      catch (IOException e)
      {
         System.out.print("Attempted to read file: " + args[0] 
            + " (No such file or directory)\nProgram ending."); 
      } 
       
   }
}