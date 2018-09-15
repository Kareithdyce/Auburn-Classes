/** 
 * Prints the course name, semester, and instructor of COMP 
 * 1210 followed by a description of the course. 
 *
 * @author Kareith Dyce
 * @version 8/22/16
 */
public class CourseInfo
{

   /**
    * Prints course information to std output.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) 
   {
     
      // print name, semester, & instructor
      System.out.println("Course Name: COMP 1210");
      System.out.println("Semester: Fall 2016");
      System.out.println("Instructor: Dr. Cross");
      System.out.println();
      // print description
      System.out.println("Description:\nCOMP 1210 uses the Java programming " 
                     + "language to cover the basics of software development.");
   }
}