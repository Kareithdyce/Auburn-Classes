import java.util.Arrays;
import java.util.ArrayDeque;
public class Queen{
   public static int board[][] = new int[8][8];
   public static boolean isLegal(int row, int col){
      for(int i = 0; i <= row; i++){
         if(board[i][col] == 1){
            return false;
         }
      }
      for(int j = 0; j <= col; j++){
         if(board[row][j] == 1){
            return false;
         }
      }
      int i = row - 1;
      int j = col - 1;
      int j2 = col + 1;
      while(i >= 0){
         if(j >= 0 && board[i][j] == 1){
            return false;
         }
         if(j2 < 8 && board[i][j2] == 1){
            return false;
         }
         i--;
         j--;
         j2++;
      }
      return true;
   }
   public static void main(String[] args) {
      int counter = 0;
      ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
      for(int i = 0; i < 8; i++){
         for(int j = 0; j < 8; j++){
            if(isLegal(i,j)){
               //System.out.println("row: " + i + " col: " + j);
               stack.push(j);
               board[i][j] = 1;
               if(i == 6){
                  for(int k = 0; k < 8; k++){
                     System.out.println(Arrays.toString(board[k]));
                  }
                  System.out.println();
                  board[i][j] = 0;
                  j = stack.pop();
                  i--;
                  board[i][j] = 0;
                  counter++;
               }
               break;
            }
            while(j == 7){
               j = stack.pop();
               i--;
               board[i][j] = 0;
            }
         } 
      }
      /*for(int i = 0; i < 8; i++){
         System.out.println(Arrays.toString(board[i]));
      }
      */
      System.out.print(counter);
   }
}