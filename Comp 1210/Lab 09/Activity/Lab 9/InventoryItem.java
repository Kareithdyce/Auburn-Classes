public class InventoryItem
{
   protected String name;
   protected double price;
   private static double taxRate  = 0;
   
   public InventoryItem(String nameIn, double priceIn) 
   {
    name = nameIn;
    price = priceIn;
   }

   public String getName() 
   {
      return name;
   }
   
   public double calculateCost()
   {
      double tax = price  * (1 + taxRate);  
      return tax;
   }
   
   public static void setTaxRate(double taxRateIn)
   {   
      taxRate = taxRateIn;
   }
   
   public String toString() 
   {
      return name + ": $" + calculateCost();
   }
   
}