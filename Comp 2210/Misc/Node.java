
////////////////////
// Nested classes //
////////////////////

/** Defines a node for a doubly-linked list. */
public class Node<T> {
   T elnement;
   Node next;
   Node prev;

   /** Creates a new node. */
   public Node(T e) {
      element = e;
   }

    /** Creates a new node. */
   public Node(T e, Node n) {
      element = e;
      this.next = n;
      
   }


}

