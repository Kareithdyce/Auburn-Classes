public class Customer implements Comparable <Customer>

{ 
   private String name, location;
   private double balance;

   
   public Customer(String nameIn) 
   {
      name = nameIn;
      location = "";
      balance = 0;
   }

   public void setLocation(String locationIn)
   {
      location = locationIn;
   }
   public void changeBalance(double amount)
   {
      balance += amount;
   }
   public String getLocation()
   {
      return location;
   }
   public void setLocation(String city, String state)
   {
      location = city + ", " + state;
   }
   
   public double getBalance()
   {
      return balance;
   }
   
   public int compareTo(Customer obj)
   { 
      if(this.balance > obj.getBalance())
      {
         return  1;  
      } 
      else if(this.balance < obj.getBalance())
      {
         return -1;
      }
      else
      {
         return 0;
      }
   }  
   public String toString()
   {
      String result = name + "\n" + location + "\n$" + balance; 
      return result;
   }
   public static void main(String [] args)
   {
      Customer cstmr1 = new Customer("John");
      cstmr1.changeBalance(10);
      cstmr1.setLocation("Boston, MA");
      System.out.println(cstmr1);
      
      Customer cstmr2 = new Customer("JoAnn");
      cstmr2.changeBalance(73);
      cstmr2.setLocation("Auburn, AL");
      System.out.println(cstmr2 + "\r\n");
      
      if(cstmr1.compareTo(cstmr2) > 0)
      {
         System.out.println("Higher Balance: " + cstmr1); 
      }
      else if(cstmr2.compareTo(cstmr1) > 0)
      {
         System.out.println("Higher Balance: " + cstmr2); 
      }
      else
      {
         System.out.println("Balances are equal.");
      }
   }
     





}



