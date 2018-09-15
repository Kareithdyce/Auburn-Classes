import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
* Tests the methods of Cone.
*  
* Project 07A
*
* @author Kareith Dyce
* @version 10/18/16
*/
public class ConeTest {
   


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /**
   *Tests getLabel().
   */
   @Test public void getLabel()
   { 
      Cone cone = new Cone("Cone", 3, 4);
      Assert.assertEquals("", "Cone", cone.getLabel());
   }
   /**
   *Tests getHeight().
   */ 
   @Test public void getHeight()
   { 
      Cone cone = new Cone("Cone", 3, 4);
      Assert.assertEquals("", 3, cone.getHeight(), 0);
   }   
   
   /**
   *Tests getRadius().
   */
   @Test public void getRadius()
   { 
      Cone cone = new Cone("Cone", 3, 4);
      Assert.assertEquals("", 4, cone.getRadius(), 0);
   }
   /**
   *Tests getCount().
   */
   @Test public void getCount() 
   { 
      Cone.resetCount();
      Cone c = new Cone("Cone", 6, 8);
      Cone c1 = new Cone("Cone2", 16, 18);
      Cone c2 = new Cone("Cone3", 26, 28);
      Assert.assertEquals("", 3, Cone.getCount(), 0);
      Cone.resetCount();
      Assert.assertEquals("", 0, Cone.getCount(), 0);
   }
    
   /**
   *Tests setLabel().
   */    
   @Test public void setLabel()
   {  
      Cone cone = new Cone("Cone", 3, 4);
      Assert.assertFalse("", cone.setLabel(null));
      Assert.assertTrue("", cone.setLabel("Cone 1"));
   }
   
   /**
   *Tests setHeight().
   */
   @Test public void setHeight()
   { 
      Cone cone = new Cone("Cone", 3, 4);
      Assert.assertFalse("", cone.setHeight(0));
      Assert.assertTrue("", cone.setHeight(4));
   }
   
   /**
   *Tests setRadius().
   */
   @Test public void setRadius()
   { 
      Cone cone = new Cone("Cone", 3, 4);
      Assert.assertFalse("", cone.setRadius(0));
      Assert.assertTrue("", cone.setRadius(4));
   }
   
   /**
   *Tests basePerimeter().
   */
   @Test public void basePerimeter()
   { 
      Cone cone = new Cone("Cone", 3, 4);
      Assert.assertEquals("", 25.133, cone.basePerimeter(), 0.01);
   }
   /**
   *Tests baseArea().
   */
   @Test public void baseArea()
   {    
      Cone cone = new Cone("Cone", 3, 4);
      Assert.assertEquals("", 50.265, cone.baseArea(), 0.01);
   }
  
  /**
   *Tests slantHeight().
   */
  
   @Test public void slantHeight()
   {  
      Cone cone = new Cone("Cone", 3, 4);
      Assert.assertEquals("", 5, cone.slantHeight(), 0.01);
   }
   
   
   /**
   *Tests sideArea().
   */
   @Test public void sideArea() 
   {  
      Cone cone = new Cone("Cone", 3, 4);
      Assert.assertEquals("", 62.832, cone.sideArea(), 0.01);
   }
   
  
  /**
   *Tests surfaceArea().
   */
   @Test public void surfaceArea()
   { 
      Cone cone = new Cone("Cone", 3, 4);
      Assert.assertEquals("", 113.097, cone.surfaceArea(), 0.01);
   }
   /**
   *Tests volume().
   */
   @Test public void volume()
   { 
      Cone cone = new Cone("Cone", 3, 4);
      Assert.assertEquals("", 50.265, cone.volume(), 0.01);  
      Assert.assertEquals("", "\"Cone\" is a cone with height = 3.0 "
         + "units and radius = 4.0 units,\nwhich has base perimeter = 25.133 "
         + "units, base area = 50.265 square units,\nslant height = 5.0 units, "
         + "side area = 62.832 square units,\nsurface area = 113.097 square "
         + "units, and volume = 50.265 cubic units.", cone.toString());      
   }
   /**
   *Tests equals().
   */
   @Test public void equals()
   {
      Cone cone = new Cone("Cone", 3, 4);
      Cone c = new Cone("cOnE", 3, 4);
      Assert.assertTrue("", cone.equals(c));
      int i = 4;
      
      c.setRadius(3);
      Assert.assertFalse("", cone.equals(c));
      c.setHeight(4);
      Assert.assertFalse("", cone.equals(c));
      c.setLabel("Coke");
      Assert.assertFalse("", cone.equals(c));
      
      Assert.assertFalse("", cone.equals(i));
      
      Assert.assertEquals("", 0, cone.hashCode(), 0);
   }

 
}

