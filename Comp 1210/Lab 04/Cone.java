import java.text.DecimalFormat;

/**
  * Create a Cone class that stores the label, height, and radius 
* (height and radius each must be greater than zero).
* Calculates the base perimeter, base area, slant height, side area,
   * surface area, and volume of a Cone object, and a method to provide a
   * String value of a Cone object
* Project 04
*
* @author Kareith Dyce
* @version 9/13/16
*/


public class Cone
{
   private String label;
   private double radius; 
   private double height;  
 
   /**
   * Creates a new Cone object with a  given label, height, and radius
   * These values initialize the values of label, height and radius. 
   *
   * @param labelIn the name of the cone object entered.
   * @param heightIn the height of the cone object entered.
   * @param radiusIn the radius of cone object entered.
   */

   public Cone(String labelIn, double heightIn, double radiusIn)
   {
      setLabel(labelIn); 
      setHeight(heightIn);
      setRadius(radiusIn); 
   }    
   
   
   /**
   * Gets the label and returns it.
   *
   * @return String value for the label.
   */


   public String getLabel()
   {  
      return label;
   }
   
   /**
   * Gets the height and returns it.
   *
   * @return double value for the height.
   */
   
   public double getHeight()
   {
      return height;
   }
   
   
   /**
   * Gets the radius and returns it.
   *
   * @return double value for the radius.
   */

   
   public double getRadius()
   {
      return radius;
   }
   
   
   
   
   /**
   * Checks that the label is not null and reads in the label. 
   *
   * @param labelIn the label that was entered in the cone class.
   * @return tells if the varible has been set.
   */
   
   
   public boolean setLabel(String labelIn)
   {
      boolean isSet = false;
      if (labelIn != null)
      {
         label = labelIn.trim();
         isSet = true;
      }
      
      return isSet;
   }  
   
   
   /**
   * Checks that the height is positive and reads in the height. 
   *
   * @param heightIn the radius that was entered in the cone class.
   * @return tells if the varible has been set.
   */
   public boolean setHeight(double heightIn)
   {
      boolean isSet = false;
      if (heightIn > 0)
      {
         height = heightIn;
         isSet = true;
      }
      
      return isSet;
   }

   /**
   * Checks that the radius is positive and reads in the radius. 
   *
   * @param radiusIn the radius that was entered in the cone class.
   * @return tells if the varible has been set.
   */
   
   public boolean setRadius(double radiusIn)
   {
      boolean isSet = false;
      if (radiusIn > 0)
      {
         radius = radiusIn;
         isSet = true;
      }
      
      return isSet;
   }

   
   
   /**
   * Calculates the perimeter of the base.
   *
   * @return double value for the perimeter of the base calculated 
   * using the radius.
   */

   public double basePerimeter()
   {
      return 2 * radius * Math.PI;  
   }
   
   /**
   * Calculates the area of the base.
   *
   * @return double value for the area of the base calculated 
   * using the radius.
   */

   
   public double baseArea()
   {
      return Math.PI * Math.pow(radius, 2.0);
   }
   
   /**
   * Calculates the slant height.
   *
   * @return double value for the slant height calculated 
   * using height and radius.
   */

   public double slantHeight()
   {
      double slantHeight;
      slantHeight = Math.sqrt(Math.pow(radius, 2.0) + Math.pow(height, 2.0));
      return slantHeight;
   }
   
   /**
   * Calculates side area. Finds the slant height.
   *
   * @return double value for the side area calculated 
   * using radius and slant height.
   */
   public double sideArea()
   {
      
      return slantHeight() * radius * Math.PI;
   }
   /**
   * Calculates suface area. Finds the slant height.
   *
   * @return double value for the surface area calculated 
   * using base area and side area.
   */
   public double surfaceArea()
   {
      return Math.PI * radius * (radius + slantHeight());
   }
   
   /**
   *Calculates volume.
   *
   *@return double value for the volume calculated using height and radius
   */
   public double volume()
   {
      return (Math.PI * Math.pow(radius, 2) * height) / 3;
   }
   
 /**
 * Returns a String representation of the cone object.
 * 
 * @return String representation of cone object.
 */
   public String toString()
   {
      DecimalFormat df = new DecimalFormat("#,##0.0##");
      String output = "";
      output += "\"" + label + "\" is a cone with height = " 
         + df.format(height)
         + " units and radius = " + df.format(radius) 
         + " units,\nwhich has base"
         + " perimeter = " + df.format(basePerimeter()) 
         + " units, base area = " 
         + df.format(baseArea()) + " square units,\nslant height = "
         + df.format(slantHeight()) + " units, side area = " 
         + df.format(sideArea()) + " square units"
         + ",\nsurface area = " + df.format(surfaceArea()) 
         + " square units, and "
         + "volume = " + df.format(volume()) + " cubic units.";   
   
      return output;
   } 
}