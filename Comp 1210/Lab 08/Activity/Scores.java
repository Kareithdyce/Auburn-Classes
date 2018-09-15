public class Scores
{
   private int[] numbers;
   
   public Scores(int[] numbersIn)
   {
      numbers = numbersIn;
   }
   public int[] findEvens()
   {
      int numberEvens = 0;
      
      for (int i = 0; i < numbers.length; i++)
      {
         if (numbers[i] % 2 == 0)
         {
            numberEvens++;
         }
      }
      int[] evens = new int[numberEvens];
      
      int count = 0;
      for(int i = 0; i < numbers.length; i++)
      {
         if (numbers[i] % 2 == 0)
         {
            evens[count] = numbers[i];
            count++;
         }
      } 
      return evens;
   } 
   
   public int[] findOdds()
   {
      int numberOdds = 0;
      
      for (int i = 0; i < numbers.length; i++)
      {
         if (numbers[i] % 2 == 1)
         {
            numberOdds++;
         }
      }
      int[] odds = new int[numberOdds];
      
      int count = 0;
      for(int i = 0; i < numbers.length; i++)
      {
         if (numbers[i] % 2 == 1)
         {
            odds[count] = numbers[i];
            count++;
         }
      } 
      return odds;
   }
   public double calculateAverage()
   {
      int sum  = 0;
      for (int i = 0; i < numbers.length; i++)
      {
         sum += numbers[i];
      }
      return (sum) / (double)(numbers.length);
   }
   public String toString()
   {
      String result = "";
     
      for(int i = 0; i < numbers.length; i++)
      {
         result += numbers[i] + "\t";
      }
      return result;
   }
   public String toStringInReverse()
   {
      String result = "";
     
      for(int i = numbers.length - 1; i >= 0;  i--)
      {
         result += numbers[i] + "\t";
      }
      return result;
   }

}
