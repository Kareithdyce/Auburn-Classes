import java.util.Scanner;

public class test
{
   public static void main (String [] args)
   {
  String answer;
   String equation;
     
      
       
      Scanner input = new Scanner(System.in);
      
      System.out.println("Do you have one or two variable(s)?");
      answer = input.nextLine(); 
      // single or double variable derivitives
      if (answer.contains("one")||answer.contains("1"))
      {
        System.out.println("Enter your equation(use x for your variable and * for mutliplication)");
        equation = input.nextLine();
        
      
       String subE = equation;
       int z = equation.length();
       
       // total ops
       int k = 0;
       
       // add ops      
       int add = 0;
       
       //sub ops
       int sub = 0;
       
       //muti ops
       int muti =0;
       
       //div ops
       int div = 0;
       
       
       for (int x = 0; x < z; x++)
       { 
       subE = equation.substring(x,x+1);
                          
        if(subE.contains("+"))
        {
         
         add++;        
        }
       
        if(subE.contains("-"))
        {
         
         sub++;
        }
        if(subE.contains("*"))
        {
        
         muti++;
        }
         if(subE.contains("/"))
        {
         
         div++;
        }

              
      
       }
       
        
        
        
            
       
       }

  
  
  
           }
       } 
