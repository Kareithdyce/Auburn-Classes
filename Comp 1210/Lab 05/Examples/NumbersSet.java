
/** 
 * A NumbersSet object represents two positive integer values and provides 
 * information about the numbers between those two values 
 * (including the two values themselves).
 */
public class NumbersSet 
{
   private int low, high; 

/** 
  * Initializes a NumbersSet object given two positive integers.
  * The integers do not have to be in a specific order. 
  *
  * @param number1 Any positive integer 
  * @param number2 Any other positive integer
  */
   public NumbersSet(int number1, int number2) 
   {
      if (number1 < number2) {
         low = number1;
         high = number2;
      }
      else {
         high = number1;
         low = number2;
      }
   }
   
   public int getLow() {
      return low;
   }
	
   public int getHigh() {
      return high;
   }
   
   /** 
	 * Returns a String including all even numbers 
	 * between the two positive integers inclusive.          
	 *
	 * @return all even numbers between the two numbers inclusive.
	 */
   public String findEvensBetween() {  
      int count = low;
      String evens = "";
   	
      while (count <= high) {
         if (count % 2 == 0) {
            evens += count + " ";
         }
         count++;
      }
      	
      return evens;
   } 
   
   /** 
	 * Returns a String including divisors common to the 2 integers.         
	 *
	 * @return all common divisors of the two numbers
	 */
   public String findCommonDivisors() {  
      int count = 1;
      String divisors = "";
   	
      while (count <= low) {
         if (low % count == 0 && high % count == 0) {
            divisors += count + " ";
         }
         count++;
      }
      
      return divisors;
   } 

   /** 
	 * Returns a String representation of the two numbers represented
	 * by this TwoIntegers object.
	 *
	 * @return string representation of the two integers
	 */
   public String toString() {  
      return low + ", " + high;
   } 
}
