import java.util.ArrayList;

public class TriangleList {

   public static void main(String[] args) {
   
      ArrayList<Triangle> triangles = new ArrayList<Triangle>();
   
      triangles.add(new Triangle(3, 7, 4));
      triangles.add(new Triangle(5, 5, 5));
      triangles.add(new Triangle(4, 4, 7));
      triangles.add(new Triangle(0, 1, 7));
   
      int index = 0;
      while (index < triangles.size()) {
         System.out.println("Triangle " + index + "\n"  
                              + triangles.get(index) + "\n");
         index++;
      }
   }

}
