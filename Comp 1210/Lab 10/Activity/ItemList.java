public class ItemList
{
   private InventoryItem [] inventory;
   private int count;
  
   public ItemList()
   {
      inventory = new InventoryItem[20];
      count = 0;  
   }
   
   public void addItem(InventoryItem itemIn)
   {
      inventory[count] = itemIn; 
      count++;  
   }
   
   public double calculateTotal(double electronicsSurcharge)
   {
      double total = 0;
      for(int i = 0; i < count; i++)
      {
         if(inventory[i] instanceof ElectronicsItem)
         {
            total += inventory[i].calculateCost() + electronicsSurcharge;
         }
         else
         {
            total += inventory[i].calculateCost();
         }   
         
      }
      return total;
   }
   
   public String toString()
   {
      String output = "All inventory:\n\n";
      
      for(int i = 0; i < count; i++)
      {
         output += inventory[i] + "\n";
      }
      return output;
   }
}