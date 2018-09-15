/**
* Creates a dog object with commands.
* Project 09
*
* @author Kareith Dyce
* @version 11/02/16
*/

public class ServiceDog extends Dog
{
   private String service;
   private String [] commands;
   /**
   * interest rate for pet.
   */
   public static final double BASE_RATE = 13; 
   /**
   * Creates a dog object with commands.
   * 
   * @param ownerIn - name of owner
   * @param nameIn - name of pet
   * @param breedIn - name of breed
   * @param weightIn - weight of pet
   * @param daysIn - days boarded 
   * @param serviceIn - the service of the dog
   * @param commandsIn - the commands known
   */

   
   public ServiceDog(String ownerIn, String nameIn, String breedIn,
       double weightIn, int daysIn, String serviceIn, String ... commandsIn)
   {
      super(ownerIn, nameIn, breedIn, weightIn, daysIn);
      setService(serviceIn);
      setCommands(commandsIn);
   }

   /**
   * Gets the service of the dog.
   *
   * @return service of the dog
   */
   public String getService()
   {
      return service;
   }
   /**
   * Gets the commands of the dog.
   *
   * @return commands of the dog
   */

   public String[] getCommands()
   {
      return commands;
   }
   
   /**
   * Sets the service of the dog.
   *
   * @param serviceIn - the service of the dog.
   */

   public void setService(String serviceIn)
   {
      service = serviceIn;
   }
   
   /**
   * Sets the commands of the dog.
   *
   * @param commandsIn - the commands known
      */
   public void setCommands(String ... commandsIn)
   {
      commands = commandsIn;
   }
   
   /**
   * The cost of boarding the pet.  
   *
   * @return The cost of boarding the pet.
   */

   public double boardingCost()
   {
      return (BASE_RATE + weight * 0.05 + commands.length) * days;
   }
   
 /**
 * Returns a String representation of the pet object.
 * 
 * @return String representation of pet object.
 */

   public String toString()
   {
      String command = "";  
      for (int i = 0; i < commands.length; i++)
      {
         command += commands[i];
         if (i < commands.length - 1)
         {
            command += " ";
         }
      }
      return super.toString() + "   Service: " + service + "\nCommands: "
               + command;
   }
}
