/**
* Creates a dog object of pet.
* Project 09.
*
* @author Kareith Dyce
* @version 11/02/16
*/

public class Dog extends Pet
{
   /**
   * interest rate for pet.
   */
   public static final double BASE_RATE = 12;
   /**
   * Creates a dog object.
   * 
   * @param ownerIn - name of owner
   * @param nameIn - name of pet
   * @param breedIn - name of breed
   * @param weightIn - weight of pet
   * @param daysIn - days boarded 
   *
   */
   
   public Dog(String ownerIn, String nameIn, String breedIn, double weightIn, 
              int daysIn)
   {
      super(ownerIn, nameIn, breedIn, weightIn, daysIn);
   } 
   /**
   * The cost of boarding the pet.  
   *
   * @return The cost of boarding the pet.
   */

   public double boardingCost()
   {
      return (BASE_RATE + weight * 0.05) * days;
   }

}
