   import java.util.Scanner;
   import java.text.DecimalFormat;

/**
 * Reads the user's income and prints tax bracket
 * accordingly.
 * @author J. Cross
 */
   public class TaxesMore
   {
   
      private static final int BRACKET = 10000;
   
   /**
    * Reads the user's income and prints tax bracket
    * accordingly.
    * @param args Standard command line arguments.
    */
      public static void main(String[] args)
      {
         DecimalFormat fmt = new DecimalFormat("$#,##0.00");
         Scanner scan = new Scanner(System.in);
         boolean getData = true;
         while (getData) {
            System.out.print("Enter your income: ");
            int income = scan.nextInt(); scan.nextLine();
            System.out.print("Are you married (Y/N): ");
            String m = scan.nextLine();
            boolean married = m.toUpperCase().equals("Y"); 
         
            System.out.println("You entered: " + income + " " + m);
         
            if (income > BRACKET) {
               if (married) {
                  System.out.println("You are married and owe: 25% of " 
                     	+ fmt.format(income) + " which is " 
                     	+ fmt.format(.25 * income));
               }
               
               else {
                  System.out.println("You are not married and owe: 30% of " 
                     	+ fmt.format(income) + " which is " 
                     	+ fmt.format(.30 * income));
               }
            }
            else
            {
               System.out.print("You owe: 5% of ");
               System.out.print(fmt.format(income) + " or "); 
               System.out.println(fmt.format(.05 * income));
            }					
            System.out.println("");
         
            System.out.print("Enter more data? (Y/N): ");
            String moreData = scan.nextLine();
            getData = moreData.equalsIgnoreCase("Y");
         }
         System.out.println("\nDone");
      }
   }
