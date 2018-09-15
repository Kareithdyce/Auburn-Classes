
/**
* Error message for invaild category.
* Project 11
*
* @author Kareith Dyce
* @version 11/17/16
*/


public class InvalidCategoryException extends Exception
{
    /**
     * Error message for invaild category.
     *
     * @param category  - type of error.
     */  
   public InvalidCategoryException(String category)
   {
      super("For category: " + "\"" + category + "\"");
   
   }
}