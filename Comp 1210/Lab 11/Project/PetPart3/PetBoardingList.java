import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.util.NoSuchElementException;
/**
* Provides methods for reading in the data file and
* generating reports.
* Project 11
*
* @author Kareith Dyce
* @version 11/16/16
*/

public class PetBoardingList
{
   private String listName;
   private Pet[] petList = new Pet [100];
   private int petCount;
   private String [] excludedRecords = new String [30];
   private int excludedCount;
   private static int listCount = 0;
/**
* Provides methods for reading in the data file and
* generating reports.
* Project 10
*
*/
   public PetBoardingList()
   {
      listCount++;
   }  
   /**
   * Gets the String representing the listName.
   *
   * @return String representing the listName
   */
   public String getListName()
   {
      return listName;
   }
   
   
   /**
   * Gets the Pet array representing the petList.
   *
   * @return Pet array representing the petList
   */

   public Pet[] getPetList()
   {
      return petList;
   }
  
   /**
   * Gets the current value of petCount.
   *
   * @return the current value of petCount.
   */

   public int getPetCount()
   {
      return petCount;
   }
   
   /**
   * Gets the String array representing the excludedRecords.
   *
   * @return the String array representing the excludedRecords.
   */

   public String [] getExcludedRecords()
   {
      return excludedRecords;
   }
 
   /**
   * Gets the current value of excludedCount.
   *
   * @return the current value of excludedCount.
   */

   public int getExcludedCount()
   {
      return excludedCount;
   }
   
   /**
   * Static method that gets the current value of listCount.
   *
   * @return the current value of listCount.
   */
 
   public static int getListCount()
   {
      return listCount;
   }
   
   /**
   * Sets listCount to zero.
   */

   public static void resetListCount()
   {
      listCount = 0;
   }
   /**
   * Sets listName.
   *
   * @param listNameIn - name of the list.
   */

   public void setListName(String listNameIn)
   {
      listName = listNameIn;
   }
   /**
   * Sets petList.
   *
   * @param petListIn - array of pets.
   */

   public void setPetList(Pet [] petListIn)
   {
      petList = petListIn;
   }
     
     
   /**
   * Sets petCount.
   *
   * @param petCountIn - number of pets.
   */

   public void setPetCount(int petCountIn)
   {
      petCount = petCountIn;
   }
   
   /**
   * Sets excludedRecords.
   *
   * @param excludedRecordsIn - list of excluded pets.
   */

   public void setExcludedRecords(String [] excludedRecordsIn)
   {
      excludedRecords = excludedRecordsIn;
   }
   
   /**
   * Sets excludedCount.
   *
   * @param excludedCountIn - number of excluded pets.
   */
   public void setExcludedCount(int excludedCountIn)
   {
      excludedCount = excludedCountIn;
   }
   
   /**
   * Reads in a file of pets. If valid makes adds them to the petList otherwise
   * added to the excluded records.
   *
   * @param file - name of file.
   * @throws IOException from scanning input file.
   */
   public void readPetFile(String file) throws IOException
   {
      Scanner list = new Scanner(new File(file));
      listName = list.nextLine(); 
      String line = "";
      char typeP;  
      String owner, name, breed, service;
      String [] commands = new String [1000];
      double weight, exerciseFee;
      int days, lives;
      
      
      while (list.hasNextLine())
      {
         line = list.nextLine();
         Scanner pet = new Scanner(line);
         pet.useDelimiter(",");
         typeP = pet.next().charAt(0);
         try {            
            switch(typeP) {
               
               case ('C'):
                  owner = pet.next();
                  name = pet.next();
                  breed = pet.next();
                  weight = Double.parseDouble(pet.next()); 
                  days = Integer.parseInt(pet.next());
                  lives = Integer.parseInt(pet.next());
                  Cat c = new Cat(owner, name, breed, weight, days, lives);
                  petList[petCount] = c;
                  petCount++;
                  break;
               
               case('D'):
                  owner = pet.next();
                  name = pet.next();
                  breed = pet.next();
                  weight = Double.parseDouble(pet.next()); 
                  days = Integer.parseInt(pet.next());
                  Dog d = new Dog(owner, name, breed, weight, days);
                  petList[petCount] = d;
                  petCount++;
                  break;
                  
               case('S'):
                  owner = pet.next();
                  name = pet.next();
                  breed = pet.next();
                  weight = Double.parseDouble(pet.next()); 
                  days = Integer.parseInt(pet.next());
                  service = pet.next();
                  int i = 0;
                  while (pet.hasNext())
                  {
                     commands[i] = pet.next();
                     i++;
                  }
                  String[] command = new String[i];
                  for (int x = 0; x < i; x++)
                  {
                     command[x] = commands[x]; 
                  }
                  
                  ServiceDog d2 = new ServiceDog(owner, name, breed, weight, 
                     days, service, command);
                  petList[petCount] = d2;
                  petCount++;
                  break;
            
               case('H'):
                  owner = pet.next();
                  name = pet.next();
                  breed = pet.next();
                  weight = Double.parseDouble(pet.next()); 
                  days = Integer.parseInt(pet.next());
               
                  exerciseFee = Double.parseDouble(pet.next());
                  Horse h = new Horse(owner, name, breed, weight, days, 
                     exerciseFee);
                  petList[petCount] = h;
                  petCount++;
                  break;
            
               
               default:
               
                  throw new InvalidCategoryException(Character.toString(typeP));
            
            }
         } 
         
         catch (InvalidCategoryException e)
         {
            excludedRecords[excludedCount] = e + " in: " + line;
            excludedCount++;
         }
         catch (NumberFormatException e)
         {
            excludedRecords[excludedCount] = e + " in: " + line;
            excludedCount++;
         }
         catch (NoSuchElementException e)
         {
            excludedRecords[excludedCount] = e + " in: " + line;
            excludedCount++;
         }
      
                  
      }   
   }
   
   /**
   * Generates Report in orginal order. 
   *
   * @return the report.
   */ 
   public String generateReport()
   {
      String result = "\n---------------------------------------"
         + "\nPet Boarding Report for " + listName 
         + "\n---------------------------------------\n";
      for (int i = 0; i < petCount; i++)
      {
         result += "\n" + petList[i] + "\n"; 
      }
      return result;
   }
   /**
   * Generates Report in order by last name.
   *
   * @return the report.
   */
   public String generateReportByOwner()
   {
      String result = "\n---------------------------------------"
         + "\nPet Boarding Report for " + listName + " (by Owner)" 
         + "\n---------------------------------------\n";
      
      Pet temp1, temp2;
               
      for (int i = 0; i < petCount; i++)
      {
         for (int j = i; j < petCount; j++)
         {
            if (petList[i].compareTo(petList[j]) > 0)
            {
               temp1 = petList[i];
               temp2 = petList[j];
               petList[i] = temp2;
               petList[j] = temp1;
            }
         }
      
         
         result += "\n" + petList[i] + "\n"; 
      }
      return result;
   }
   
   /**
   * Generates Report in order by pet name.
   *
   * @return the report.
   */

   public String generateReportByPetName()
   {
      String result = "\n---------------------------------------"
         + "\nPet Boarding Report for " + listName + " (by Pet Name)" 
         + "\n---------------------------------------\n";  
      Pet[] pList2 = Arrays.copyOf(petList, petCount);
      Arrays.sort(pList2, new PetNameComparator());
      for (int i = 0; i < petCount; i++)
      {
         result += "\n" + pList2[i] + "\n";
      }
      return result;
   }
   /**
   * Generates Report in order by Boarding Cost.
   *
   * @return the report.
   */

   public String generateReportByBoardingCost()
   {
      String result = "\n---------------------------------------"
         + "\nPet Boarding Report for " + listName + " (by Boarding Cost)" 
         + "\n---------------------------------------\n";  
      Pet[] pList2 = Arrays.copyOf(petList, petCount);
      Arrays.sort(pList2, new BoardingCostComparator());
      for (int i = 0; i < petCount; i++)
      {
         result += "\n" + pList2[i] + "\n";
      }
      return result;
   }
   /**
   * Generates Excluded Report.
   *
   * @return the report.
   */

   public String generateExcludedRecordsReport()
   {
      String result = "\n---------------------------------------"
         + "\nExcluded Records Report" 
         + "\n---------------------------------------\n";
      for (int i = 0; i < excludedCount; i++)
      {
         result += "\n" + excludedRecords[i];
      }
      return result;
   
   }
}