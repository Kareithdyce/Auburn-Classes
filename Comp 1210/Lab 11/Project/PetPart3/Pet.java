import java.text.DecimalFormat;
/**
* Create an abstract Pet class that stores pet data and provides methods 
* to access the data.
* Project 10
*
* @author Kareith Dyce
* @version 11/02/16
*/
public abstract class Pet implements Comparable <Pet>
{
   protected String owner, name, breed;
   protected double weight;
   protected  int days;
   protected static int count = 0;
   /**
   * Create an abstract Pet class that stores pet data and provides
   * methods to access the data.
   * @param ownerIn - name of owner
   * @param nameIn - name of pet
   * @param breedIn - name of breed
   * @param weightIn - weight of pet
   * @param daysIn - days boarded 
   *
   */
   
   public Pet(String ownerIn, String nameIn, String breedIn, double weightIn, 
              int daysIn)
   {
      setOwner(ownerIn);
      setName(nameIn);
      setBreed(breedIn);
      setWeight(weightIn);
      setDays(daysIn); 
      count++;
   }
   
   /**
   *  Gets the name of the owner.
   *
   * @return name of owner. 
   */ 
   public String getOwner()
   {
      return owner;
   }
   
   /**
   *  Gets the name of the pet.
   *
   * @return name of pet. 
   */
   public String getName()
   {
      return name;
   }
     
   /**
   *  Gets the breed of the pet.
   *
   * @return breed of pet. 
   */   
   public String getBreed()
   {
      return breed;
   }
    
   /**
   * Gets the weight of pet.
   *
   * @return weight of pet. 
   */   
   public double getWeight()
   {
      return weight;
   }
   
   /**
   *Gets the days.
   *
   *@return days. 
   */   
   public int getDays()
   {
      return days;
   }
   
   /**
   * Gets the number of pet objects.
   *
   * @return name of pet objects. 
   */   
   public static int getCount()
   {
      return count;
   }
   
   /**
   * Sets count equal to zero.
   */
   public static void resetCount()
   {
      count = 0;
   }
   
   /**
   * Sets the name of the owner.
   *
   * @param ownerIn - name of owner
   */
   
   public void setOwner(String ownerIn)
   {
      owner = ownerIn;
   }
   
   /**
   * Sets the name of the pet.
   *
   * @param nameIn - name of pet
   */ 
   public void setName(String nameIn)
   {
      name = nameIn; 
   }
   
   /**
   * Sets the breed of the pet.
   *
   * @param breedIn - breed of pet
   */ 

   public void setBreed(String breedIn)
   {
      breed = breedIn;
   }
   
   /**
   * Sets the weight of the pet.
   *
   * @param weightIn - weight of pet
   */ 

   public void setWeight(double weightIn)
   {
      weight = weightIn;
   }
   
   /**
   * Sets the number of days staying.
   *
   * @param daysIn - number of days staying
   */ 

   public void setDays(int daysIn)
   {
      days = daysIn;
   }
   /**
   * The cost of boarding the pet.  
   *
   * @return The cost of boarding the pet.
   */
   public abstract double boardingCost();
   /**
   * Compares pet objects based owner's name (Last name, First name).
   * @param p the pet being compared.
   * @return an int indicating the ordering from
   * lowest to highest based on the owner’s last name followed by first name.
   */
   public int compareTo(Pet p)
   {
      String name1 = this.owner.toLowerCase();
      String name2 =  p.getOwner().toLowerCase();
      
      int i = name1.indexOf(' ');
      String lName = name1.substring(i);
      String fName = name1.substring(0, i);
      name1 = lName + " " + fName;  
      i = p.owner.indexOf(' ');
      lName = name2.substring(i);
      fName = name2.substring(0, i);
      name2 = lName + " " + fName;
      name1 = name1.trim(); 
      name2 = name2.trim();
      return name1.compareTo(name2);
   }
   
   
   
   
/**
 * Returns a String representation of the pet object.
 * 
 * @return String representation of pet object.
 */

   
   public String toString()
   {
      DecimalFormat cost = new DecimalFormat("$#,##0.00");
      String result = "Owner: " + owner + "   Pet: " + name 
                 + "   Days: " + days + "   Boarding Cost: " 
                 + cost.format(boardingCost()) + "\n" 
                 + this.getClass().toString().substring(6) + ": "
                 + breed + "   Weight: " + weight + " lbs";      
      
      return result;   
   }
 

      
   
}