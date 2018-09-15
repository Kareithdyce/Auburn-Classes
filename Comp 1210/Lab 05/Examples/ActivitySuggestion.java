import java.util.Scanner;

public class ActivitySuggestion {

   public static void main(String args[])
   {
   //1. prompt the user for a temperature
      System.out.print ("Please enter temperature: ");
   
      Scanner scan = new Scanner (System.in);
   //2. read in temp
      int temp = scan.nextInt();
   
   //3. prompt user if it is raining or not and read it in
      System.out.print ("Is it raining? (enter yes or no) ");
      String raining = scan.next();
   
   //4. using if-else suggest appropriate activities
   //   to guests based on the weather
      String activity ="";
   
      if (temp<40){
         activity = "skiing";
      }
      else{
         if (temp<60 && raining.equals("no")){
            activity = "golf";
         }
         else {
            if (temp<80 && raining.equals("no")){
               activity = "tennis";
            }
            else 
               if  (temp>=80 && raining.equals("no")){
                  activity = "swimming";
               }
               else{
                  activity = "bowling";
               }
         }
      }
      
      // 5. print out the activity appropriate for that temperature.    
      System.out.println ("Suggested activity = " + activity);
   }
}
