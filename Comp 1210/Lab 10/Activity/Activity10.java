public class Activity10
{
   public static void main(String [] args)
   {
      ItemList myItems = new ItemList();
      InventoryItem.setTaxRate(.05);
      
      myItems.addItem(new ElectronicsItem("laptop", 1234.56, 10));
      myItems.addItem(new InventoryItem("motor oil", 9.8));
      myItems.addItem(new OnlineBook("All Things Java", 12.3));
      myItems.addItem(new OnlineArticle("Useful Acronyms", 3.4));
      System.out.println(myItems);
      
      System.out.println("Total: " + myItems.calculateTotal(2.0));
   
   } 
}