import java.util.*;
public class Question1{
   public static void main(String[] args) {
     
   }
   public static boolean solution(int total_money, int total_damage, int[] costs, int[] damages) {
      int money = 0;
      int damage = 0;
      for(int i = 0; i < costs.length; i++){
         money += costs[i];
         damage += damages[i];
         if(damage > total_damage || money > total_money){
            money = 0;
            damage = 0;
         }else if( total_damage == damage){
            return true;
         }else{
            for(int j = i; j < costs.length; j++){
               money += costs[j];
               damage += damages[j];
               if(damage > total_damage || money > total_money){
                  money -= costs[j];
                  damage -= damages[j];
               
               }else if( total_damage == damage){
                  return true;
               }   
            
            }
         }
      }
      return false;
   }
}