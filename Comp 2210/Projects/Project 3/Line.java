import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Line.java
 * Models a line segment as a sorted set of points.
 *
 * @author:  Kareith Dyce (kzd0030@auburn.edu)
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2017-02-08
 *
 */
public class Line implements Comparable<Line>, Iterable<Point> {

   SortedSet<Point> line;

   /**
    * Creates a new line containing no points.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Line() {
      line = new TreeSet<Point>();
   }

   /**
    * Creates a new line with containing all distinct collinear points in the
    * Collection c.
    */
   public Line(Collection<Point> c) {
      line = new TreeSet<Point>();
      if (!c.isEmpty()) {
         for (Point p : c) {
            if (!line.contains(p)) {
               this.add(p);
            }
         }
      }
   }

   /**
    * Adds the point p to this line if p is collinear with all points already
    * in the line and p itself is not already in the line. Returns true if this
    * line is changed as a result, false otherwise.
    */
   public boolean add(Point p) {
      if (line.isEmpty()) {
         line.add(p);
         return true;
      }
   
      if (!line.contains(p)) {
         if (line.size() == 1) {
            line.add(p);
            return true;
         }
         if (line.first().slopeTo(p) == line.first().slopeTo(line.last())) {
            line.add(p);
            return true;
         }
      }
      return false;
   }

   /**
    * Returns the first (minimum) point in this line or null if this line
    * contains no points.
    */
   public Point first() {
      if (line.isEmpty()) {
         return null;
      }
      return line.first();
   }


   /**
    * Returns the last (maximum) point in this line or null if this line
    * contains no points.
    */
   public Point last() {
      if (line.isEmpty()) {
         return null;
      }
      return line.last();
   }

   /**
    * Returns the number of points in this line.
    */
   public int length() {
      return line.size();
   }

   /**
    * Compares this line with the specified line for order. Returns a negative
    * integer, zero, or a positive integer if this line is less than, equal to,
    * or greater than the specified line. Lines are ordered first by their
    * first point then by their last point. All three properties of compareTo
    * as specified in the Comparable interface are met, and this implementation
    * is consistent with equals.
    */
   @Override
   public int compareTo(Line that) {
   
      if (that.length() == 0 && line.isEmpty()) {
         return 0;
      }
      if (that.length() == 0) {
         return Integer.MAX_VALUE;
      }
   
      if (line.isEmpty()) {
         return Integer.MIN_VALUE;
      }
    
   
      int comp = line.first().compareTo(that.first());
      if (comp != 0) {
         return comp;
      }
      comp = line.last().compareTo(that.last());
      return comp;
   }

   /**
    * Provide an iterator over all the points in this line. The order in which
    * points are returned must be ascending natural order.
    */
   @Override
   public Iterator<Point> iterator() {
   
      return line.iterator();
   }

   /**
    * Return true if this point's x and y coordinates are the same as that
    * point's x and y coordinates, and return false otherwise.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj == this) {
         return true;
      }
      if (!(obj instanceof Line)) {
         return false;
      }
      Line that = (Line) obj;
      if ((this.length() == 0) && (that.length() == 0)) {
         return true;
      }
      if ((this.length() == 0) && (that.length() != 0)) {
         return false;
      }
      if ((this.length() != 0) && (that.length() == 0)) {
         return false;
      }
      return (this.first().equals(that.first()))
         && (this.last().equals(that.last()));
   }

   /**
    * Return a string representation of this line.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   @Override
   public String toString() {
      if (length() == 0) {
         return "";
      }
      StringBuilder s = new StringBuilder();
      for (Point p : line) {
         s.append(p + " -> ");
      }
      s = s.delete(s.length() - 4, s.length());
      return s.toString();
   }

}
