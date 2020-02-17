------------------------------------------------
-- 32-bit register - done - tested
------------------------------------------------
library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;

library work;
use work.dlx_types.all;
use work.bv_arithmetic.all;

entity dlx_register is 
	generic(prop_delay: Time := 5 ns);
	port(
	in_val: in dlx_word; 
	clock: in bit; 
	out_val: out dlx_word); 
end entity dlx_register;

architecture behavior of dlx_register is
begin
	dlx_reg_process: process(in_val, clock) is
	begin
		if clock = '1' then
			out_val <= in_val after prop_delay;
		end if;
	end process dlx_reg_process;
end behavior;

------------------------------------------------
-- 32-bit two-way multiplexer - done - tested
------------------------------------------------
library work;
use work.dlx_types.all;
use work.bv_arithmetic.all;

entity mux is 
	generic(prop_delay: Time := 5 ns);
	port (
	input_0,input_1: in dlx_word; 
	which: in bit; 
	output: out dlx_word); 
end entity mux;

architecture behavior of mux is
begin
	mux_process: process(input_0, input_1, which) is
	begin
		if which = '0' then
			output <= input_0 after prop_delay;
		elsif which = '1' then
			output <= input_1 after prop_delay;
		end if;
	end process mux_process;
end behavior;

------------------------------------------------
-- sign extender - done - tested
------------------------------------------------
library work;
use work.dlx_types.all;
use work.bv_arithmetic.all;

entity sign_extend is 
	generic(prop_delay: Time := 5 ns);
	port (
	input: in half_word; 
	output: out dlx_word); 
end entity sign_extend;

architecture behavior of sign_extend is
begin
	sign_ex_process: process(input) is
	variable positiv: half_word := "0000000000000000";
	variable negativ: half_word := "1111111111111111";
	begin
		if input(15) = '0' then
			output <= positiv & input after prop_delay;
		elsif input(15) = '1' then
			output <= negativ & input after prop_delay;
		end if; 
	end process sign_ex_process;
end behavior;

------------------------------------------------
-- PC incrementer - done - tested
------------------------------------------------
library IEEE;
use IEEE.std_logic_unsigned.all;

library work;
use work.dlx_types.all;
use work.bv_arithmetic.all;

entity add4 is 
	generic(prop_delay: Time := 5 ns);
	port (
	input: in dlx_word; 
	output: out dlx_word); 
end entity add4;

architecture behavior of add4 is
begin
	add4_process: process(input) is
	variable four: dlx_word := x"00000004";
	variable temp: dlx_word;
	variable wrap: dlx_word;
	variable overflow_flag: boolean;
	begin
		--output <= input after prop_delay;
		bv_addu(input, four, temp, overflow_flag);
		--temp <= input + four;
		if overflow_flag then
			bv_divu(input, four, temp, wrap, overflow_flag);
			output <= wrap after prop_delay;
		else
			output <= temp after prop_delay;
		end if;
	end process add4_process;
end behavior;

------------------------------------------------
-- register file
------------------------------------------------
library work;
use work.dlx_types.all;
use work.bv_arithmetic.all;

entity regfile is 
	generic(prop_delay: Time := 5 ns);
	port (
	read_notwrite,clock : in bit; 
	regA,regB: in register_index; 
	data_in: in dlx_word; 
	dataA_out,dataB_out: out dlx_word ); 
end entity regfile;

architecture behavior of regfile is
type reg_file is array (31 downto 0) of dlx_word;
begin
	regfile_process: process(read_notwrite, clock, regA, regB, data_in) is
	variable registers: reg_file;
	begin
		if clock = '1' then
			if read_notwrite = '0' then
				registers(bv_to_integer(regA)) := data_in;
			elsif read_notwrite = '1' then
				dataA_out <= registers(bv_to_integer(regA)) after prop_delay;
				dataB_out <= registers(bv_to_integer(regB)) after prop_delay;
			end if;
		end if;
	
	end process regfile_process;
end behavior;