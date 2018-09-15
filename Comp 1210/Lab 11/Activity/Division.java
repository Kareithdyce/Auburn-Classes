public class Division
{
  

   public static int intDivide(int num, int denom)
   {
      try
      {
         return num /denom;
      }
      catch(ArithmeticException e)
      {
         return 0;
      }
   }
   
   public static double decimalDivide(int num, int denom)
   {
      if (denom == 0) {
         throw new IllegalArgumentException("The denominator "
            + "cannot be zero.");
      }
      else
      {
         return (double)num / denom;
      }
   }
}