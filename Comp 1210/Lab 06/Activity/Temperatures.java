import java.util.ArrayList;

public class Temperatures
{
   ArrayList<Integer> temperatures = new ArrayList<Integer>();
   
   public Temperatures(ArrayList<Integer> temperaturesIn)
   {
      temperatures = temperaturesIn;
   
   }
   
   public int getLowTemp()
   {
      if(temperatures.isEmpty())
      {
         return 0;
      }
      
      int low = temperatures.get(0);
      for(int i = 1; i < temperatures.size(); i++)
      {
         if(temperatures.get(i) < low)
         {
         low = temperatures.get(i);
         }
      }  
   
   return low;
   } 
   
   
   public int getHighTemp()
   {
   
      if(temperatures.isEmpty())
      {
         return 0;
      }
      
      int high = temperatures.get(0);
       
      for(Integer temp : temperatures)
      {
         
         if(temp > high)
         {
         high = temp;
         
         }
      }  
   
   return high;
   
   }  
   
   public int lowerMinimum(int lowIn)
   {
      return lowIn < getLowTemp() ? lowIn : getLowTemp();
   } 
  
   public int higherMaximum(int highIn)
   {
      return highIn > getHighTemp() ? highIn : getHighTemp();

   } 
     
   public String toString()
   {
      return "\tTemperatures: "+ temperatures
           + "\n\tLow " + getLowTemp()
           + "\n\tHigh " + getHighTemp();
           
   }
   
   
   
}
