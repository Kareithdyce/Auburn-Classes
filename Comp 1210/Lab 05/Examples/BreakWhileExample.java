class BreakWhileExample {

   public static void main(String[] args) {
   
      String searchMe = "how much wood could a woodchuck chuck " 
            + "if a woodchuck could chuck wood";
      char searchForLast = 'h';
      int index = searchMe.length() - 1;
   
      boolean foundIt = false;
      
      while (index >= 0) {
         // interested only in last h
         if (searchMe.charAt(index) == searchForLast) {
            foundIt = true;
            break;
         }
         
         index--;
      }
      
      if (foundIt) {
         System.out.println("Found last " + searchForLast 
                           + " at index " + index);
      } 
      else {
         System.out.println(searchForLast + " not in the array");
      }
   }
}