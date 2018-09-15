import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Random;
import java.text.DecimalFormatSymbols;

/**
*
*The purpose of this program is to accept coded game ticket information as 
*input that includes the ticket price, category, time, date, seat, row, 
*section, followed by the description of the game. 
*@author Kareith Dyce
*@version 9/6/16
*
*/

public class GameTicket
{
   static final double STUDENT_DISCOUNT = .85;
   static final double FACULTY_STAFF_DISCOUNT = .25;

   /**
   *
   *@param args Command line arguments (not used).
   *
   */

   public static void main(String [] args)
   {
      DecimalFormatSymbols slash = new DecimalFormatSymbols();
      slash.setDecimalSeparator('/'); 
      slash.setGroupingSeparator('/');
      
      DecimalFormatSymbols colon = new DecimalFormatSymbols();
      colon.setGroupingSeparator(':'); 
      
        
      DecimalFormat gamePrize = new DecimalFormat("000000");
      
      DecimalFormat gameTime = new DecimalFormat("##, ##", colon);
      
      DecimalFormat gamePrice = new DecimalFormat("$#,###.00");
      
      DecimalFormat gameDate = new DecimalFormat("#,00,00.0000", slash);
      
   
      Scanner userInput = new Scanner(System.in);
      Random gPrize = new Random();
              
      String ticket = "";
      String gameTicket;
      String game, date, time,
         section, row, seat, priceString; 
      char category;
      
      int prize = gPrize.nextInt(999998) + 1;
      int gRow, gSeat, gSection; 
      
      double cost, gTime, price, gDate; 
   
      
      System.out.print("Enter your ticket code: ");
      ticket = userInput.nextLine();
      System.out.println();
      gameTicket = ticket.trim();
      if (ticket.length() < 25)
      {
         System.out.print("\nInvalid Ticket Code."
            + "\nTicket code must have at least 25 characters.");
      }
      else
      {
      
         priceString = gameTicket.substring(0, 5);
         category = gameTicket.charAt(5);   
         time = gameTicket.substring(6, 10);
         date = gameTicket.substring(11, 18);
         seat = gameTicket.substring(18, 20);
         row = gameTicket.substring(20, 22);
         section = gameTicket.substring(22, 24);
         game = gameTicket.substring(24);
         price = Double.parseDouble(priceString);
         
         price /= 100;
         
         gSection =  Integer.parseInt(section);
         gSeat =  Integer.parseInt(seat);
         gRow =  Integer.parseInt(row);
         gDate =  Double.parseDouble(date);
         gTime =  Double.parseDouble(time);
         
         gDate /= 10000;
         
         if (category == ('s'))
         {
            cost = price - (price * STUDENT_DISCOUNT); 
         }
         if (category == ('f'))
         {
            cost = price - (price * FACULTY_STAFF_DISCOUNT); 
         } 
         else
         {
            cost = price;
         }
         System.out.print("Game: " + game + "   Date: "
            + gameDate.format(gDate) 
            + "   Time: " + gameTime.format(gTime) 
            + "\nSection: " + gSection 
            + "   Row: " + gRow + "   Seat: " + gSeat  
            + "\nPrice: " + gamePrice.format(price)
            + "   Category: " + category + "   Cost: " + gamePrice.format(cost)
            + "\nPrize Number: " + gamePrize.format(prize));
      }
      
      
   }
         
}