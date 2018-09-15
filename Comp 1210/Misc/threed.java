public class threed
{
   public static void main(String [] args)
   {
      int [][][] hell = new int [3][3][3];
      for (int i = 0; i < 3; i++)
      {
         for (int j = 0; j < 3; j++)
         {
            for (int k = 0; k < 3; k++)
            {
            hell[i][j][k] = 666;
            }
         }
      }
   }
}