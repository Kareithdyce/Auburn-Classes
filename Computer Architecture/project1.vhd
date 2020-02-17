entity project1 is 
   generic(prop_delay: Time := 15 ns);
   port(a_in: in bit; b_in: in bit; control: in bit;
             result: out bit);
end entity project1; 


architecture behaviour1 of project1 is
begin 
   process1 : process(a_in,b_in,control) is 
   
   begin
      if control = '1' then
         if a_in = '1' then 
            if b_in = '1' then
                       --  1 and 1 = 1 
               result <= '1' after prop_delay; 
            else
                       -- 1 and 0 = 0  
               result <= '0' after prop_delay; 
            end if;
         else
            if b_in = '1' then
                       --  0 and 1 = 0
               result <= '0' after prop_delay; 
            else
                       -- 0 and 0 = 0  
               result <= '0' after prop_delay; 
            end if;  
         end if;
      else
         if a_in = '1' then 
            if b_in = '1' then
                       --  1 or 1 = 1 
               result <= '1' after prop_delay; 
            else
                       -- 1 or 0 = 1  
               result <= '1' after prop_delay; 
            end if;
         else
            if b_in = '1' then
                       --  0 or 1 = 1
               result <= '1' after prop_delay; 
            else
                       -- 0 or 0 = 0  
               result <= '0' after prop_delay; 
            end if;  
         end if;
      end if; 
   end process process1; 
end architecture behaviour1; 

