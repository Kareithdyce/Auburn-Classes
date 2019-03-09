import java.text.DecimalFormat;

/**.
*
* Project 6
* @author Evelyn Vaughn - COMP 1 - Section 007
* 2/6/2019
*/
   
public class PentagonalPyramid
{

// instance variables 
   private String label = ""; 
   private double baseEdge = 0;
   private double height = 0; 
   

// constructor

      /**
      * Constructor.
      * @param labelIn - name of shape
      * @param baseEdgeIn - name of base length
      * @param heightIn - name of height
      */
 
   public PentagonalPyramid(String labelIn, double baseEdgeIn, double heightIn) 
   {
   
      setLabel(labelIn);
      setBaseEdge(baseEdgeIn);
      setHeight(heightIn);
      
   }
   
// methods

      /**. 
      * @return String
      *
      */
   public String getLabel() {
      return label;
   }
   
      /**.
      * Method.
         * @return label
      * @param labelInput for label
      */
   public boolean setLabel(String labelInput) {
    
      if (labelInput != null) {
         label = labelInput.trim();
         return true;
      }
      return false;
   }
   
      /**.
      * @return baseEdge
      * @param baseEdge
      */
   public double getBaseEdge() {
      return baseEdge;
   
   }
   
      /**.
      * @param baseEdgeIn for setbaseEdge
      * @return baseEdge for baseEdgeIn
      */
   public boolean setBaseEdge(double baseEdgeIn) {
      if (baseEdgeIn > 0) {
         baseEdge = baseEdgeIn;
         return true;
      }
      return false;
      
   }
   
      /**.
      * @return double
      *
      */
   public double getHeight() {
      return height;
   }
   
      /**.
      * @param heightIn - name for height
      * @return heightIn - name for height
      */
   public boolean setHeight(double heightIn) {
      if (heightIn > 0) {
         height = heightIn;
         return true;
      }
      return false;     
      
   }
   
      /**.
      * @param surfaceArea
      * @return double
      *
      */
   public double surfaceArea() {
   
      return ((5.0 / 4.0) * Math.tan(Math.toRadians(54)) 
         * Math.pow(baseEdge, 2)) 
         + (5.0 * (baseEdge / 2.0) * (Math.sqrt(Math.pow(height, 2) 
         + Math.pow(baseEdge * Math.tan(Math.toRadians(54)) / 2.0, 2))));
   }
   
      /**.
      * @param 
      * @return double
      *
      */
   public double volume() {
   
      return (5.0 / 12.0) * Math.tan(Math.toRadians(54)) 
         * height * Math.pow(baseEdge, 2);
   }
    
    /**.
      * @param 
      * @return double
      *
      */
   public String toString() {
      DecimalFormat df = new DecimalFormat("#,##0.0######");
      
      return "PentagonalPyramid \"" + label + "\" with" 
         + " base edge = " + baseEdge + " and height = " 
         + height + " units has: " + "\n\tsurface area = " 
         + df.format(surfaceArea()) + " square units" 
         + "\n\tvolume = " + df.format(volume()) + " cubic units";
   }

}