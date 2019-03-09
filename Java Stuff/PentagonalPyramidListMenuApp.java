import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**.
*
* Project 6
* @author Evelyn Vaughn - COMP 1 - Section 007
* 2/27/2019
*/
   
public class PentagonalPyramidListMenuApp {

/**.
   * @throws FileNotFoundException used
   * @param args Command line arguments - not used
   */
   public static void main(String[] args) throws FileNotFoundException
   {
      ArrayList<PentagonalPyramid> myList =
         new ArrayList<PentagonalPyramid>();
      String pyramidListName = "";
   
      PentagonalPyramidList myPyramidList = 
         new PentagonalPyramidList(pyramidListName, myList);
      Scanner scan = new Scanner(System.in);
   
   //printing the action codes with short descriptions 
   
      System.out.println("PentagonalPyramid List System Menu");
      System.out.println("R - Read File and Create PentagonalPyramid List");
      System.out.println("P - Print PentagonalPyramid List");
      System.out.println("S - Print Summary");
      System.out.println("A - Add PentagonalPyramid");
      System.out.println("D - Delete PentagonalPyramid");
      System.out.println("F - Find PentagonalPyramid");
      System.out.println("E - Edit PentagonalPyramid");
      System.out.println("Q - Quit");
      
      char letterChar  = ' ';
      String letter = "";
      String label;
      String fileName;
      
      do { 
         System.out.print("Enter Code [R, P, S, A, D, F, E, or Q]: "); 
         letter = scan.nextLine().toUpperCase();
         letterChar = letter.charAt(0);
      
         switch (letterChar) {
            case 'R': 
               System.out.print("\tFile Name: ");
               fileName = scan.nextLine();
               myPyramidList = myPyramidList.readFile(fileName);
               System.out.println("\tFile read in and"
                  + " PentagonalPyramid List created");
               break;
         
            case 'P':
               System.out.print(myPyramidList);
               break;
         
            case 'S': 
               fileName = scan.nextLine();
               myPyramidList.readFile(fileName);
               System.out.println("");
               System.out.println(myPyramidList.summaryInfo());
               break; 
         
            case 'A':
               System.out.println("\tLabel: ");
               label = scan.nextLine();
               System.out.println("\tBase Edge: ");
               double baseEdge = Double.parseDouble(scan.nextLine()); 
               System.out.println("\tHeight: "); 
               double height = Double.parseDouble(scan.nextLine());  
               myPyramidList.addPentagonalPyramid(label, baseEdge, height); 
               System.out.println("*** PentagonalPyramid added ***");
               break;
                 
               
            case 'D': 
               System.out.println("\tLabel: "); 
               label = scan.nextLine();
               PentagonalPyramid p = 
                  myPyramidList.deletePentagonalPyramid(label); 
               if (p == null) {
                  System.out.print("\"" + label + "\" not found");
               }
               else {
                  System.out.print("\"" + label
                     + "\" deleted");
               }
               break;
               
            case 'F':
               System.out.println("\tLabel: ");
               label = scan.nextLine();
               PentagonalPyramid enter1 = 
                  myPyramidList.findPentagonalPyramid(label);
               if (enter1 == null) {
                  System.out.print("\"" + enter1 + "\" not found"); 
               }
               else {
                  System.out.println("\"" + enter1 + "\"");
               }
               break;
               
            case 'E':
               System.out.println("\tLabel: ");
               label = scan.nextLine();
               System.out.println("\tBase Edge: ");
               baseEdge = Double.parseDouble(scan.nextLine()); 
               System.out.println("\tHeight: "); 
               height = Double.parseDouble(scan.nextLine());  
               if (myPyramidList.editPentagonalPyramid(label, 
                  baseEdge, height)) {
                  System.out.print("\"" + label + "\" successfully edited");
               }
               else {
                  System.out.print("\"" + label + "\" not found");
               }
            case 'Q': 
               break;
                              
            default: 
               System.out.println("\t*** invalid code ***");
               System.out.println();
         
         
         }  
      } while (letterChar != 'Q');
   }
}

