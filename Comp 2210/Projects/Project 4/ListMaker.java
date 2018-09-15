import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author   Kareith Dyce (kzd0030@auburn.edu)
 * @version  2017-03-08
 */

public class ListMaker<T> implements RandomizedList<T> {
   private int size = 0;
   private static final int DEFAULT_CAPACITY = 1;
   private T[] elements;

    /**
     * Creates a new ListMaker with default capacity.
     */
   public ListMaker() {
      this(DEFAULT_CAPACITY);
   }

    /**
     * Creates a new ListMaker specified capacity.
     */
   @SuppressWarnings("unchecked")
    public ListMaker(int capacity) {
      size = 0;
      elements = (T[]) new Object[capacity];
   }


    /**
     * Returns the number of elements in this list.
     */
   public int size() {
      return size;
   }

    /**
     * Returns true if this list contains no elements, false otherwise.
     */
   public boolean isEmpty() {
      return size == 0;
   }

    /**
     * Creates and returns an iterator over the elements of this list.
     */
   public Iterator<T> iterator() {
      return new RandomIterator<T>(elements, size);
   }

    /**
     * Adds the specified element to this list. If the element is null, this
     * method throws an IllegalArgumentException.
     */
   public void add(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }

      if (size == elements.length) {
         resize(size * 2);
      }

      elements[size] = element;
      size++;
   }

    /**
     * Selects and removes an element selected uniformly at random from the
     * elements currently in the list. If the list is empty this method returns
     * null.
     */
   public T remove() {
      if (size == 0) {
         return null;
      }
      if (size == 1) {
        size--;
        T temp = elements[0];
        elements[0] = null;
        return temp;
      }
      size--;
      Random ran = new Random();
      int num = ran.nextInt(size);
      T e =  elements[num];
      elements[num] = elements[size];
      elements[size] = null;


      if (size > 0 && size < elements.length / 4) {
         resize(elements.length / 2);
      }

      return e;
   }
    /**
     * Selects but does not remove an element selected uniformly at random from
     * the elements currently in the list. If the list is empty this method
     * return null.
     */

   public T sample() {
      if (size == 0) {
         return null;
      }

      Random ran = new Random();
      int num = ran.nextInt(size);
      return elements[num];
   }



   private class RandomIterator<T> implements Iterator<T> {

      // the array of elements to iterate over
      private T[] elements;
      // the number of elements in the array, beginning at index zero
      private int count;
      // the index of the next element in the iteration sequence
      private int current;

      /**
       * Construct a properly initialized iterator.
       */
      public RandomIterator(T[] elem, int size) {
         elements = elem;
         count = size;
         current = 0;
      }

      /**
       * Returns true if there is at least one more element remaining
       * in the iteration sequence.
       */
      public boolean hasNext() {
         return current < count;
      }

      /**
       * Returns a random element in the iteration sequence.
       */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         Random ran = new Random();
         current++;
         if(current == count){
           return elements[0];
         }
         int num = ran.nextInt(count - current);
         T temp = elements[num];
         elements[num] = elements[count - current];
         elements[count - current] = temp;
         return temp;
      }

      /**
       * Unsupported operation.
       */
      public void remove() {
         throw new UnsupportedOperationException();

      }
   }
    /**
   * Reassign elements to a new array of capacity newSize with the
   * current elements of this bag.
   */

   private void resize(int newSize) {
      assert newSize > 0;
      @SuppressWarnings("unchecked")
             T[] newArray = (T[]) new Object[newSize];
      System.arraycopy(elements, 0, newArray, 0, size);
      elements = newArray;
   }
 


}
