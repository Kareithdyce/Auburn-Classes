   library work;
use work.dlx_types.all;
use work.bv_arithmetic.all;

entity ALU is
   port(operand1, operand2: in dlx_word; operation: in
   alu_operation_code;
   result: out dlx_word; error: out error_code);
end entity ALU; 

architecture behaviour of ALU is
begin 
   process1 : process(operand1, operation) is 
      variable overflow : boolean := false;
      variable sum: dlx_word := "00000000000000000000000000000000";
      variable zero: boolean := false;	
   
   begin
      case operation is	  
         when "0000" => 
            bv_addu(operand1, operand2, sum, overflow);
            result <= sum;    
            if overflow then
               error <= "0001";
            else
               error <= "0000";
            end if;
         when "0001" =>
            bv_subu(operand1, operand2, sum, overflow);
            result <= sum;    
            if overflow then
               error <= "0010";
            else
               error <= "0000";
            end if;
         when "0010"=>
            bv_add(operand1, operand2, sum, overflow);
            if overflow then
               error <= "0010";
            else
               error <= "0000";
            end if;
         when "0011" =>
            bv_sub(operand1, operand2, sum, overflow);
            result <= sum;    
            if overflow then
               error <= "0010";
            else
               error <= "0000";
            end if;
         when "0100" =>
            bv_mult(operand1, operand2, sum, overflow);
            result <= sum;    
            if overflow then
               error <= "0010";
            else
               error <= "0000";
            end if;
         when "0101" =>
            bv_div(operand1, operand2, sum, zero, overflow);
            result <= sum;
            if zero then
               error <= "0011";
            else 
               if overflow then
                  error <= "0001";
               end if;
               error <= "0000";
            end if;
         when "0110" =>
            if operand1 = sum then
               if operand2 = sum then
                  result <= sum;
                  error <= "0000";
               end if;
            else 
               result <= "00000000000000000000000000000001";
               error <= "0000";
            end if;
         when "0111" =>
            result <= operand1 and operand2;
            error <= "0000";
         when "1000" =>
            if operand1 /= sum then
               if operand2 /= sum then
                  result <= "00000000000000000000000000000000";
                  error <= "0000";
               end if;
            else 
               result <= "00000000000000000000000000000001";
               error <= "0000";
            end if;
         when "1001" =>
            result <= operand1 or operand2;
            error <= "0000";
         when "1010" =>
            error <= "0000";
            if operand1 = sum then
               result <= "00000000000000000000000000000001";
            else 
               result <= sum;
            end if;
         when "1011" =>
            result <= not operand1;
            error <= "0000";
         when others =>
            result <= "00000000000000000000000000000000";
            error <= "0000";
      end case;
   
   end process process1; 
end architecture behaviour; 


