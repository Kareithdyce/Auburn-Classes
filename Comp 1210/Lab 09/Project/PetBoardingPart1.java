/**
* Creates a driver for pet.
* Project 09.
*
* @author Kareith Dyce
* @version 11/02/16
*/


public class PetBoardingPart1
{
 /**
* Creates a driver for pet.
*
* @param args - does nothing.
*/
   public static void main(String [] args)
   {
      Cat c = new Cat("Barb Jones", "Callie", "Siamese", 9.0, 7, 9);
      Dog d = new Dog("Jake Smith", "Honey", "Great Dane", 60, 7);
      ServiceDog d2 = new ServiceDog("Jen Baker", "Pepper", "Sheppard", 60, 7,
         "guide dog",
         "sit", "down", "stay", "come", "around",
         "forward", "right", "left");
      Horse h = new Horse("Jessie Rider", "King", "Quarter Horse", 
         1000, 7, 10.0);   
          
      System.out.println(c);
      System.out.println();
      System.out.println(d);
      System.out.println();
      System.out.println(d2);
      System.out.println();
      System.out.println(h);   
   }
}