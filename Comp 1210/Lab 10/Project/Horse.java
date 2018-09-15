import java.text.DecimalFormat;
/**
* Creates a horse object of pet.
* Project 09.
*
* @author Kareith Dyce
* @version 11/02/16
*/
public class Horse extends Pet
{
   private double exerciseFee;
   /**
   * interest rate for pet.
   */
   public static final int BASE_RATE = 15;

   /**
   * Creates a horse object of pet.
   * @param ownerIn - name of owner
   * @param nameIn - name of pet
   * @param breedIn - name of breed
   * @param weightIn - weight of pet
   * @param daysIn - days boarded 
   * @param exerciseFeeIn - fee for exercising 
   */
   public Horse(String ownerIn, String nameIn, String breedIn, double weightIn, 
              int daysIn, double exerciseFeeIn)
   {
      super(ownerIn, nameIn, breedIn, weightIn, daysIn);
      setExerciseFee(exerciseFeeIn);
   }
   
   
   /**
   * Gets the fee for exercising.
   *
   * @return fee for exercising
   */
   public double getExerciseFee()
   {
      return exerciseFee;
   }
   
   
   /**
   * sets the fee for exercising.
   *
   * @param exerciseFeeIn - fee for exercising
   */
   public void setExerciseFee(double exerciseFeeIn)
   {
      exerciseFee = exerciseFeeIn;
   }
   
   /**
   * The cost of boarding the pet.  
   *
   * @return The cost of boarding the pet.
   */

   public double boardingCost()
   {
      return (BASE_RATE + weight * 0.01 + exerciseFee) * days; 
   }
/**
 * Returns a String representation of the pet object.
 * 
 * @return String representation of pet object.
 */

   public String toString()
   {
      DecimalFormat cost = new DecimalFormat("$#,##0.00");
      return super.toString() + "   Exercise Fee: " + cost.format(exerciseFee);
   }
}