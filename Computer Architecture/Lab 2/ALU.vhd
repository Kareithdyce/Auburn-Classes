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

use work.dlx_types.all;
entity dlx_register is
	generic(prop_delay: Time := 10 ns);	
	port(in_val: in dlx_word; clock: in bit; out_val: out
		dlx_word);
end entity dlx_register;

architecture behavior of dlx_register is

begin
	dlx_reg_process: process(in_val, clock) is
	begin
		if(clock = '1') then 
			out_val <= in_val after prop_delay;
		end if;
	end process dlx_reg_process;
end architecture behavior;


use work.dlx_types.all;
entity mux is
	generic(prop_delay : Time := 5 ns);
	port (input_1,input_0 : in dlx_word; which: in bit; output: out dlx_word);
end entity mux;

architecture behavior of mux is

begin
	mux_process: process(input_0, input_1, which) is
	begin
		if(which = '1') then 
			output <= input_1 after prop_delay;
		else
			output <= input_0 after prop_delay;
		end if;
	end process mux_process;
end architecture behavior;



use work.dlx_types.all;
entity threeway_mux is
	generic(prop_delay : Time := 5 ns);
	port (input_2,input_1,input_0 : in dlx_word; which: in threeway_muxcode;
output: out dlx_word);
end entity threeway_mux;

architecture behavior of threeway_mux is
begin
	threeway_mux_process: process(input_0, input_1, input_2, which) is
	begin
		if(which = "01") then 
			output <= input_1 after prop_delay;
		elsif(which = "10") then
			output <= input_2 after prop_delay;
		else
			output <= input_0 after prop_delay;
		end if;
	end process threeway_mux_process;
end architecture behavior;

use work.dlx_types.all;
use work.bv_arithmetic.all;
entity pcplusone is
	generic(prop_delay: Time := 5 ns);
	port (input: in dlx_word; clock: in bit; output: out dlx_word);
end entity pcplusone;

architecture behavior of pcplusone is
begin
	pcplusone_process: process(input, clock) is
	variable sum: dlx_word;
	variable errorCode: boolean;	
	begin
		if clock'event and clock = '1' then
			bv_addu("00000000000000000000000000000001",input, sum, errorCode);
			output <= sum;
		end if;

	end process pcplusone_process;
end architecture behavior;

use work.bv_arithmetic.all;
use work.dlx_types.all;
entity reg_file is
	generic(prop_delay: Time := 15 ns);
	port(data_in : in dlx_word; readnotwrite, clock: in bit; data_out: out
		dlx_word; reg_number : in register_index);
end entity reg_file;

architecture behavior of reg_file is
	type reg_type is array (0 to 31) of dlx_word;
begin
	reg_file_process: process(readnotwrite, clock, reg_number, data_in) is
	variable registers : reg_type;
	begin
		if(clock ='1')then
			if(readnotwrite = '1') then
				data_out <= registers(bv_to_integer(reg_number)) after prop_delay;
			else
 				registers(bv_to_integer(reg_number)) := data_in;
			end if;
		end if;
	end process reg_file_process;
end architecture behavior;



