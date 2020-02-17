-- datapath_aubie.vhd

-- entity reg_file (lab 2)
use work.dlx_types.all;
use work.bv_arithmetic.all;

entity reg_file is
    generic(prop_delay : Time := 5 ns);
    port (
        data_in     :   in dlx_word;
        readnotwrite:   in bit;
        clock       :   in bit;
	data_out    :   out dlx_word;
        reg_number  :   in register_index
    );
end entity reg_file;

architecture behavior of reg_file is
		type reg_type is array (0 to 31) of dlx_word;
begin
	reg_file_process: process(readnotwrite, clock, reg_number, data_in) is
		variable registers: reg_type;
	begin
		-- Start process
		if (clock = '1') then
			if (readnotwrite = '1') then
				data_out <= registers(bv_to_integer(reg_number)) after prop_delay;
			else
				registers(bv_to_integer(reg_number)) := data_in;
				
			end if;
		end if;
	end process reg_file_process;
end architecture behavior;


-- entity alu (lab 3)
use work.dlx_types.all;
use work.bv_arithmetic.all;

entity alu is
    generic(prop_delay : Time := 5 ns);
    port(
        operand1  :   in dlx_word;
        operand2  :   in dlx_word;
        operation :   in alu_operation_code;
        result    :   out dlx_word;
        error     :   out error_code
    );
end entity alu;

-- alu_operation_code values
-- 0000 unsigned add
-- 0001 signed add
-- 0010 2's compl add
-- 0011 2's compl sub
-- 0100 2's compl mul
-- 0101 2's compl divide
-- 0110 logical and
-- 0111 bitwise and
-- 1000 logical or
-- 1001 bitwise or
-- 1010 logical not (op1)
-- 1011 bitwise not (op1)
-- 1101-1111 output all zeros

-- error code values
-- 0000 = no error
-- 0001 = overflow (too big positive)
-- 0010 = underflow (too small neagative)
-- 0011 = divide by zero
architecture behavior of ALU is
	    -- Define any types here that will be used
	

	begin
	    alu_process: process(operand1, operand2, operation) is
	          variable sum: dlx_word := x"00000000";
	          variable logical_true: dlx_word := x"00000001";
	          variable logical_false: dlx_word := x"00000000";
	          variable overflow: boolean;
	          variable div_by_zero: boolean;
	          variable op1_logical_status: bit; 
	          variable op2_logical_status: bit; 
	

	          begin
	              error <= "0000"; 
	              case(operation) is
	                  when "0000" => 
	                      bv_addu(operand1, operand2, sum, overflow);
	                      if overflow then
	                          error <= "0001";
	                      end if;
	                      result <= sum;
	                  when "0001" => 
	                      bv_subu(operand1, operand2, sum, overflow);
	                      if overflow then
	                          error <= "0010";
	                          
	                      end if;
	                      result <= sum;
	                  when "0010" => 
	                      bv_add(operand1, operand2, sum, overflow);
	                      if overflow then
	                          
	                          if (operand1(31) = '0') AND (operand2(31) = '0') then
	                              if (sum(31) = '1') then
	                                  error <= "0001"; 
	                              end if;
	                         
	                          elsif (operand1(31) = '1') AND (operand2(31) = '1') then
	                              if (sum(31) = '0') then
	                                  error <= "0010"; 
	                              end if;
	                          end if;
	                      end if;
	                      result <= sum;
	                  when "0011" => 
	                      bv_sub(operand1, operand2, sum, overflow);
	                      if overflow then
	                         
	                          if (operand1(31) = '1') AND (operand2(31) = '0') then
	                              if (sum(31) = '0') then
	                                  error <= "0010"; 
	                              end if;
	                          
	                          elsif (operand1(31) = '0') AND (operand2(31) = '1') then
	                              if (sum(31) = '1') then
	                                  error <= "0001"; 
	                              end if;
	                          end if;
	                      end if;
	                      result <= sum;
	                  when "0100" => 
	                      bv_mult(operand1, operand2, sum, overflow);
	                      if overflow then
	                          if (operand1(31) = '1') AND (operand2(31) = '0') then 
	                              error <= "0010"; 
	                          elsif (operand1(31) = '0') AND (operand2(31) = '1') then 
	                              error <= "0010"; 
	                          else 
	                              error <= "0001";
	                          end if;
	                      end if;
	                      result <= sum;
	                  when "0101" => 
	                      bv_div(operand1, operand2, sum, div_by_zero, overflow);
	                      if div_by_zero then
	                          error <= "0011"; --
	                      elsif overflow then
	                          error <= "0010"; 
	                      end if;
	                      result <= sum;
	                  when "0110" => 
	                      op1_logical_status := '0'; 
	                      op2_logical_status := '0';
	                      for i in 31 downto 0 loop
	                          if (operand1(i) = '1') then
	                              op1_logical_status := '1';
	                              exit;
	                          end if;
	                      end loop;
	                     for i in 31 downto 0 loop
	                           if (operand2(i) = '1') then
	                              op2_logical_status := '1';
	                              exit;
	                          end if;
	                      end loop;
	                      if ((op1_logical_status AND op2_logical_status) = '1') then
	                          result <= logical_true; 
	                      else
	                          result <= logical_false; 
	                      end if;
	                  when "0111" => 
	                      for i in 31 downto 0 loop
	                          sum(i) := operand1(i) AND operand2(i);
	                      end loop;
	                      result <= sum;
	                  when "1000" => 
	                 op1_logical_status := '0'; 
	                      op2_logical_status := '0'; 
	                      for i in 31 downto 0 loop
	                          if (operand1(i) = '1') then
	                              op1_logical_status := '1';
	                              exit;
	                          end if;
	                      end loop;
	                      for i in 31 downto 0 loop
	                         if (operand2(i) = '1') then
	                              op2_logical_status := '1';
	                              exit;
	                          end if;
	                      end loop;
	                      if ((op1_logical_status OR op2_logical_status) = '1') then
	                          result <= logical_true; 
	                      else
	                          result <= logical_false; 
	                      end if;
	                  when "1001" => 
	                      for i in 31 downto 0 loop
	                          sum(i) := operand1(i) OR operand2(i);
	                      end loop;
	                      result <= sum;
	                  when "1010" => 
	                      sum := logical_true; 
	                      for i in 31 downto 0 loop
	                          if (NOT operand1(i) = '0') then 
	                              sum := logical_false; 
	                              exit;
	                          end if;
	                      end loop;
	                      result <= sum;
	                  when "1011" => 
	                      for i in 31 downto 0 loop
	                          sum(i) := NOT operand1(i);
	                      end loop;
	                      result <= sum;
	                  when "1100" => 
	                      sum := logical_false;
	                      if (operand1 = x"00000000") then
	                          sum := logical_true;
	                      end if;
	                      result <= sum;
	                  when others => 
	                      result <= x"00000000";
	              end case;
	   end process alu_process;
	end architecture behavior;

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

-- entity dlx_register (lab 3)
use work.dlx_types.all;

entity dlx_register is
    generic(prop_delay : Time := 5 ns);
    port(
        in_val  :   in dlx_word;
        clock   :   in bit;
        out_val :   out dlx_word
    );
end entity dlx_register;

architecture behavior of dlx_register is

begin
	dlx_reg_process: process(in_val, clock) is
	begin
		-- Start process
		if (clock = '1') then
			out_val <= in_val after prop_delay;
		end if;
	end process dlx_reg_process;
end architecture behavior;

-- entity pcplusone
use work.dlx_types.all;
use work.bv_arithmetic.all;

entity pcplusone is
	generic(prop_delay: Time := 5 ns);
	port (
        input : in dlx_word;
        clock : in bit;
        output: out dlx_word
    );
end entity pcplusone;

architecture behavior of pcplusone is
begin
    plusone: process(input, clock) is  
        variable newpc: dlx_word;
        variable error: boolean;
    begin
        if clock'event and clock = '1' then
            bv_addu(input,"00000000000000000000000000000001",newpc,error);
            output <= newpc after prop_delay;
        end if;
    end process plusone;
end architecture behavior;


-- entity mux
use work.dlx_types.all;

entity mux is
     generic(prop_delay : Time := 5 ns);
     port (
            input_1 : in dlx_word;
            input_0 : in dlx_word;
            which   : in bit;
            output  : out dlx_word
     );
end entity mux;

architecture behavior of mux is
begin
   muxProcess : process(input_1, input_0, which) is
   begin
      if (which = '1') then
         output <= input_1 after prop_delay;
      else
         output <= input_0 after prop_delay;
      end if;
   end process muxProcess;
end architecture behavior;
-- end entity mux

-- entity threeway_mux
use work.dlx_types.all;

entity threeway_mux is
    generic(prop_delay : Time := 5 ns);
    port (
        input_2 : in dlx_word;
        input_1 : in dlx_word;
        input_0 : in dlx_word;
        which   : in threeway_muxcode;
        output  : out dlx_word
    );
end entity threeway_mux;

architecture behavior of threeway_mux is
begin
   muxProcess : process(input_1, input_0, which) is
   begin
      if (which = "10" or which = "11" ) then
         output <= input_2 after prop_delay;
      elsif (which = "01") then
         output <= input_1 after prop_delay;
      else
         output <= input_0 after prop_delay;
      end if;
   end process muxProcess;
end architecture behavior;
-- end entity mux


-- entity memory
use work.dlx_types.all;
use work.bv_arithmetic.all;

entity memory is
    port (
        address       :   in dlx_word;
        readnotwrite  :   in bit;
        data_out      :   out dlx_word;
        data_in       :   in dlx_word;
        clock         :   in bit
    );
end memory;

architecture behavior of memory is

begin  -- behavior

    mem_behav: process(address,clock) is
    -- note that there is storage only for the first 1k of the memory, to speed
    -- up the simulation
        type memtype is array (0 to 1024) of dlx_word;
        variable data_memory : memtype;
    begin
    -- fill this in by hand to put some values in there
    -- some instructions
        
        data_memory(0) :=  X"30200000"; --LD R4, 0x100    
	data_memory(1) :=  X"00000100"; -- address 0x100 for previous instruction    
	data_memory(2) :=  "00000000000110000100010000000000"; -- ADDU R3,R1,R2    
	-- some data    
	-- note that this code runs every time an input signal to memory changes,     
	-- so for testing, write to some other locations besides these    
	data_memory(256) := "01010101000000001111111100000000";    
	data_memory(257) := "10101010000000001111111100000000";    
	data_memory(258) := "00000000000000000000000000000001";
	data_memory(259) := "11111100000000000000000000000001";
	data_memory(260) := "11111111111111111111111111111111";

	
        if clock = '1' then
          if readnotwrite = '1' then
            -- do a read
            data_out <= data_memory(bv_to_natural(address)) after 5 ns;
          else
            -- do a write
            data_memory(bv_to_natural(address)) := data_in;
          end if;
        end if;
  end process mem_behav;
end behavior;
-- end entity memory