
   class Test 
   {
      public static final int MAX = 100;
      public static void main(String[] args) 
      { 
         int total = 10;
         int errorCount = 0;
      
         if (total > MAX)
            System.out.println ("Error!!");
         errorCount++;
      		
         System.out.println ("MAX = " +  MAX);
         System.out.println ("total = " + total);
         System.out.println ("errorCount = " + errorCount);
      		
      }
   }