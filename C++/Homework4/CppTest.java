public class CppTest{
   public static void main(String [] args){
      int []array1 = {1,3,5,6,8};//[MAX_SIZE];
      int array1_size = 5;
      int []array2 = {1,4,7,9};//[MAX_SIZE];
      int array2_size = 4;
      int array3[] = new int[100];
      int array3_size;
    
      int index = 0,i1 = 0,i2 = 0;
      for (index = 0; index <= (array1_size + array2_size)-1; index++){
         if((i1 < array1_size) && (i2 < array2_size) && (array1[i1] < array2[i2])) {
            array3[index] = array1[i1];
            i1++;
         }
         else if((i2 < array2_size)) {
            array3[index] = array2[i2];
            i2++;
         }
         else{
            array3[index] = array1[i1];
            i1++;
         
         }
      }
    
      for(int i = 0; i < index; i++) {
        
      }
    
   }

}