import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
/**
   * Tests the methods of ConeList2.
   *  
   * Project 07A
   *
   * @author Kareith Dyce
   * @version 10/18/16
   */



public class ConeList2Test 
{  
   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp()
   {
   }

   /**
   *Tests getName().
   */

   @Test public void getName()
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", "List", cList.getName());
   }
   
   /**
   *Tests numberOfCones().
   */

   @Test public void numberOfCones()
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", 0, cList.numberOfCones(), 0);
      cList.addCone("c1", 1.5, 2);
      cList.addCone("c2", 3, 4);
      cList.addCone("c3", 6, 8);
      cList.addCone("c4", 9, 12);
      Assert.assertEquals("", 4, cList.numberOfCones(), 0);
   }
      
   /**
   *Tests totalBasePerimeter().
   *
   *@throws IOException from scanning input file.
   */

   @Test public void totalBasePerimeter() throws IOException
   {INT 
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", 0, cList.totalBasePerimeter(), 0);
      cList = cList.readFile("cone_1.dat");
      Assert.assertEquals("", 2802.929, cList.totalBasePerimeter(), 0.1);
   }
   
   /**
   *Tests totalBaseArea().
   *
   *@throws IOException from scanning input file.
      */

   @Test public void totalBaseArea() throws IOException
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", 0, cList.totalBaseArea(), 0);
      cList = cList.readFile("cone_1.dat");
      Assert.assertEquals("", 505496.112, cList.totalBaseArea(), 0.1);
   }
   /**
   *Tests totalSlantHeight().
   *
   * @throws IOException from scanning input file.
      */

   @Test public void totalSlantHeight() throws IOException
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", 0, cList.totalSlantHeight(), 0);
      cList = cList.readFile("cone_1.dat");
      Assert.assertEquals("", 631.491, cList.totalSlantHeight(), 0.1);
   }

   /**
   *Tests totalSideArea().
   *
   * @throws IOException from scanning input file.
   */

   @Test public void totalSideArea() throws IOException
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", 0, cList.totalSideArea(), 0);
      cList = cList.readFile("cone_1.dat");
      Assert.assertEquals("", 636490.731, cList.totalSideArea(), 0.1);
   }
   /**
   *Tests totalSurfaceArea().
   *
   *@throws IOException from scanning input file.
      */

   @Test public void totalSurfaceArea() throws IOException
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", 0, cList.totalSurfaceArea(), 0);
      cList = cList.readFile("cone_1.dat");
      Assert.assertEquals("", 1141986.844, cList.totalSurfaceArea(), 0.1);
   }
   /**
   *Tests totalVolume().
   *
   *@throws IOException from scanning input file.
      */

   @Test public void totalVolume() throws IOException
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", 0, cList.totalVolume(), 0);
      cList = cList.readFile("cone_1.dat");
      Assert.assertEquals("", 50312842.12, cList.totalVolume(), 0.1);
   }
   
   /**
   *Tests averageSurfaceArea().
   *
   * @throws IOException from scanning input file.
   */

   @Test public void averageSurfaceArea() throws IOException
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", 0, cList.averageSurfaceArea(), 0);
      cList = cList.readFile("cone_1.dat");
      
      Assert.assertEquals("", 285496.711, cList.averageSurfaceArea(), 0.1);
   }
   
   /**
   *Tests averageVolume().
   *
   *@throws IOException from scanning input file.
   */
   @Test public void averageVolume() throws IOException
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", 0, cList.averageVolume(), 0);
      cList = cList.readFile("cone_1.dat");
      Assert.assertEquals("", 12578210.53, cList.averageVolume(), 0.1);
   }
   
   /**
   *Tests findCone().
   *
   *@throws IOException from scanning input file.
      */
   @Test public void findCone() throws IOException
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      cList = cList.readFile("cone_1.dat");
      list = cList.getList();
      Assert.assertEquals("", list[2], cList.findCone("Tall Example"));
      Assert.assertEquals("", null, cList.findCone("Hi"));   
   }
   /**
   *Tests deleteCone().
   *
   * @throws IOException from scanning input file.
   */

   @Test public void deleteCone() throws IOException
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      cList = cList.readFile("cone_1.dat");
      list = cList.getList();
      Assert.assertEquals("", list[2], cList.deleteCone("Tall Example"));
      Assert.assertEquals("", null, cList.deleteCone("Hi"));   
   }
   /**
   *Tests editCone().
   */

   @Test public void editCone()
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      cList.addCone("c1", 3, 4);
      Assert.assertTrue("", cList.editCone("c1", 6, 8));
      Assert.assertFalse("", cList.editCone("cone", 6, 8)); 
   }
   /**
   *Tests findConeWithSmallestHeight().
   *
   *@throws IOException from scanning input file.
   */

   @Test public void findConeWithSmallestHeight() throws IOException
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", null, cList.findConeWithSmallestHeight());
      cList = cList.readFile("cone_1.dat");
      list = cList.getList();
      Assert.assertEquals("", list[0], cList.findConeWithSmallestHeight());
   }
   
   /**
   *Tests findConeWithLargestHeight().
   *
   *@throws IOException from scanning input file.
   */

   @Test public void findConeWithLargestHeight() throws IOException
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", null, cList.findConeWithLargestHeight());
      cList = cList.readFile("cone_1.dat");
      list = cList.getList();
      cList.addCone("c1", 3, 4);
      Assert.assertEquals("", list[3], cList.findConeWithLargestHeight());
   }
/**
   *Tests findConeWithSmallestRadius().
   *
   *@throws IOException from scanning input file.
   */

   @Test public void findConeWithSmallestRadius() throws IOException
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", null, cList.findConeWithSmallestRadius());
      cList = cList.readFile("cone_1.dat");
      list = cList.getList();
      Assert.assertEquals("", list[0], cList.findConeWithSmallestRadius());
   }
   
   /**
   *Tests findConeWithLargestRadius().
   *
   *@throws IOException from scanning input file.
   */

   @Test public void findConeWithLargestRadius() throws IOException
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      Assert.assertEquals("", null, cList.findConeWithLargestRadius());
      cList = cList.readFile("cone_1.dat");
      list = cList.getList();
      Assert.assertEquals("", list[3], cList.findConeWithLargestRadius());
   }
   
   /**
   *Tests toString() and summaryInfo().
   */ 
   @Test public void strings()
   {
      Cone[] list = new Cone[100];
      ConeList2 cList = new ConeList2("List", list, 0);
      cList.addCone("Cone", 3, 4);
      Assert.assertEquals("", "\nList\n\n\"Cone\" is a cone with height = 3.0 "
         + "units and radius = 4.0 units,\nwhich has base perimeter = 25.133 "
         + "units, base area = 50.265 square units,\nslant height = 5.0 units, "
         + "side area = 62.832 square units,\nsurface area = 113.097 square "
         + "units, and volume = 50.265 cubic units.\n", cList.toString());
      Assert.assertEquals("", "----- Summary for List -----"
            + "\nNumber of Cones: 1"
            + "\nTotal Base Perimeter: 25.133"
            + "\nTotal Base Area: 50.265"   
            + "\nTotal Slant Height: 5.0" 
            + "\nTotal Side Area: 62.832" 
            + "\nTotal Surface Area: 113.097"
            + "\nTotal Volume: 50.265"
            + "\nAverage Surface Area: 113.097"
            + "\nAverage Volume: 50.265", cList.summaryInfo());
   }
}
