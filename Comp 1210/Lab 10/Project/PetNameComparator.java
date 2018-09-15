import java.util.Comparator;
   /**
* A sorter that sorts by petName.
* Project 10
*
* @author Kareith Dyce
* @version 11/02/16
*/

public class PetNameComparator implements Comparator<Pet>
{
   
   /**
   * A sorter that sorts by petName.
   *
   * @param p1 - pet to be sorted 
   * @param p2 - pet to be sorted
   * @return difference between pets.
   */ 
   public int compare(Pet p1, Pet p2)
   {
      if (p1.getName().compareTo(p2.getName()) < 0)
      {
         return -1; 
      }
      else if (p1.getName().compareTo(p2.getName()) > 0)
      {
         return 1; 
      }
      else
      {
         return 0;
      }
   }
}