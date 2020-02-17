public class Request{
   public int tml;
   public int requestID;
   public int operandCode;
   public int numberOperands;
   public int operand1;
   public int operand2;
   public int error;
   public int result;
   
   public Request(int tmlIn, int requestIDIn ,int operandCodeIn, int numberOperandsIn, int operand1In, int operand2In){
      tml = tmlIn;
      requestID = requestIDIn;
      operandCode = operandCodeIn;
      numberOperands = numberOperandsIn;
      operand1 = operand1In;
      operand2 = operand2In; 
   }
   
   public Request(int tmlIn, int requestIDIn ,int operandCodeIn, int numberOperandsIn, int operand1In){
      tml = tmlIn;
      requestID = requestIDIn;
      operandCode = operandCodeIn;
      numberOperands = numberOperandsIn;
      operand1 = operand1In; 
   }
   
   public Request(int tmlIn, int requestIDIn, int errorIn, int resultIn) {
      tml = tmlIn;
      requestID = requestIDIn;
      error = errorIn;
      result = resultIn;
   }
   public String toString() {
      final String EOLN = java.lang.System.getProperty("line.separator");
      String value = "";
      if(tml == 7){
         value = "TML = " + tml + " bytes" + EOLN +
                 "Request ID = " + requestID + EOLN +
                 "Error Code = " + error + EOLN +
                 "Result = " + result + EOLN;                 
               return value;
      }
      
      value = "TML = " + tml + " bytes" + EOLN +
                 "Request ID = " + requestID + EOLN +
                 "Operand Code = " + operandCode + EOLN +
                 "Number Operands = " + numberOperands + EOLN +
                 "Operand 1 = " + operand1 + EOLN;
      if(numberOperands == 2) {
         value += "Operand 2 = " + operand2 + EOLN;
      }
      
      return value;
   }

}