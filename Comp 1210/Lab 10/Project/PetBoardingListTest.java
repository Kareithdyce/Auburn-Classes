import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.io.IOException;
/**
* Tests the methods in PetBoarding list.
* Project 10
*
* @author Kareith Dyce
* @version 11/02/16
*/


public class PetBoardingListTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /**
    * Tests getListName and setListName.
    *
    * @throws IOException from scanning input file.
    */
   @Test public void listNameTest() throws IOException
   {
      PetBoardingList pList = new PetBoardingList();
      pList.readPetFile("pet_boarding_data.csv");
      Assert.assertEquals("Critter Sitter", pList.getListName());
      pList.setListName("Pet List");
      Assert.assertEquals("Pet List", pList.getListName());   
   }
   
   /**
    * Tests getPetList and setPetList.
    *
    * @throws IOException from scanning input file.
    */
   @Test public void petListTest() throws IOException
   {
      PetBoardingList pList = new PetBoardingList();
      pList.readPetFile("pet_boarding_data.csv");
      Assert.assertEquals("Owner: Jake Smith   Pet: Honey   Days: 7   "
         + "Boarding Cost: $105.00\nDog: Great Dane   Weight: 60.0 lbs", 
         pList.getPetList()[1].toString());
      Pet [] pList2 = new Pet [4];
      pList2[0] = new Cat("Barb Jones", "Callie", "Siamese", 9.0, 7, 9);
      pList2[1] = new Dog("Jake Smith", "Honey", "Great Dane", 60, 7);
      pList2[2] = new Dog("Jen Baker", "Pepper", "Sheppard", 60, 7); 
      pList2[3] = new Horse("Jessie Rider", "King", "Quarter Horse", 
         1000, 7, 10.0);
      pList.setPetList(pList2);
      Assert.assertEquals(pList2[1], pList.getPetList()[1]);   
   }
   /**
    * Tests getPetCount and setPetCount.
    *
    * @throws IOException from scanning input file.
    */
   @Test public void petCountTest() throws IOException
   {
      PetBoardingList pList = new PetBoardingList();
      pList.readPetFile("pet_boarding_data.csv");
      Assert.assertEquals(5, pList.getPetCount(), 0);
      pList.setPetCount(6);
      Assert.assertEquals(6, pList.getPetCount(), 0);   
   }
      
    /**
    * Tests getExcludedRecords and setExcludedRecords.
    *
    * @throws IOException from scanning input file.
    */
   @Test public void excludedRecordsTest() throws IOException
   {
      PetBoardingList pList = new PetBoardingList();
      pList.readPetFile("pet_boarding_data.csv");
      Assert.assertEquals("*** invalid category ***" 
         + " E,Jo Doe,Sugar,Spaniel,30.0,7", 
         pList.getExcludedRecords()[0].toString());
      String [] pList2 = new String [1];
      pList2[0] = "*** invalid category ***" 
         + " P,Cr7,Messi,Spaniel,30.0,7";
      
      pList.setExcludedRecords(pList2);
      Assert.assertEquals(pList2[0], pList.getExcludedRecords()[0]);   
   }

    /**
    * Tests getExcludedCount and setExcludedCount.
    *
    * @throws IOException from scanning input file.
    */
   @Test public void excludedCountTest() throws IOException
   {
      PetBoardingList pList = new PetBoardingList();
      pList.readPetFile("pet_boarding_data.csv");
      Assert.assertEquals(1, pList.getExcludedCount(), 0);
      pList.setExcludedCount(6);
      Assert.assertEquals(6, pList.getExcludedCount(), 0);   
   }
   
   
    /**
    * Tests getListCount and resetListCount.
    */

   @Test public void listCountTest()
   {
      PetBoardingList.resetListCount();
      Assert.assertEquals(0, PetBoardingList.getListCount(), 0); 
      PetBoardingList pList = new PetBoardingList();
      Assert.assertEquals(1, PetBoardingList.getListCount(), 0);
   }
   
   
   /**
    * Tests generateReport.
    *
    * @throws IOException from scanning input file.
    */
   @Test public void generateReportTest() throws IOException
   {
      PetBoardingList pList = new PetBoardingList();
      pList.readPetFile("pet_boarding_data.csv");
      Pet [] pList2 = pList.getPetList();
      Assert.assertEquals("\n---------------------------------------"
         + "\nPet Boarding Report for Critter Sitter" 
         + "\n---------------------------------------\n"
         + "\n" + pList.getPetList()[0].toString() + "\n" 
         + "\n" + pList.getPetList()[1].toString() + "\n"
         + "\n" + pList.getPetList()[2].toString() + "\n"
         + "\n" + pList.getPetList()[3].toString() + "\n"
         + "\n" + pList.getPetList()[4].toString() + "\n", 
            pList.generateReport());
   }
   /**
    * Tests generateReportByOwner.
    *
    * @throws IOException from scanning input file.
    */
   @Test public void generateReportByOwnerTest() throws IOException
   {
      PetBoardingList pList = new PetBoardingList();
      pList.readPetFile("pet_boarding_data.csv");
      Pet [] pList2 = pList.getPetList();
      Assert.assertEquals("\n---------------------------------------"
         + "\nPet Boarding Report for Critter Sitter (by Owner)" 
         + "\n---------------------------------------\n"
         + "\n" + pList.getPetList()[4].toString() + "\n" 
         + "\n" + pList.getPetList()[2].toString() + "\n"
         + "\n" + pList.getPetList()[0].toString() + "\n"
         + "\n" + pList.getPetList()[3].toString() + "\n"
         + "\n" + pList.getPetList()[1].toString() + "\n", 
            pList.generateReportByOwner());
   }
   
   /**
    * Tests generateReportPetName.
    *
    * @throws IOException from scanning input file.
    */

   @Test public void generateReportByPetNameTest() throws IOException
   {
      PetBoardingList pList = new PetBoardingList();
      pList.readPetFile("pet_boarding_data.csv");
      Pet [] pList2 = pList.getPetList();
      Assert.assertEquals("\n---------------------------------------"
         + "\nPet Boarding Report for Critter Sitter (by Pet Name)" 
         + "\n---------------------------------------\n"
         + "\n" + pList.getPetList()[0].toString() + "\n" 
         + "\n" + pList.getPetList()[4].toString() + "\n"
         + "\n" + pList.getPetList()[1].toString() + "\n"
         + "\n" + pList.getPetList()[3].toString() + "\n"
         + "\n" + pList.getPetList()[2].toString() + "\n", 
            pList.generateReportByPetName());
   }
  
   /**
    * Tests generateReportPetName.
    *
    * @throws IOException from scanning input file.
    */

   @Test public void generateReportByBoardingCostTest() throws IOException
   {
      PetBoardingList pList = new PetBoardingList();
      pList.readPetFile("pet_boarding_data.csv");
      Pet [] pList2 = pList.getPetList();
      Assert.assertEquals("\n---------------------------------------"
         + "\nPet Boarding Report for Critter Sitter (by Boarding Cost)" 
         + "\n---------------------------------------\n"
         + "\n" + pList.getPetList()[3].toString() + "\n" 
         + "\n" + pList.getPetList()[2].toString() + "\n"
         + "\n" + pList.getPetList()[4].toString() + "\n"
         + "\n" + pList.getPetList()[1].toString() + "\n"
         + "\n" + pList.getPetList()[0].toString() + "\n", 
            pList.generateReportByBoardingCost());
   }
   
   /**
    * Tests generateExcludedRecordsReport.
    *
    * @throws IOException from scanning input file.
    */  
   @Test public void generateExcludedRecordsReportTest() throws IOException
   {
      PetBoardingList pList = new PetBoardingList();
      pList.readPetFile("pet_boarding_data.csv");
      String [] pList2 = pList.getExcludedRecords();
      Assert.assertEquals("\n---------------------------------------"
         + "\nExcluded Records Report" 
         + "\n---------------------------------------\n"
         + "\n" + pList.getExcludedRecords()[0].toString(),
            pList.generateExcludedRecordsReport());     
        
   }
}
