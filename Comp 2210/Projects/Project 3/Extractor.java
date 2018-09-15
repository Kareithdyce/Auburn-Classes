import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author:  Kareith Dyce (kzd0030@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2017-02-08
 *
 */
public class Extractor {

   /** raw data: all (x,y) points from source data. */
   private Point[] points;

   /** lines identified from raw data. */
   private SortedSet<Line> lines;

   /**
    * Builds an extractor based on the points in the file named by filename.
    */
   public Extractor(String filename) throws java.io.IOException {
      Scanner file = new Scanner(new File(filename));
      int n = Integer.parseInt(file.nextLine());
      int x;
      int y;
      points = new Point[n];
      for (int i = 0; i < n; i++) {
         x = Integer.parseInt(file.next());
         y = Integer.parseInt(file.nextLine().trim());
         Point p = new Point(x, y);
         points[i] = p;
      }
   }

   /**
    * Builds an extractor based on the points in the Collection named by pcoll.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }

   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-f  orce combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();

      for (int i = 0; i < points.length; i++) {
         for (int j = i + 1; j < points.length; j++) {
            for (int k = j + 1; k <  points.length; k++) {
               for (int l = k + 1; l <  points.length; l++) {
                  Line temp = new Line();
                  temp.add(points[i]);
                  temp.add(points[j]);
                  temp.add(points[k]);
                  temp.add(points[l]);
                  if (temp.length() >= 4) {
                     lines.add(temp);
                  }
               }
            }
         }
      }
      return lines;
   }

   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      for (Point p : points) {
         Point[] sort = Arrays.copyOf(points, points.length);
         Arrays.sort(sort, p.slopeOrder);
         int index = 1;
         for (int  i = 1; i < sort.length; i++) {
            if (index != 1 &&  i == sort.length - 1) {
               Line l = new Line(Arrays.asList(Arrays.copyOfRange(sort, i - index, i + 1)));
               l.add(p);
               if (l.length() >= 4) {
                  lines.add(l);
               }
               index = 1;
            }
            if (p.slopeTo(sort[i - 1]) == p.slopeTo(sort[i])) {
               index++;
            }
            else if (index != 1) {
               Line l = new Line(Arrays.asList(Arrays.copyOfRange(sort, i - index, i + 1)));
               l.add(p);
               if (l.length() >= 4) {
                  lines.add(l);
               }
               index = 1;
            }
         }
      }
      return lines;
   }
}
