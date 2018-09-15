import java.util.Arrays;
   
/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Kareith Dyce(kzd0030@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2017-01-19
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    *
    * @param a - the array being searched.
    * @return min - value in the array.
    */
   public static int min(int[] a) {
      int min = Integer.MAX_VALUE;
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < a.length; i++) {
         
         if (min > a[i]) {
            min = a[i];
         }
      }
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    *
    * @param a - the array being searched.
    * @return - the max value in the array.
    */
   public static int max(int[] a) {
     
      int max = Integer.MIN_VALUE;
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < a.length; i++) {
         if (max < a[i]) {
            max = a[i];
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    *
    * @param a - the array being searched.
    * @param k - the min value being searched. 
    * @return - kth minimum value from the array a.
    */
   public static int kmin(int[] a, int k) {
      if ((a == null) || (a.length == 0) || (k < 1) || (k > a.length)) {
         throw new IllegalArgumentException();
      }
      int [] temp = new int[a.length];
      int [] sort = Arrays.copyOf(a, a.length);
      Arrays.sort(sort);
      int diff = 0;
      int store = 0;
      
      for (int i = 0; i < temp.length; i++) {
         if (sort[i] != store) {
            temp[diff] = sort[i];
            store = temp[diff];
            diff++; 
         } 
      }
      if (diff < k) {
         throw new IllegalArgumentException();
      }
      int min = temp[k - 1];
      return min;
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    *
    * @param a - the array being searched.
    * @param k - the max value being searched. 
    * @return - kth maximum value from the array a.

    */
   public static int kmax(int[] a, int k) {
      
      if ((a == null) || (a.length == 0) || (k < 1) || (k > a.length)) {
         throw new IllegalArgumentException();
      }
      int [] temp = new int [a.length];
      int [] sort = Arrays.copyOf(a, a.length);
      Arrays.sort(sort);
      int diff = 0;
      int store = 0;
      
      for (int i = 0; i < temp.length; i++) {
         if (sort[i] != store) {
            temp[diff] = sort[i];
            store = temp[diff];
            diff++; 
         } 
      }
      if (diff < k) {
         throw new IllegalArgumentException();
      }
      temp = Arrays.copyOf(temp, diff);
      Arrays.sort(temp);
        
      int max = temp[temp.length - k];
      
         
      return max;
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    *
    * @param a - the array being searched.
    * @param low - the min value. 
    * @param high - max value.
    *
    * @return - an array containing all the values in a in the
    *     range [low..high]
    */
   public static int[] range(int[] a, int low, int high) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      int count = 0;
      int [] empty = new int [0];
      int [] range  =  new int [a.length]; 
      
      for (int i = 0; i < a.length; i++) {
         if ((low <= a[i]) && (high >= a[i])) {
            range[count] = a[i];
            count++; 
         }
      }
      if (count == 0) {
         return empty;
      } 
      range = Arrays.copyOf(range, count);
      return range;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    *
    * @param a - the array being searched.
    * @param key - the number that splits the array. 
    * @return smallest value in a that is greater than or equal to
    *     the given key.
    */
   public static int ceiling(int[] a, int key) {
      int ceiling = Integer.MAX_VALUE;
      boolean exists = false;
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < a.length; i++) {
         if (key <= a[i] && ceiling > a[i]) {
            ceiling = a[i];
            exists = true; 
         }
      }
      if (!exists) {
         throw new IllegalArgumentException();
      }
      return ceiling;
      
   }
   
   


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    * 
    * @param a - the array being searched.
    * @param key - the number that splits the array. 
    * @return smallest value in a that is greater than or equal to
    *     the given key.
    */
   public static int floor(int[] a, int key) {
      int floor = Integer.MIN_VALUE;
      boolean exists = false;
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < a.length; i++) {
         
         if (key >= a[i] && floor < a[i]) {
            floor = a[i];
            exists = true;
         }
      }
      if (!exists) {
         throw new IllegalArgumentException();
      }
      return floor;
   }

}
