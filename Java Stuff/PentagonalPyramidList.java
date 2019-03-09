import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**.
*
* Project 6
* @author Evelyn Vaughn - COMP 1 - Section 007
* 2/20/2019
*/
   
public class PentagonalPyramidList {

//instance variables
   private String listName = " ";
   private ArrayList<PentagonalPyramid> pList;
  
//constructor 
 
    /**
      * Constructor.
      * @param listNameIn types a name
      * @param pListIn types a list
      */
   public PentagonalPyramidList(String listNameIn, 
      ArrayList<PentagonalPyramid> pListIn) {
      listName = listNameIn;
      pList = pListIn; 
   }

//methods 

   /**. 
      * @return litstName
      */
   public String getName() {
      return listName;
   }
   
    /**. 
      * @return totlSurfaceArea 
      */
   public int numberOfPentagonalPyramids() {
      return pList.size();
   } 
        
   /**. 
      * @return totlSurfaceArea 
      */
   public double totalSurfaceArea() {
      double total = 0;
      int surfaceArea = 0;
      while (surfaceArea < pList.size()) {
         total += pList.get(surfaceArea).surfaceArea();
         surfaceArea++;  
      }   
      
      return total;
   }

   /**. 
      * @return the total volume for all objects
      */
   public double totalVolume() {
      double total = 0;
      int totalVolume = 0;
      while (totalVolume < pList.size()) {
         total += pList.get(totalVolume).volume();
         totalVolume++;  
      }   
      
      return total;
   }
   
   /**. 
      * @return averageSurfaceArea
      */
   public double averageSurfaceArea() {  
      double averageSurfaceArea = 0;
      if (averageSurfaceArea < pList.size()) {
         return totalSurfaceArea() / numberOfPentagonalPyramids();
      }
      return averageSurfaceArea;
   }
   
   /**. 
      * @return averageVolume
      */
   public double averageVolume() {
      double averageVolume = 0;
      if (averageVolume < pList.size()) {
         return totalVolume() / numberOfPentagonalPyramids();
      }
      return averageVolume;
   }
   
   /**. 
      * @return String 0 name of the list
      */
   public String toString() {
      String result = listName + "\n";
      int index = 0;
      while (index < pList.size()) {
         result += "\n" + pList.get(index) + "\n"; 
         index++;  
      }   
      return result;
   }
   
   /**. 
      * @return String
      */
   public String summaryInfo() {
      DecimalFormat df = new DecimalFormat("#,##0.0##");
      String result = "";
      result += "----- Summary for " + getName() + " -----"; 
      result += "\nNumber of PentagonalPyramid: " 
         + numberOfPentagonalPyramids();
      result += "\nTotal Surface Area: " + df.format(totalSurfaceArea()); 
      result += "\nTotal Volume: "  + df.format(totalVolume());
      result += "\nAverage Surface Area: "  + df.format(averageSurfaceArea());
      result += "\nAverage Volume: "  + df.format(averageVolume());
      
      return result;
   }
   
  
   //New Methods
   
   /**  
   * @return PentagonalPyramidList
   */ 
   public ArrayList<PentagonalPyramid> getList() {
      return pList;
   }
  
  /**  
   *
   * @param fileNameIn for fileName to read
   * @return PentagonalPyramidList
   * @throws FileNotFoundException if the file cannot be opened
   */   
   public PentagonalPyramidList readFile(String fileNameIn) 
                                 throws FileNotFoundException {
            
      Scanner scanFile = new Scanner(new File(fileNameIn));
      ArrayList<PentagonalPyramid> myList = new ArrayList<PentagonalPyramid>();
      String pListName = "";
      String label = "";
      double baseEdge = 0;
      double height = 0;   
      listName = scanFile.nextLine(); 
           
      while (scanFile.hasNext()) {
         label = scanFile.nextLine();
         baseEdge = Double.parseDouble(scanFile.nextLine());
         height = Double.parseDouble(scanFile.nextLine());
         PentagonalPyramid p = new PentagonalPyramid(label, baseEdge, height);
         myList.add(p);           
      }
      
      PentagonalPyramidList pL = new PentagonalPyramidList(listName, myList);
      
      return pL;
   }
   
  /**  
   * @param labelIn for label
   * @param baseEdgeIn for base edge
   * @param heightIn for height
   */   
   public void addPentagonalPyramid(String labelIn, 
      double baseEdgeIn, double heightIn) {
       
      PentagonalPyramid p = new PentagonalPyramid(labelIn, 
         baseEdgeIn, heightIn);
      pList.add(p);
   }
   
   /**
   * @param labelIn for label
   * @return null
   */   
   public PentagonalPyramid findPentagonalPyramid(String labelIn) {
      PentagonalPyramid result = null;
      
      for (PentagonalPyramid p : pList) {
      
         if (p.getLabel().equalsIgnoreCase(labelIn)) {
         
            result = p;
            return result;
         } 
      }
      return result;
   }
   
   
  /**
   * @param labelIn 
   * @return true if deleted else return false
   */   
   public PentagonalPyramid deletePentagonalPyramid(String labelIn) {
   
      PentagonalPyramid result = null;
      
      for (PentagonalPyramid p : pList) {
      
         if (p.getLabel().equalsIgnoreCase(labelIn)) {
            result = p;
            pList.remove(p);
            break;
         } 
      }
      return result;
   }
   
/**
   * @param labelIn 
   * @param baseEdgeIn for base edge
   * @param heightIn for height
   * @return true
   */
   public boolean editPentagonalPyramid(String labelIn, 
      double baseEdgeIn, double heightIn) {
      PentagonalPyramid pP = findPentagonalPyramid(labelIn); 
      if (pP != null) {
         pP.setBaseEdge(baseEdgeIn);
         pP.setHeight(heightIn);
         return true;
      }
      return false;
   
   }
}
