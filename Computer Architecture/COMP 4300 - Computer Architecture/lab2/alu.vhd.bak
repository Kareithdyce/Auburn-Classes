library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;

library work;
use work.dlx_types.all;
use work.bv_arithmetic.all;

entity ALU is 
	generic(prop_delay: Time := 5ns);	
	port(
	operand1, operand2: in dlx_word; 
	operation: in alu_operation_code;
	signed: in bit;
	result: out dlx_word; 
	error: out error_code); 
end entity ALU;


architecture behavior of alu is
begin
	switchProcess : process(operand1, operand2, signed, operation) is
	variable temp: dlx_word := x"00000000";
	variable x: dlx_word;
	variable overflow_flag: boolean;
	begin 
		error <= "0000";
		case(operation) is
			when "0000" => --addition*****
				if signed = '0' then
					bv_addu(operand1, operand2, temp, overflow_flag);
					if overflow_flag then
						error <= "0001" after prop_delay;
					end if;
				elsif signed = '1' then
					bv_add(operand1, operand2, temp, overflow_flag);
					if operand1(31) = '0' and operand2(31) = '0' then
						if temp(31) = '1' then -- (+)+(+)=(-)
							error <= "0001" after prop_delay;
						end if;
					elsif operand1(31) = '1' and operand2(31) = '1' then
						if temp(31) = '0' then	-- (-)+(-)=(+)
							error <= "0001" after prop_delay;
						end if;
					end if;
				end if;
				result <= temp after prop_delay;

			when "0001" => -- subtraction*****
				if signed = '0' then
					bv_subu(operand1, operand2, temp, overflow_flag);
					if overflow_flag then
						error <= "0001" after prop_delay;
					end if;
				elsif signed = '1' then
					bv_sub(operand1, operand2, temp, overflow_flag);
					if overflow_flag then
						error <= "0001" after prop_delay;
					end if;
				end if;
				result <= temp after prop_delay;

			when "0010" => -- and*****
				for i in 31 downto 0 loop
					if operand1(i) = '1' then
						if operand2(i) = '1' then
							temp(i):= '1';
						else
							temp(i):= '0';
						end if;
					else 
						temp(i):='0';
					end if;
				end loop;
				result <= temp after prop_delay;

			when "0011" => -- or*****
				for i in 31 downto 0 loop
					if operand1(i) = '1' then
						temp(i) := '1';
					elsif operand2(i) = '1' then
						temp(i) := '1';
					else
						temp(i) := '0';
					end if;
				end loop;
				result <= temp after prop_delay;

			when "0100" => -- xor
				result <= "00000000000000000000000000000000" after prop_delay;

			when "0101" => -- unused
				result <= "00000000000000000000000000000000" after prop_delay;

			when "0110" => -- shift left logical 
				result <= "00000000000000000000000000000000" after prop_delay;

			when "0111" => -- shift right logical
				result <= "00000000000000000000000000000000" after prop_delay;

			when "1000" => -- shift right arithmetic
				result <= "00000000000000000000000000000000" after prop_delay;

			when "1001" => -- SEQ
				result <= "00000000000000000000000000000000" after prop_delay;

			when "1010" => -- SNE
				result <= "00000000000000000000000000000000" after prop_delay;

			when "1011" => -- set less than*****
				bv_subu(operand1, operand2, temp, overflow_flag);
					if overflow_flag then
						result <= "00000000000000000000000000000001" after prop_delay;
					else 
						result <= "00000000000000000000000000000000" after prop_delay;
					end if;

			when "1100" => -- SGT
				result <= "00000000000000000000000000000000" after prop_delay;

			when "1101" => -- unused
				result <= "00000000000000000000000000000000" after prop_delay;

			when "1110" => -- multiplication*****
				if signed = '0' then
					bv_multu(operand1, operand2, temp, overflow_flag);
					if overflow_flag then
						error <= "0001" after prop_delay;
					end if;
				elsif signed = '1' then
					bv_mult(operand1, operand2, temp, overflow_flag);
					if overflow_flag then
						error <= "0001" after prop_delay;
					elsif operand1(31) = '0' and operand2(31) = '0' then
						if temp(31) = '1' then -- (+)*(+)=(-)
							error <= "0001" after prop_delay;
						end if;
					elsif operand1(31) = '1' and operand2(31) = '0' then
						if temp(31) = '0' then -- (-)*(+)=(+)
							error <= "0001" after prop_delay;
						end if;
					elsif operand1(31) = '0' and operand2(31) = '1' then
						if temp(31) = '0' then -- (+)*(-)=(+)
							error <= "0001" after prop_delay;
						end if;
					elsif operand1(31) = '1' and operand2(31) = '1' then
						if temp(31) = '1' then -- (-)*(-)=(-)
							error <= "0001" after prop_delay;
						end if;
					end if;
				end if;
				result <= temp after prop_delay;

			when "1111" => -- division
				result <= "00000000000000000000000000000000" after prop_delay;
		end case;
	end process switchProcess;
end behavior;