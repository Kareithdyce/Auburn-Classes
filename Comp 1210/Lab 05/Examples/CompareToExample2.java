public class CompareToExample2 {

   public static void main(String[] args) {
   
      String food1 = "apple", food2 = "Carrot";
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
