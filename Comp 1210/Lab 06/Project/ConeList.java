import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
* Finds the totals and averages of properties for a list of cones such as
* total surface area, total volume, total base perimeter, total base area,
* average surface area, and average volume.
*
* Project 06
*
* @author Kareith Dyce
* @version 10/4/16
*/


public class ConeList
{
   private String name;
   private ArrayList<Cone> coneList = new ArrayList<Cone>();
    
   
   /**
   * Creates a arraylist to store cone objects
   * These values initialize the values of coneList and the name of the List . 
   *
   * @param nameIn the name of the list the of cones.
   * @param coneListIn the array that holds the cone objects 
   */

   
   public ConeList(String nameIn, ArrayList<Cone> coneListIn)
   { 
      coneList = coneListIn;
      name = nameIn;
   }
   /**
   *Returns a String representing the name of the list.
   *
   *@return returns the String 
   */
   public String getName()
   {
      return name;
   }
   
   
   /**
   * Returns the ArrayList of Cone objects.
   *
   *@return Returns the ArrayList 
   */

   public ArrayList<Cone> getList() 
   {
      return coneList;
   } 
   
   
   /**
   *Returns an int representing the number of Cone objects in the
   *ConeList.
   *
   *@return number of cones 
   */
   
   public int numberOfCones()
   {
      int numCone = coneList.size();
      return numCone;
   }
   
   /**
   *Returns a double representing the total for the base
   *perimeters for all Cone objects in the list.
   *
   *@return total base perimeter 
   */
   public double totalBasePerimeter()
   {
      double total = 0;
      int index = 0;
      while (index < coneList.size())
      { 
         total += coneList.get(index).basePerimeter();
         
         index++;
      }
      
      return total;
   }
   
   
   /**
   *Returns a double representing the total for the base
   *areas for all Cone objects in the list.
   *
   *@return total base area 
   */
   public double totalBaseArea()
   {
      double total = 0;
      int index = 0;
      while (index < coneList.size())
      { 
         total += coneList.get(index).baseArea();
         
         index++;
      }
      
      return total;
   }
   
   /**
   *Returns a double representing the total for the slant
   *height for all Cone objects in the list.
   *
   *@return total slant height 
   */
   public double totalSlantHeight()
   {
      double total = 0;
      int index = 0;
      while (index < coneList.size())
      { 
         total += coneList.get(index).slantHeight();
         
         index++;
      }
      
      return total;
   }
   
   /**
   *Returns a double representing the total for the side
   *area for all Cone objects in the list.
   *
   *@return total side area 
   */
   public double totalSideArea()
   {
      double total = 0;
      int index = 0;
      while (index < coneList.size())
      { 
         total += coneList.get(index).sideArea();     
         index++;
      }
      
      return total;
   }
 /**
 *Returns a double representing the total for the 
 *volumes for all Cone objects in the list.
 *
 *@return total volume
 */
   public double totalVolume()
   {
      double total = 0;
      int index = 0;
      while (index < coneList.size())
      { 
         total += coneList.get(index).volume();
         
         index++;
      }
      
      return total;
   }
  
  
   /**
   *Returns a double representing the total for the surface
   *areas for all Cone objects in the list.
   *
   *@return total surface area 
   */
      
   public double totalSurfaceArea()
   {
      double total = 0;
      int index = 0;
      while (index < coneList.size())
      { 
         total += coneList.get(index).surfaceArea();
         
         index++;
      }
      
      return total;
   }
   
 /**
 *Returns a double representing the average 
 *volume for all Cone objects in the list.
 *
 *@return average volume
 */

   public double averageVolume()
   {
      double total = 0;
      int index = 0;
      while (index < coneList.size())
      { 
         total += coneList.get(index).volume();
         
         index++;
      }
      
      if (total != 0)
      {
         return total / coneList.size();
      }
      else
      {
         return total;
      }
   }

 /**
 *Returns a double representing the average 
 *surface area for all Cone objects in the list.
 *
 *@return surface area
 */
   public double averageSurfaceArea()
   {
      double total = 0;
      int index = 0;
      while (index < coneList.size())
      { 
         total += coneList.get(index).surfaceArea();
         
         index++;
      }
      
      if (total != 0)
      {
         return total / coneList.size();
      }
      else
      {
         return total;
      }
   
   
   }
   
   /**
   *Takes a String parameter representing the file name, reads in the file,
      *storing the list name and creating an ArrayList of Cone objects.
   *
   * @param fileIn file name that the programs reads in
   * @return ArrayList of cones using data from file
   * @throws IOException from scanning input file.
      */
   
   public ConeList readFile(String fileIn) throws IOException
   {
      Scanner file = new Scanner(new File(fileIn)); 
      ArrayList<Cone> conList = new ArrayList<Cone>();
      String listName = file.nextLine(); 
      String label = "";
      double height = 0, radius = 0;
      
      while (file.hasNext())
      { 
         label = file.nextLine();
         height = Double.parseDouble(file.nextLine());
         radius = Double.parseDouble(file.nextLine());
         Cone c = new Cone(label, height, radius);
         conList.add(c);
      }   
      ConeList cList = new ConeList(listName, conList);
      return cList;
   }
   
   
   /**
   * Creates a new Cone object, and adds it to the ConeList object.
   *
   * @param labelIn the label of the cone.
   * @param heightIn the hieght of the cone.
   * @param radiusIn the radius of the cone.
   */
   public void addCone(String labelIn, double heightIn, double radiusIn)
   {
      Cone c = new Cone(labelIn, heightIn, radiusIn);
      coneList.add(c);
   }
   /**
   *Takes a label of a Cone as the String parameter and returns the
      *corresponding Cone object if found in the ConeList object.
   *
   *@param labelIn the label of the cone to be added.
   *@return the cone that was found (if found).
   */
   public Cone findCone(String labelIn)
   {
      for (Cone c: coneList)
      {
         if (c.getLabel().equalsIgnoreCase(labelIn))
         {
            return c;
         }
      }
      return null;
   } 
   
   /**
   *Deletes a cone.
   *
   *@param labelIn the label of the cone to  be deleted.
   *@return the cone that was deleted(if found).
   */

   public Cone deleteCone(String labelIn)
   {
      for (Cone c: coneList)
      {
         if (c.getLabel().equalsIgnoreCase(labelIn))
         {
            coneList.remove(c);
            return c;
         }
      }
      
      return null;
   } 
   
   /**
   * Edits a cone.
   *
   * @param labelIn the label of the cone to be edited.
   * @param heightIn the new height of the cone.
   * @param radiusIn the new radius of the cone.
   * @return the edited cone(if found).
   */

   
   public boolean editCone(String labelIn, double heightIn, double radiusIn)
   {
      if (findCone(labelIn) != null)
      { 
         Cone c = findCone(labelIn);
         c.setHeight(heightIn);
         c.setRadius(radiusIn); 
         return true;
      }
      else
      {
         return false;
      }
   }
      
 /**
 * Returns a String containing the name of the list followed
 * by each Cone in the ArrayList.
 *
 *@return the string descriptions of each cone  
 */
   public String toString()
   {
      String result = "\n" + name + "\n";
      int index = 0;
      while (index < coneList.size())
      { 
         result += "\n" + coneList.get(index) + "\n";
         index++;
      }
      return result;
   }

 /**
 *Returns a String containing the name of the list
 *followed by various summary items.
 *
 *@return a string summarizing all the data of the cones 
 */

   public String summaryInfo()
   {
      DecimalFormat df = new DecimalFormat("#,##0.0##");
      String result = "----- Summary for " + name + " -----"
            + "\nNumber of Cones: " + numberOfCones()
            + "\nTotal Base Perimeter: " + df.format(totalBasePerimeter())
            + "\nTotal Base Area: " + df.format(totalBaseArea())  
            + "\nTotal Slant Height: " + df.format(totalSlantHeight()) 
            + "\nTotal Side Area: " + df.format(totalSideArea())
            + "\nTotal Surface Area: " + df.format(totalSurfaceArea())
            + "\nTotal Volume: " + df.format(totalVolume())
            + "\nAverage Surface Area: " + df.format(averageSurfaceArea())
            + "\nAverage Volume: " + df.format(averageVolume());
      return result;
   }

}





