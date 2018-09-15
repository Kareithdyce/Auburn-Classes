import java.io.IOException;

/**
* Holds the file and prints out the reports. 
* Project 10
*
* @author Kareith Dyce
* @version 11/02/16
*/
public class PetBoardingPart2
{
/**
* Holds the file and prints out the reports. 
*
* @param args - holds the file;
* @throws IOException from scanning input file.
*/
   
   public static void main(String [] args) throws IOException
   {
      
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
      if (args.length == 0)
      {
         System.out.println("File name expected as command line argument."
            + "\nProgram ending.");
      }
      
   }
}