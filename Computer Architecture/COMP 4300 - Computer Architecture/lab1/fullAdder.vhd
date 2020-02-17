entity fullAdder is
	generic (prop_delay: Time := 10ns);
	port(a_in, b_in, carry_in: in bit;
		sum, carry_out: out bit);
end entity fullAdder;

architecture behaviour1 of fullAdder is
begin
	addProcess : process(a_in,b_in,carry_in) is
	begin
		if a_in = '0' and b_in = '0' then
			if carry_in = '0' then
				sum <= '0' after prop_delay; 
				carry_out <= '0' after prop_delay;
			else 
				sum <= '1' after prop_delay;
				carry_out <= '0' after prop_delay;
			end if;
		elsif a_in = '0' and b_in = '1' then
			if carry_in = '0' then
				sum <= '1' after prop_delay;
				carry_out <= '0' after prop_delay;
			else
				sum <= '0' after prop_delay;
				carry_out <= '1' after prop_delay;
			end if;
		elsif a_in = '1' and b_in = '0' then
			if carry_in = '0' then
				sum <= '1' after prop_delay;
				carry_out <= '0' after prop_delay;
			else
				sum <= '0' after prop_delay;
				carry_out <= '1' after prop_delay;
			end if;
		elsif a_in = '1' and b_in = '1' then
			if carry_in = '0' then
				sum <= '0' after prop_delay;
				carry_out <= '1' after prop_delay;
			else
				sum <= '1' after prop_delay;
				carry_out <= '1' after prop_delay;
			end if;
		end if;
	end process addProcess;
end architecture behaviour1;