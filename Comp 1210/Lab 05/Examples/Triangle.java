
/** 
 * Represents a triangle with 3 sides and provides a classification
 * of the triangle (equilateral, scalene, not a triangle, or isosceles.
 */
public class Triangle 
{
   private double s1; 
   private double s2; 
   private double s3;  

/** 
  * Initializes a Triangle object given the length of 
  * its 3 sides. 
  *
  * @param side1 Length of side 1.
  * @param side2 Length of side 2.
  * @param side3 Length of side 3.
  */
   public Triangle(double side1, double side2, double side3) 
   {
      s1 = side1;
      s2 = side2;    
      s3 = side3;  
   }
   
   /** 
	 * Returns the classsification of the triangle based 
	 * on the length of its 3 sides. 
	 *
	 * @return classification of triangle
	 */
   public String getClassification() {  
      String result = "isosceles";  
      
      if (s1 <= 0 || s2 <= 0 ||  s3 <= 0) {
         result = "not a triangle";
      }
      else if ((s1 >= s2 + s3) || (s2 >= s1 + s3) || (s3 >= s1 + s2)) {
         result = "not a triangle";
      }
      else if ((s1 == s2) && (s2 == s3)) {  
         result = "equilateral";   
      } 
      else if ((s1 != s2) && (s1 != s3) && (s2 != s3)) { 
         result = "scalene";
      } 
       
      return result; 
   } 
   
   /** 
	 * Determines of if corresponding sides are equal.  
    *
	 * @param t the triangle to compare
    * 
	 * @return true if all corresponding sides are equal
	 */
   public boolean equals(Triangle t) {
      return (s1 == t.s1 && s2 == t.s2 && s3 == t.s3);
   }

   /** 
	 * Returns a String representation of the triangle 
	 * including the length of its 3 sides and its classification. 
	 *
	 * @return string representation of the triangle object
	 */
   public String toString() {  
      String output = "Side 1 = " + s1 + " units\n"
                    + "Side 2 = " + s2 + " units\n"
                    + "Side 3 = " + s3 + " units\n"
                    + "(" + getClassification() + ")";
       
      return output; 
   } 
}
