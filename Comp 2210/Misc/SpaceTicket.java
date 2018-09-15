import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Random;
import java.text.DecimalFormatSymbols;


public class SpaceTicket {
   static final double STUDENT_DISCOUNT = .25;
   static final double CHILD_DISCOUNT = .35;
   
   public static void main(String [] args) {
   
      String ticket;
      DecimalFormatSymbols slash = new DecimalFormatSymbols();
      slash.setDecimalSeparator('/'); 
      slash.setGroupingSeparator('/');
      
      DecimalFormatSymbols colon = new DecimalFormatSymbols();
      colon.setGroupingSeparator(':'); 
   
      DecimalFormat dateF = new DecimalFormat("00,00.0000", slash);
      DecimalFormat timeF = new DecimalFormat("00,00", colon);
      DecimalFormat priceF = new DecimalFormat("$#,###.00");
      DecimalFormat prizeF = new DecimalFormat("000000");
      
      Random spPrize = new Random();
      int prize = spPrize.nextInt(999998) + 1;
      
      Scanner userInput = new Scanner(System.in);
      System.out.print("Enter ticket code: ");
      ticket = userInput.nextLine();
      ticket = ticket.trim();
   
      if (ticket.length() < 25) {
      
         System.out.print("\n*** Invalid ticket code ***"
               + "\nTicket code must have at least 25 characters.");
      } 
      else {
      
      
         String spTicket = ticket.substring(24);
         String dateStr = ticket.substring(12,21);
      
         double date = Double.parseDouble(dateStr);
         date /= 10000;
      
         String timeStr = ticket.substring(9, 13);
      
         int time = Integer.parseInt(timeStr);
      
         String seat = ticket.substring(21,24);
      
         String priceStr = ticket.substring(0, 8);
         double price = Double.parseDouble(priceStr); 
         price /= 100;
      
         double cost = price;
      
         char category = ticket.charAt(8);
         if (category == 's') {
            cost *= (1 - STUDENT_DISCOUNT);       
         }
         if (category == 'c') {
            cost *= (1 - CHILD_DISCOUNT);       
         } 
        
         System.out.print("\nSpace Ticket: " + spTicket);
         System.out.println("\nDate: " + dateF.format(date) + "   Time: " 
            + timeF.format(time)+ "   Seat: " + seat); 
      
         System.out.println("Price: " +  priceF.format(price) 
            + "   Category: " + category + "   Cost: " +  
            priceF.format(cost));
         System.out.print("Prize: " + prizeF.format(prize));   
      
      }
   }
}
