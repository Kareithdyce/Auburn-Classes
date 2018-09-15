import java.util.Scanner;
public class Test {
   private static long peek = 0; 
   public static void main(String[] args) {
      Scanner reader = new Scanner(System.in);
   
      System.out.print("Enter a number greater than 2: ");
      long n = reader.nextLong();
      long f = factorial(n); 
      System.out.print(f);
   }

   public static long factorial(long n) {
      if (n == 1) {
         return 1;
      }
      //peek = factorial(n - 1) * n;
      return factorial(n - 1) * n;     
   }




/*

      Scanner reader = new Scanner(System.in);
      int f = 0;
      int f1 = 0;
      int f2 = 1;
      int n;
      int count = 2;
      System.out.print("Enter a number greater than 2: ");
      n = reader.nextInt();
      do {
         f = f1 + f2;

         f1 = f2;

         f2 = f;
         count++;
      }
      while(count < n);
      System.out.print("The " + n + "th term is " + f);
   }

*/
}
