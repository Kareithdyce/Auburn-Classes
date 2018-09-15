public class Question2{
   public static void main(String[] args) {
        //Solution s = new Solution();
      System.out.print(solution("abcd","ac"));
   
   }
    //class Solution {
   public  static int solution(String S, String T) {
      int counter = 0;
      int remain = S.length();  
      for(int i = 0; i < T.length(); i++){      
         if(S.charAt(i+counter) != T.charAt(i)){
            counter++;
            i--;
         }
         if(counter >= S.length()-1){
            return 0;
         }       
         remain--;
      }
      return counter + remain;        
   }
    //}
}
