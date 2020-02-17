-- dlx_datapath.vhd

package dlx_types is
  subtype dlx_word is bit_vector(31 downto 0); 
  subtype half_word is bit_vector(15 downto 0); 
  subtype byte is bit_vector(7 downto 0); 

  subtype alu_operation_code is bit_vector(3 downto 0); 
  subtype error_code is bit_vector(3 downto 0); 
  subtype register_index is bit_vector(4 downto 0);

  subtype opcode_type is bit_vector(5 downto 0);
  subtype offset26 is bit_vector(25 downto 0);
  subtype func_code is bit_vector(5 downto 0);
end package dlx_types; 

--------------------------------------------------------
-- alu 
--------------------------------------------------------
use work.dlx_types.all; 
use work.bv_arithmetic.all;  

entity alu is 
     port(operand1, operand2: in dlx_word; 
	  operation: in alu_operation_code; signed: in bit; 
	  result: out dlx_word; error: out error_code); 
end entity alu; 

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;

library work;
use work.dlx_types.all;
use work.bv_arithmetic.all;

architecture behaviour of alu is
begin
	process(operand1, operand2, operation, signed) is
	  variable temp: dlx_word;
	  variable overflow: boolean; 
	begin

	   case(operation) is
		when "0000" => -- addition
			if signed = '0' then
				bv_addu(operand1, operand2, temp, overflow);
			elsif signed = '1' then 
				bv_add(operand1, operand2, temp, overflow);
			end if;

		when "0001" => -- subtraction
			if signed = '0' then
				bv_subu(operand1, operand2, temp, overflow);
			elsif signed = '1' then
				bv_sub(operand1, operand2, temp, overflow);
			end if;

		when "0010" => -- and
			temp := operand1 and operand2;
			overflow := false;

		when "0011" => -- or
			temp := operand1 or operand2;
			overflow := false;

		when "1011" => -- set less than
			bv_subu(operand1,operand2, temp, overflow);
			if overflow then
				temp := x"00000001";
			else 
				temp := x"00000000";
			end if;
			overflow := false;

		when "1110" => -- multiplication
			if signed = '0' then
				bv_multu(operand1, operand2, temp, overflow);
			elsif signed = '1' then
				bv_mult(operand1, operand2, temp, overflow);
			end if;

		when others => -- default
			temp := x"00000000";
			overflow := false;
	   end case;

	   result <= temp after 5 ns;

	   if overflow = true then 
		error <= "0001" after 5 ns;
	   else
		error <= "0000" after 5 ns;
	   end if;
	end process;
end architecture behaviour;

--------------------------------------------------------
-- mips zero 
--------------------------------------------------------
use work.dlx_types.all; 

entity mips_zero is
  port (
    input  : in  dlx_word;
    output : out bit);
end mips_zero;

architecture behaviour of mips_zero is
begin
	process(input) is
	begin
		if (input = "00000000000000000000000000000000") then
			output <= '1' after 5 ns;
		else
			output <= '0' after 5 ns;
		end if;
	end process;
end architecture behaviour;

--------------------------------------------------------
-- mips register 
--------------------------------------------------------
use work.dlx_types.all; 

entity mips_register is
     port(in_val: in dlx_word; clock: in bit; 
	  out_val: out dlx_word);
end entity mips_register;

library work;
use work.dlx_types.all;
use work.bv_arithmetic.all;

architecture behaviour of mips_register is
begin
	process(in_val, clock) is
	begin
		if clock = '1' then
			out_val <= in_val after 5 ns;
		end if;
	end process;
end architecture behaviour;

--------------------------------------------------------
-- mips bit register 
--------------------------------------------------------
use work.dlx_types.all; 

entity mips_bit_register is
     port(in_val: in bit; clock: in bit; 
	  out_val: out bit);
end entity mips_bit_register;

library work;
use work.dlx_types.all;
use work.bv_arithmetic.all;

architecture behaviour of mips_bit_register is
begin
	process(in_val, clock) is
	begin
		if (clock = '1') then
			out_val <= in_val after 5 ns;
		end if;
	end process;
end architecture behaviour;

--------------------------------------------------------
-- mux - done
--------------------------------------------------------
use work.dlx_types.all; 

entity mux is
     port (input_1,input_0 : in dlx_word; which: in bit; 
	   output: out dlx_word);
end entity mux;

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;

architecture behaviour of mux is
begin
	process(input_0, input_1, which) is
	begin
		if which = '0' then
			output <= input_0 after 5 ns;
		elsif which = '1' then
			output <= input_1 after 5 ns;
		end if;
	end process;

end architecture behaviour;

--------------------------------------------------------
-- index mux 
--------------------------------------------------------
use work.dlx_types.all;

entity index_mux is
     port (input_1,input_0 : in register_index; which: in bit; 
	   output: out register_index);
end entity index_mux;

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;

library work;
use work.dlx_types.all;
use work.bv_arithmetic.all;

architecture behaviour of index_mux is
begin

	process(input_1, input_0, which) is
	begin
		if which = '0' then
			output <= input_0 after 5 ns;
		elsif which = '1' then
			output <= input_1 after 5 ns;
		end if;
	end process;
end architecture behaviour;

--------------------------------------------------------
-- sign extend 
--------------------------------------------------------
use work.dlx_types.all;

entity sign_extend is
     port (input: in half_word; signed: in bit; 
	   output: out dlx_word);
end entity sign_extend;

architecture behaviour of sign_extend is
  signal ex: half_word;
begin
	sign_ex_process: process(input, signed) is
	begin
		if signed = '0' then 
			ex <= (others => '0');
		elsif signed ='1' then 
			ex <= (others => '1');
		end if; 
		output <= ex & input after 5 ns;
	end process;

end architecture behaviour;

--------------------------------------------------------
-- add4 
--------------------------------------------------------
use work.dlx_types.all; 
use work.bv_arithmetic.all; 

entity add4 is
    port (input: in dlx_word; 
	  output: out dlx_word);
end entity add4;

 architecture behaviour of add4 is
begin
	increment: process(input) is
	  variable four: dlx_word := x"00000004";
	  variable temp: dlx_word;
	  variable wrap: dlx_word;
	  variable overflow: boolean;
	begin
		bv_addu(input, four, temp, overflow);
		if overflow then
			bv_divu(input, four, temp, wrap, overflow);
			output <= wrap after 5 ns;
		else
			output <= temp after 5 ns;
		end if;
	end process;
end behaviour;

--------------------------------------------------------
-- reg file 
--------------------------------------------------------
use work.dlx_types.all;
use work.bv_arithmetic.all;  

entity regfile is
     port (read_notwrite,clock : in bit; 
           regA,regB: in register_index; 
	   data_in: in  dlx_word; 
	   dataA_out,dataB_out: out dlx_word
	   );
end entity regfile; 

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;

library work;
use work.dlx_types.all;
use work.bv_arithmetic.all;

architecture behaviour of regfile is
	type reg_file is array(0 to 31) of dlx_word;
	signal reg: reg_file;
begin
	process(read_notwrite,clock,regA,regB)
		variable lv_a: std_logic_vector(regA'range);
		variable lv_b: std_logic_vector(regB'range);
		variable address_a: integer range 0 to 31;
		variable address_b: integer range 0 to 31;
	begin
		for i in regA'range loop
			if( regA(i) = '1') then
				lv_a(i) := '1';
			else
				lv_a(i) := '0';
			end if;
		end loop;

		for i in regB'range loop
			if( regB(i) = '1') then
				lv_b(i) := '1';
			else
				lv_b(i) := '0';
			end if;
		end loop;

		address_a := conv_integer(lv_a);
		address_b := conv_integer(lv_b);

		if( read_notwrite = '1' ) then
			dataA_out <= reg(address_a) after 5 ns;
			dataB_out <= reg(address_b) after 5 ns;
		elsif( clock = '1') then
			reg(address_a) <= data_in after 5 ns;
		end if;
	end process;
end architecture behaviour;

--------------------------------------------------------
-- data memory 
--------------------------------------------------------
use work.dlx_types.all;
use work.bv_arithmetic.all;

entity DM is
  port (
    address : in dlx_word;
    readnotwrite: in bit; 
    data_out : out dlx_word;
    data_in: in dlx_word; 
    clock: in bit); 
end DM;

architecture behaviour of DM is
  --type memtype is array (0 to 1024) of dlx_word;
  --signal data_memory : memtype;
  signal init: bit :='0';
begin  -- behaviour

  DM_behav: process(address,clock) is
    type memtype is array (0 to 1024) of dlx_word;
    variable data_memory : memtype;
  begin
    if init = '0' then
    -- fill this in by hand to put some values in there
       data_memory(1023) := B"00000101010101010101010101010101";
       data_memory(0) := B"00000000000000000000000000000001";
       data_memory(1) := B"00000000000000000000000000000010";
       init <= '1';
    end if;
    if clock'event and clock = '1' then
      if readnotwrite = '1' then
        -- do a read
        data_out <= data_memory(bv_to_natural(address)/4);
      else
        -- do a write
        data_memory(bv_to_natural(address)/4) := data_in; 
      end if;
    end if;


  end process DM_behav; 

end behaviour;

--------------------------------------------------------
-- instruction memory
--------------------------------------------------------
use work.dlx_types.all;
use work.bv_arithmetic.all;

entity IM is
  port (
    address : in dlx_word;
    instruction : out dlx_word;
    clock: in bit); 
end IM;

architecture behaviour of IM is
begin  -- behaviour

  IM_behav: process(address,clock) is
    type memtype is array (0 to 1024) of dlx_word;
    variable instr_memory : memtype;                   
  begin
    -- fill this in by hand to put some values in there
    -- first instr is 'LW R1,4092(R0)'		-- load R1 with mem(1023) = 1
    instr_memory(0) := B"10001100000000010000111111111100";
    -- next instr is 'ADD R2,R1,R1'		-- R2 = R1 + R1 = 2
    instr_memory(1) := B"00000000001000010001000000100000";
		     -- 000000/00001/00001/00010/00000/100000
    -- next instr is SW R2,8(R0)'		-- read R2 into mem(2) = 2
    instr_memory(2) := B"10101100000000100000000000001000";
    -- next instr is LW R3,8(R0)'		-- load R3 with mem(2) = 2
    instr_memory(3) := B"10001100000000110000000000001000";
    -- ADDU R2, R3, R3				-- R2 = R3 + R3 = 4
    instr_memory(4) := B"00000000011000110001000000100001";
		      -- 000000/00011/00011/00010/00000/100001
    -- ADDI R2, R3, 4				-- R2 = R3 + 4 = 6
    instr_memory(5) := B"00100000011000100000000000000100";
		      -- 001000/00011/00010/0000000000000100
    -- SUB R2, R2, R3 				-- R2 = R2 - R3 = 4
    instr_memory(6) := B"00000000010000110001000000100010";
		      -- 000000/00010/00011/00010/00000/100010
    -- SUBU R2, R2, R3				-- R2 = R2 - R3 = 2
    instr_memory(7) := B"00000000010000110001000000100011";
		      -- 000000/00010/00011/00010/00000/100011
    -- SUBI R2, R2, 2				-- R2 = R2 - 2 = 0
    instr_memory(8) := B"00101000010000100000000000000010";
		      -- 001010/00010/00010/0000000000000010
    -- ADDUI R2, R3, 8				-- R2 = R3 + 8 = 10
    instr_memory(9) := B"00000000011000110001000000100001";
	 	      -- 000000/00011/00010/0000000000100001
    -- MUL R2, R3, R3				-- R2 = R3 * R3 = 4
    instr_memory(10) := B"00000000011000110001000000001110";
	 	       -- 000000/00011/00011/00010/00000/001110
    -- MULU R2, R3, R2				-- R2 = R2 * R3 = 8
    instr_memory(11) := B"00000000011000100001000000010110";
	 	       -- 000000/00011/00010/00010/00000/010110
    -- AND R3, R2, R3				-- R2 = R2 and R3 = 2
    instr_memory(12) := B"00000000010000110001000000100100";
		       -- 000000/00010/00011/00011/00000/100100
    -- OR R2, R2, R3				-- R2 = R2 or R3 = 8
    instr_memory(13) := B"00000000011000100001000000100101";
		       -- 000000/00011/00010/00011/00000/100101
    -- SLT R3, R3, R2				-- R3 = R3 < R2 = 1
    instr_memory(14) := B"00000000011000100001100000101010";
		       -- 000000/00011/00010/00011/00000/101010
    -- SLTU R3, R2, R3				-- R3 = R2 < R3 = 0
    instr_memory(15) := B"00000000010000110001100000101011";
		      -- 000000/00010/00011/00011/00000/101011 
    -- ANDI R2, R2, FF				-- R2 = R2 and FF = 8
    instr_memory(16) := B"00110000010000100000000000001010";
		       -- 001100/00010/00010/0000000011111111
    -- SW R2,8(R0)'				-- read R2 into mem(2) = 8
    instr_memory(17) := B"10101100000000100000000000001000";
		       -- 101011/00000/00010/00000/00000/001000

    if clock'event and clock = '1' then
        -- do a read
        instruction <= instr_memory(bv_to_natural(address)/4);
    end if;
  end process IM_behav; 

end behaviour;







