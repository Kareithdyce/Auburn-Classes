public class CompareToExample1 {

   public static void main(String[] args) {
   
      String food1 = "Apple", food2 = "Banana";
      if (food1.compareTo(food2) < 0) {
         System.out.println(food1 + " before " + food2);
      }
      else if (food1.compareTo(food2) > 0) { 
         System.out.println(food2 + " before " + food1);
      }
      else {
         System.out.println(food2 + " and " + food1 
                            + " are the same");
      }
   }
}
