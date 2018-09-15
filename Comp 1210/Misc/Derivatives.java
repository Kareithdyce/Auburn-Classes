import java.util.Scanner;

public class Derivatives
{


   public static void main(String [] args)
   {
      String derv;
      String answer;
      String equation;
       // add ops      
      int add = 0;
      int[] plusLoc;
       
       //sub ops
      int sub = 0;
       
       //muti ops
      int muti = 0;
       
       //div ops
      int div = 0;
   
      int ops = 0;
      
       
      Scanner input = new Scanner(System.in);
      
      System.out.println("Do you have one or two variable(s)?");
      answer = input.nextLine(); 
      // single or double variable derivitives
      if (answer.toLowerCase().contains("one") || answer.contains("1"))
      {
         System.out.print("Enter your equation(use x for your variable "
         + "and enter ALL powers using ^)");
         equation = input.nextLine();
         equation = equation.toLowerCase();
         add = addOps(equation);
         plusLoc = addLoc(add, sub, equation);
        
       /* for (int i=0; i<plusLoc.length; i++) 
        {
         System.out.print(plusLoc[i]+" ");
        }
        */
         sub = subOps(equation);
         muti = mutiOps(equation);
         div = divOps(equation);
         ops = ops(add, sub, muti, div);
         derv = singlDervRule(equation, plusLoc);
         System.out.println(derv);        
      }
      
           
      if (answer.contains("two"))
      // implicit differntation is not implemeted yet
      {
         System.out.println("Error");
         return;
      }        
   }
   
   
   //methods
   public static int constant(String equation)
   {
      System.out.println("The derivative of " + equation + " is 0");   
      return 0;
   }
   

   public static String normRule(String equation)
   {
      // finds the power
      int indexPow = equation.indexOf("^");
      String powers = equation.substring(indexPow + 1); 
      int pow = Integer.parseInt(powers);
        
      //finds coeffiecnt
      int indexVar = equation.indexOf("x");
      String coE = equation.substring(0, indexVar); 
      int num = Integer.parseInt(coE);
   
      if (pow == 1)
      {
         return Integer.toString(num);
      }       
      return pow * num + "x^" + (pow - 1);    
   }
   
   //quotient rule
   public static String qRule(String equation)
   {
      //System.out.println("The derivative of " + equation +" is 0");   
      return "";
   }
   
   //addition and subraction rule
   public static String sumRule(String equation, int[] addLoc)
   {
      String subE;
      String derv = "";
      //Just a subString
      for (int x = 1; x < addLoc.length; x++)
      {
         if (x == 1)
         {
            subE = equation.substring(0, addLoc[x]);
            derv = normRule(subE) + "+";
          
         } 
         else  
         {
            subE = equation.substring(addLoc[x - 1] + 1, addLoc[x]);
            if (x < addLoc.length - 1)
            {
               derv += normRule(subE) + "+";
            }
            else
            {
               derv += normRule(subE);
            }
         }
      
      }
        
      return derv;
   }
   public static String proRule(String equation)
   {
      System.out.println("The derivative of " + equation + " is 0");   
      return "";
   }

   public static String singlDervRule(String equation, int[] addLoc)
   {
      String derv = "";
   
   
   //reads varible
      if (equation.contains("x") || equation.contains("X"))
      {
            
                       
         if (equation.contains("*"))
         {
            proRule(equation);
             //product rule
         }
           
         else if (equation.contains("/"))
         {
            qRule(equation);
               //quotient rule
         }
             
         else if (equation.contains("+") || equation.contains("-"))
         {
            /* 
             for (int x = 0; x < addLoc[0]; x++)
             {
             */
            derv = sumRule(equation, addLoc); 
             //}//sum rule
            
         }
         
             
             
         else
         {
            derv = normRule(equation);
               //simple derivative
            System.out.println(derv);
         }
         
      }
      if (!equation.contains("x") && !equation.contains("X"))
         //makes sure only  letter x is used
      {        
         if (equation.matches(".*[0-9].*")) 
            //makes sure only numbers are used
         {
                //derivates of a constant is 0
            constant(equation);
         }
         else
         {
            System.out.println("ERROR!");
            
         }
      }             
      return derv;
   } 
       
       
       
       
        //counts the additon ops 
   public static int addOps(String equation)
   {
      String subE = equation;
       
      int z = equation.length();
      int add = 0;
      for (int x = 0; x < z; x++)
      {  
         subE = equation.substring(x, x + 1);
                           
         if (subE.contains("+"))
         {
            add++;        
         }
          
      }  
      return add;
   }
         
      
      //finds the location off addition and subtraction
   public static int[] addLoc(int add, int sub, String equation)
   {
      String subE = equation;
      int[] addLoc;
      int z = equation.length();
      int addLen = 0;
   
        
      addLoc = new int[add + 1];
       
         // if no additon leaves loop
      if (add == 0)
      {
         addLoc[0] = 0;
         return addLoc;
      }
       
      addLoc[0] = add; 
      int a = 1;
      int y = 0;
      subE = equation.substring(y);
   
      for (int x = 0; x < z; x++)
      { 
           
                                                 
         if (subE.contains("+"))
         {
             
            subE = subE.substring(y);
            y = subE.indexOf("+");
            addLen += y;
            y++;
            if (a > 1)
            {
               addLen++; 
            } 
            addLoc[a] = addLen;
            if (a < add)             
            {
               a++;
            }
                    
         }
        
      }
          
      return addLoc;
   }

         
         
     
            
            
       //counts the  subtraction ops 
   public static int subOps(String equation)
   {
      String subE = equation;
      int z = equation.length();
        
        
      int sub = 0;
       
      for (int x = 0; x < z; x++)
      { 
         subE = equation.substring(x, x + 1);
                          
         if (subE.contains("-"))
         {
            sub++;        
         }
      }
      return sub;
   }
            
      
        //counts the muti ops 
   public static int mutiOps(String equation)
   {
      String subE = equation;
      int z = equation.length();
        
        
      int muti = 0;
       
      for (int x = 0; x < z; x++)
      { 
         subE = equation.substring(x, x + 1);
                          
         if (subE.contains("*"))
         {
            muti++;        
         }
      }
      return muti;
   }
            
            
       //counts the divison ops 
   public static int divOps(String equation)
   {
      String subE = equation;
      int z = equation.length();
        
        
   
      int div = 0;
       
      for (int x = 0; x < z; x++)
      { 
         subE = equation.substring(x, x + 1);
                          
         if (subE.contains("/"))
         {
            div++;        
         }
      }
      return div;
   }
            

            
            
            
       
       
   public  static int ops(int add, int sub, int muti, int div)
   {
      
      int k = add + sub + muti + div;
      return k;
   
      
              
      
   }
       
        
        
        
        
       
                   
       
       
       
}
