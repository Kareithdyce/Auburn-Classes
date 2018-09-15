import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
* Finds the totals and averages of properties for a list of cones such as
* total surface area, total volume, total base perimeter, total base area,
* average surface area, and average volume.
*
* Project 07A
*
* @author Kareith Dyce
* @version 10/18/16
*/


public class ConeList2
{
   private String name;
   private Cone[] list = new Cone[100];
   private int numCone;
   
   /**
   * Creates an array to store cone objects
   * These values initialize the values of list and the name of the List . 
   *
   * @param nameIn the name of the list the of cones.
   * @param listIn the array that holds the cone objects 
   * @param numConeIn the amount of cones in the object
   */

   
   public ConeList2(String nameIn, Cone[] listIn, int numConeIn)
   { 
      list = listIn;
      name = nameIn;
      numCone = numConeIn;
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
   * Returns array of Cone objects.
   *
   *@return Returns the array 
   */

   public Cone[] getList() 
   {
      return list;
   } 
   
   
   /**
   *Returns an int representing the number of Cone objects in the
   *ConeList2.
   *
   *@return number of cones 
   */
   
   public int numberOfCones()
   {  
      if (numCone == 0)
      {
         return 0;
      }
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
      while (index < numCone)
      { 
         total += list[index].basePerimeter();
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
      while (index < numCone)
      { 
         total += list[index].baseArea();
         
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
      while (index < numCone)
      { 
         total += list[index].slantHeight();
         
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
      while (index < numCone)
      { 
         total += list[index].sideArea();     
         index++;
      }
      
      return total;
   }
 /**
 *Gives a double representing the total for the 
 *volumes for all Cone objects in the list.
 *
 *@return total volume
 */
   public double totalVolume()
   {
      double total = 0;
      int index = 0;
      while (index < numCone)
      { 
         total += list[index].volume();
         index++;
      }
      
      return total;
   }
  
  
   /**
   *Gives a double representing the total for the surface
   *areas for all Cone objects in the list.
   *
   *@return total surface area 
   */
      
   public double totalSurfaceArea()
   {
      double total = 0;
      int index = 0;
      while (index < numCone)
      { 
         total += list[index].surfaceArea();
         index++;
      }
      
      return total;
   }
   
   /**
   *Finds the cone with the smallest height.
   *
   *@return the cone or null if there are no cones. 
   */

   public Cone findConeWithSmallestHeight()
   {
      double sHeight = Double.MAX_VALUE; 
      Cone height = null;
      if (numCone != 0)
      {
         for (int i = 0; i < numCone; i++)
         { 
            if (list[i].getHeight() < sHeight)
            {
               sHeight = list[i].getHeight();
               height = list[i];  
            }
         }
      }
      return height;
   }   
   
   /**
   *Finds the cone with the largest height.
   *
   *@return the cone or null if there are no cones. 
   */

   
   public Cone findConeWithLargestHeight()
   {
      Cone height = null;
      double sHeight = Double.MIN_VALUE; 
      if (numCone != 0)
      {
         for (int i = 0; i < numCone; i++)
         { 
            if (list[i].getHeight() > sHeight)
            {
               sHeight = list[i].getHeight();
               height = list[i];  
            }
         }
      }
      return height;
   }

   /**
   *Finds the cone with the smallest radius.
   *
   *@return the cone or null if there are no cones. 
   */

   public Cone findConeWithSmallestRadius()
   {
      double sRadius = Double.MAX_VALUE; 
      Cone radius = null;
      if (numCone != 0)
      {
         for (int i = 0; i < numCone; i++)
         { 
            if (list[i].getRadius() < sRadius)
            {
               sRadius = list[i].getRadius();
               radius = list[i];  
            }
         
         }
      }
      return radius;
   }

   
   
   /**
   *Finds the cone with the largest radius.
   *
   *@return the cone or null if there are no cones. 
   */

   public Cone findConeWithLargestRadius()
   {
      double sRadius = Double.MIN_VALUE;
      Cone radius = null;
      if (numCone != 0)
      {
         for (int i = 0; i < numCone; i++)
         { 
            if (list[i].getRadius() > sRadius)
            {
               sRadius = list[i].getRadius();
               radius = list[i];  
            }
         }
      }
      return radius;
   }
 /**
 *Returns a double representing the average 
 *volume for all Cone objects in the list.
 *
 *@return average volume
 */

   public double averageVolume()
   {
      double total = totalVolume();
         
      if (numCone != 0)
      {
         return total / numCone;
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
      double total = totalSurfaceArea();
               
      if (numCone != 0)
      {
         return total / numCone;
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
   
   public ConeList2 readFile(String fileIn) throws IOException
   {
      Scanner file = new Scanner(new File(fileIn)); 
      Cone [] conList = new Cone[100];
      String listName = file.nextLine(); 
      String label = "";
      double height = 0, radius = 0;
      int cone = 0;
      while (file.hasNext())
      { 
         label = file.nextLine();
         height = Double.parseDouble(file.nextLine());
         radius = Double.parseDouble(file.nextLine());
         Cone c = new Cone(label, height, radius);
         conList[cone] = c;
         cone++; 
      }  
      ConeList2 cList = new ConeList2(listName, conList, cone);
      return cList;
   }
   
   
   /**
   * Creates a new Cone object, and adds it to the ConeList2object.
   *
   * @param labelIn the label of the cone.
   * @param heightIn the hieght of the cone.
   * @param radiusIn the radius of the cone.
   */
   public void addCone(String labelIn, double heightIn, double radiusIn)
   {
      Cone c = new Cone(labelIn, heightIn, radiusIn);
      list[numCone] = c;
      numCone++;
   }
   /**
   *Takes a label of a Cone as the String parameter and returns the
   *corresponding Cone object if found in the ConeList2 object.
   *
   *@param labelIn the label of the cone to be added.
   *@return the cone that was found (if found).
    */
   public Cone findCone(String labelIn)
   {
      for (int i = 0; i < numCone; i++)
      {
         if (list[i].getLabel().equalsIgnoreCase(labelIn))
         {
            return list[i];
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
      for (int i = 0; i < numCone; i++)
      {
         if (list[i].getLabel().equalsIgnoreCase(labelIn))
         {
            Cone temp = list[i];
            for (int j = i; j < numCone; j++) 
            {
               list[j] = list[j + 1];
            }
            list [numCone - 1] = null;
            numCone--;
            return temp;
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
      while (index < numCone)
      { 
         result += "\n" + list[index] + "\n";
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
            + "\nNumber of Cones: " + numCone
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





