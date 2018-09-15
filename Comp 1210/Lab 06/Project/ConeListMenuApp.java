import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


/** 
*The main method should print a list of options with the action code and a 
* short description followed by a line with just the action codes prompting the
* user to select an action.
*
* Project 06
*
* @author Kareith Dyce
* @version 10/4/16
   */   
public class ConeListMenuApp

{
 /**
 * 
 * @param args - is not used.
 * @throws IOException from scanning input file.
 */
   
   public static void main(String [] args) throws IOException
   {
      String name = "List name", label = "";
      double height = 0, radius = 0;
      ArrayList<Cone> coneList = new ArrayList<Cone>();
      ConeList cList = new ConeList(name, coneList); 
      boolean isEdited = false;
   
      
      String uChoice = "";
      char choice = 'a';
      Scanner in = new Scanner(System.in); 
      System.out.print("Cone List System Menu"
                  + "\nR - Read File and Create Cone List"
                  + "\nP - Print Cone List"
                  + "\nS - Print Summary"
                  + "\nA - Add Cone"
                  + "\nD - Delete Cone"
                  + "\nF - Find Cone"
                  + "\nE - Edit Cone"
                  + "\nQ - Quit");
      do {
         System.out.print("\nEnter Code [R, P, S, A, D, F, E, or Q]: ");
         uChoice = in.nextLine();
         if (uChoice.length() == 0) 
         {
            continue;
         }
         choice = uChoice.toUpperCase().charAt(0);
         switch(choice)
         {
            case 'R' :
               System.out.print("\tFile name: ");
               String file = in.nextLine();
               System.out.println("\tFile read in and Cone List created");
               cList = cList.readFile(file);
               break;
         
            case 'P' :
               System.out.print(cList);
               break;
         
            case 'S' :
               System.out.println(cList.summaryInfo());
               break;
         
            case 'A' :
               System.out.print("\tLabel: ");
               label = in.nextLine();
               System.out.print("\tHeight: ");
               height = Double.parseDouble(in.nextLine());
               System.out.print("\tRadius: ");
               radius = Double.parseDouble(in.nextLine());
               System.out.println("\t*** Cone added ***");
               cList.addCone(label, height, radius);
               break;
         
            case 'D' :
               System.out.print("\tLabel: ");
               label = in.nextLine();
               System.out.println("\t\"" + label + "\""   
                    + (cList.deleteCone(label) != null 
                           ? " deleted" : " not found"));
               cList.deleteCone(label);
               break;
         
            case 'F' :
               System.out.print("\tLabel: ");
               label = in.nextLine();
               System.out.println(cList.findCone(label) != null
                       ?cList.findCone(label) : "\t\""
                       + label + "\" not found");
            
               break;
         
            case 'E' :
               
               System.out.print("\tLabel: ");
               label = in.nextLine();
               System.out.print("\tHeight: ");
               height = Double.parseDouble(in.nextLine());
               System.out.print("\tRadius: ");
               radius = Double.parseDouble(in.nextLine());
               isEdited = cList.editCone(label, height, radius);
               System.out.println("\t\"" + label + "\"" 
                  + (isEdited ? " successfully edited" : " not found")); 
               break;
         
            case 'Q' :
            
               break;
         
            default: 
               System.out.println("\t*** invaild code ***");
               
               break;
         
         }
      
      
      }
      while(choice != 'Q');   
   }
}