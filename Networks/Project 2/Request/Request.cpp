#include <string>
#include <iostream>

using namespace std;
class Request{
   int tml;
   int requestID;
   int operandCode;
   int numberOperands;
   int operand1;
   int operand2;
   int error;
   int result;

   Request(int tmlIn, int requestIDIn ,int operandCodeIn, int numberOperandsIn, int operand1In, int operand2In){
      tml = tmlIn;
      requestID = requestIDIn;
      operandCode = operandCodeIn;
      numberOperands = numberOperandsIn;
      operand1 = operand1In;
      operand2 = operand2In;
   }

   Request(int tmlIn, int requestIDIn ,int operandCodeIn, int numberOperandsIn, int operand1In){
      tml = tmlIn;
      requestID = requestIDIn;
      operandCode = operandCodeIn;
      numberOperands = numberOperandsIn;
      operand1 = operand1In;
   }

   Request(int tmlIn, int requestIDIn, int errorIn, int resultIn) {
      tml = tmlIn;
      requestID = requestIDIn;
      error = errorIn;
      result = resultIn;
   }
   string toString() {
      string EOLN = "\n";
      string value = "";
      if(tml == 7){
         value = "TML = " + std::to_string(tml) + " bytes" + EOLN +
                 "Request ID = " + std::to_string(requestID) + EOLN +
                 "Error Code = " + std::to_string(error) + EOLN +
                 "Result = " + std::to_string(result) + EOLN;
               return value;
      }

      value = "TML = " + std::to_string(tml) + " bytes" + EOLN +
                "Request ID = " + std::to_string(requestID) + EOLN +
                 "Operand Code = " + std::to_string(operandCode) + EOLN +
                 "Number Operands = " + std::to_string(numberOperands) + EOLN +
                 "Operand 1 = " + std::to_string(operand1) + EOLN;
      if(numberOperands == 2) {
         value += "Operand 2 = " + std::to_string(operand2) + EOLN;
      }

      return value;
   }

};
