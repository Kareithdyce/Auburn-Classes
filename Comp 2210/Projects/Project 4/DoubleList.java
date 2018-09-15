import java.util.Iterator;
import java.util.NoSuchElementException;
 /**
  * @author   Kareith Dyce (kzd0030@auburn.edu)
  * @version  2017-03-08
  */

public class DoubleList<T> implements DoubleEndedList<T> {
   private Node front;
   private Node end;
   private int size;
  /**
   * Creates a new DoubleList object.
   */

   public DoubleList() {
      front = null;
      end = null;
      size = 0;
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
      return new DoubleIterator();
   }

  /**
   * Adds element to the front of the list. If element is null,
   * this method throws an IllegalArgumentException.
   */
   public void addFirst(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }

      Node n = new Node(element);
      n.next = front;

      if (front != null) {
         front.prev = n;
      }

      if (this.isEmpty()) {
         end = n;
      }

      front = n;
      size++;
   }

/**
 * Adds element to the end of the list. If element is null,
 * this method throws an IllegalArgumentException.
 */
   public void addLast(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }

      Node n = new Node(element);
      n.prev = end;
      if (end != null) {
         end.next = n;
      }

      if (this.isEmpty()) {
         front = n;
      }

      end = n;
      size++;
   }

/**
 * Delete and return the element at the front of the list.
 * If the list is empty, this method returns null.
 */
   public T removeFirst() {
      if (this.isEmpty()) {
         return null;
      }

      T temp = front.element;
      if (size == 1) {
         front = null;
         end = null;
         size--;
         return temp;
      }

      front = front.next;
      front.prev = null;
      size--;
      return temp;
   }

/**
 * Delete and return the element at the end of the list.
 * If the list is empty, this method returns null.
 */

   public T removeLast() {
      if (this.isEmpty()) {
         return null;
      }

      T temp = end.element;

      if (size == 1) {
         front = null;
         end = null;
         size--;
         return temp;
      }

      end = end.prev;
      end.next = null;
      size--;
      return temp;
   }



   private class DoubleIterator implements Iterator<T> {
      private Node current = front;

      /** Returns true if more elements are left in the iteration. */
      public boolean hasNext() {
         return current != null;
      }

      /** Returns the next element in the iteration. */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }

         T result = current.element;
         current = current.next;
         return result;
      }

      /** Unsupported operation. */
      public void remove() {
         throw new UnsupportedOperationException();
      }

   }
/** Defines a node for a doubly-linked list. */

   private class Node {
      private T element;
      private Node next;
      private Node prev;

    /** Creates a new node. */
      public Node(T e) {
         element = e;
      }
   }
}
