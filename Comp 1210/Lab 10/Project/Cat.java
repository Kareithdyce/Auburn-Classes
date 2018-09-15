/**
* Creates a cat object of pet.
* Project 09.
*
* @author Kareith Dyce
* @version 11/02/16
*/

public class Cat extends Pet
{
   private int livesLeft;
   /**
   * interest rate for pet.
   */
   public static final double BASE_RATE = 10;
   
   
   /**
   * Creates a dog object.
   * 
   * @param ownerIn - name of owner
   * @param nameIn - name of pet
   * @param breedIn - name of breed
   * @param weightIn - weight of pet
   * @param daysIn - days boarded 
   * @param livesLeftIn - livesleft
   */
   public Cat(String ownerIn, String nameIn, String breedIn, double weightIn, 
              int daysIn, int livesLeftIn)
   {
      super(ownerIn, nameIn, breedIn, weightIn, daysIn);
      setLivesLeft(livesLeftIn);     
   }
   
   /**
   * returns the amount of lives left.
   * 
   * @return livesleft
   */
   public int getLivesLeft()
   {
      return livesLeft;
   }
   
   /**
   * sets the amount of lives left.
   * 
   * @param livesLeftIn - livesleft
   */

   public void setLivesLeft(int livesLeftIn)
   {
      livesLeft = livesLeftIn;
   }   

   /**
   * The cost of boarding the pet.  
   *
   * @return The cost of boarding the pet.
   */

   public double boardingCost()
   {
      return (BASE_RATE + weight * 0.10) * days * (9.0 / livesLeft);
   }

 /**
 * Returns a String representation of the pet object.
 * 
 * @return String representation of pet object.
 */

   public String toString()
   {
      return super.toString() + "   Lives Left: " + livesLeft; 
   }
}