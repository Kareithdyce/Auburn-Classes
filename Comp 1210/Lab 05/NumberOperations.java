public class NumberOperations 
{  
   private int number;
  
 

   public NumberOperations (int numberIn)// sets NumberIn = number
   {
      number = numberIn;
   }
  
   public int getValue()
   {
      return number;
   }
  
   public String oddsUnder() //finds odd numbers
   {
      String output = "";
      int i = 0;
      while (i < number)
      {
         if (i % 2 != 0)
         {
            output += i + "\t";
         } 
         i++;
      }
      return output;
   }  
  
   public String powersTwoUnder() //finds the powers of two below the number
   {
      String output = "";
      int powers = 1;
      
      while (powers < number)
      {
      output += powers + "\t"; 
      powers = powers * 2; 
      }
      return output;
   }
  
   public int isGreater(int compareNumber)
   {
   if (number > compareNumber)
      {
      return 1;
      }
   
   else if (number < compareNumber)
      {
      return -1;
      }
   
   
   
   else{
      return 0;
   }
   }
   public String toString()
   {
      return number + "";
   }
   


}