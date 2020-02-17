use work.bv_arithmetic.all;
use work.dlx_types.all;



entity aubie_controller is
    generic(
        prop_delay    : Time := 5 ns;
        extra_prop_delay : Time := 15 ns -- Extended prop_delay for allowing other signals to propagate first
    ); -- Controller propagation delay
    port(
      ir_control            :   in dlx_word;
      alu_out               :   in dlx_word;
      alu_error             :   in error_code;
      clock                 :   in bit;
      regfilein_mux         :   out threeway_muxcode;
      memaddr_mux           :   out threeway_muxcode;
      addr_mux              :   out bit;
      pc_mux                :   out threeway_muxcode;
      alu_func              :   out alu_operation_code;
      regfile_index         :   out register_index;
      regfile_readnotwrite  :   out bit;
      regfile_clk           :   out bit;
      mem_clk               :   out bit;
      mem_readnotwrite      :   out bit;
      ir_clk                :   out bit;
      imm_clk               :   out bit;
      addr_clk              :   out bit;
      pc_clk                :   out bit;
      op1_clk               :   out bit;
      op2_clk               :   out bit;
      result_clk            :   out bit
    );
end aubie_controller;


architecture behavior of aubie_controller is
begin
    behav: process(clock) is

        type state_type is range 1 to 20;
        variable state: state_type := 1;
        variable opcode: byte;
        variable destination, operand1, operand2 : register_index;
        variable stor_op : alu_operation_code := "0111";
        variable jz_op : alu_operation_code := "1100";
        variable logical_true : dlx_word := x"00000001";
        variable logical_false : dlx_word := x"00000000";

        begin
            if (clock'event and clock = '1') then
                opcode := ir_control(31 downto 24);
                destination := ir_control(23 downto 19);
                operand1 := ir_control(18 downto 14);
                operand2 := ir_control(13 downto 9);

                case state is
                    when 1 =>
                    -- Mem[PC] --> IR
                        memaddr_mux <= "00" after prop_delay; -- memory threeway_mux input_0 to read from PC
                        regfile_clk <= '0' after prop_delay;
                        mem_clk	<= '1' after prop_delay; -- High so it can output mem_out
                        mem_readnotwrite <= '1' after prop_delay; -- In state 1, we want to read from main memory and ignore data_in
                        ir_clk <= '1' after prop_delay; -- High so IR will be receiving signal from Memory[PC]
                        imm_clk <= '0' after prop_delay;
                        addr_clk <= '0' after prop_delay;
                        addr_mux <= '1' after prop_delay;
                        pc_clk <= '0' after prop_delay; -- Low so PC will output the current address it retains -- Initially zero
                        op1_clk	<= '0' after prop_delay;
                        op2_clk	<= '0' after prop_delay;
                        result_clk <= '0' after prop_delay;
                        
			state := 2;
                    when 2 =>  -- figure out which instruction
                        if opcode(7 downto 4) = "0000" then -- ALU op
                            state := 3;
                        elsif opcode = X"20" then  -- STO
                            state := 9;
                        elsif opcode = X"30" or opcode = X"31" then -- LD or LDI
                            state := 7;
                        elsif opcode = X"22" then -- STOR
                            state := 14;
                        elsif opcode = X"32" then -- LDR
                            state := 12;
                        elsif opcode = X"40" or opcode = X"41" then -- JMP or JZ
                            state := 16;
                        elsif opcode = X"10" then -- NOOP
                            state := 19;
                        else -- error: Bad opcode -- No need to do anything
                        end if;
                    when 3 => -- ALU op (Step1):  load op1 register from the regfile
                        regfile_index <= operand1 after prop_delay; -- The register_index
                        -- Needs to be high because we're doing a read op (ignore data_in and send regfile[reg_number] -> to op1 register)
                        regfile_readnotwrite <= '1' after prop_delay;
                        regfile_clk <= '1' after prop_delay; -- Needs to be high for a regfile operation
                        mem_clk <= '0' after prop_delay;
                        ir_clk <= '0' after prop_delay;
                        imm_clk <= '0' after prop_delay;
                        addr_clk <= '0' after prop_delay;
                        pc_clk <= '0' after prop_delay;
                        op1_clk <= '1' after prop_delay; -- op1_register clock needs to be high so it can accept regfile data_out
                        op2_clk <= '0' after prop_delay;
                        result_clk <= '0' after prop_delay;
                        state := 4;
                    when 4 => -- ALU op (Step2): load op2 register from the regfile
                        regfile_index <= operand2 after prop_delay; -- The register_index
                        regfile_readnotwrite <= '1' after prop_delay; -- Needs to be high because we're doing a read
                        regfile_clk <= '1' after prop_delay; -- Needs to be high for regfile operation
                        mem_clk <= '0' after prop_delay;
                        ir_clk <= '0' after prop_delay;
                        imm_clk <= '0' after prop_delay;
                        addr_clk <= '0' after prop_delay;
                        pc_clk <= '0' after prop_delay;
                        op1_clk <= '0' after prop_delay;
                        op2_clk <= '1' after prop_delay; 
                        result_clk <= '0' after prop_delay;
                        state := 5;
                    when 5 => -- ALU op (Step3):  perform ALU operation (Copy ALU output into result register)
                        alu_func <= opcode(3 downto 0) after prop_delay; -- The specific ALU operation denoted by the last 4 bits of the opcode
                        regfile_clk <= '0' after prop_delay;
                        mem_clk <= '0' after prop_delay;
                        ir_clk <= '0' after prop_delay;
                        imm_clk <= '0' after prop_delay;
                        addr_clk <= '0' after prop_delay;
                        pc_clk <= '0' after prop_delay;
                        op1_clk <= '0' after prop_delay;
                        op2_clk <= '0' after prop_delay;
                        result_clk <= '1' after prop_delay;
                        state := 6;
                    when 6 => 
                        regfilein_mux <= "00" after prop_delay; 
                        pc_mux <= "00" after prop_delay; 
                        regfile_index <= destination after prop_delay;
                        regfile_readnotwrite <= '0' after prop_delay; 
                        regfile_clk <= '1' after prop_delay;
                        ir_clk <= '0' after prop_delay;
                        imm_clk <= '0' after prop_delay;
                        addr_clk <= '0' after prop_delay;
                        pc_clk <= '1' after prop_delay; 
                        op1_clk <= '0' after prop_delay;
                        op2_clk <= '0' after prop_delay;
                        result_clk <= '0' after prop_delay;
                        state := 1;
                    when 7 => 
                        if (opcode = x"30") then
                   
                            pc_clk <= '1' after prop_delay;
                            pc_mux <= "00" after prop_delay; 
                            memaddr_mux <= "00" after prop_delay; 
                            addr_mux <= '1' after prop_delay; 
                            regfile_clk <= '0' after prop_delay;
                            mem_clk <= '1' after prop_delay;
                            mem_readnotwrite <= '1' after prop_delay; 
                            ir_clk <= '0' after prop_delay;
                            imm_clk <= '0' after prop_delay;
                            addr_clk <= '1' after prop_delay;
                            op1_clk <= '0' after prop_delay;
                            op2_clk <= '0' after prop_delay;
                            result_clk <= '0' after prop_delay;
                        elsif (opcode = x"31") then -- LDI
                            pc_clk <= '1' after prop_delay;
                            pc_mux <= "00" after prop_delay; 
                            memaddr_mux <= "00" after prop_delay;
                            regfile_clk <= '0' after prop_delay;
                            mem_clk <= '1' after prop_delay;
                            mem_readnotwrite <= '1' after prop_delay;
                            ir_clk <= '0' after prop_delay;
                            imm_clk <= '1' after prop_delay;
                            addr_clk <= '0' after prop_delay;
                            op1_clk <= '0' after prop_delay;
                            op2_clk <= '0' after prop_delay;
                            result_clk <= '0' after prop_delay;
                        end if;
                        state := 8;
                     when 8 => -- LD or LDI (Step2)
                        if (opcode = x"30") then -- LD
                            regfilein_mux <= "01" after prop_delay; -- mux selector for memory out
                            memaddr_mux <= "01" after prop_delay; 
                            regfile_index <= destination after prop_delay;
                            regfile_readnotwrite <= '0' after prop_delay;
                            regfile_clk <= '1' after prop_delay;
                            mem_clk <= '1' after prop_delay;
                            mem_readnotwrite <= '1' after prop_delay;
                            ir_clk <= '0' after prop_delay;
                            imm_clk <= '0' after prop_delay;
                            addr_clk <= '0' after prop_delay; -- Addr clk should retain its old value
                            op1_clk <= '0' after prop_delay;
                            op2_clk <= '0' after prop_delay;
                            result_clk <= '0' after prop_delay;
                            pc_clk <= '0' after prop_delay, '1' after extra_prop_delay; 
                            pc_mux <= "00" after extra_prop_delay;
                            

                        elsif (opcode = x"31") then -- LDI
                        -- Copy immediate register into the destination register. Increment PC.
                        -- Immed --> Regs[IR[dest]]. PC --> PC+1.
                            regfilein_mux <= "10" after prop_delay; -- mux selector for immediate register out
                            regfile_index <= destination after prop_delay;
                            regfile_readnotwrite <= '0' after prop_delay;
                            regfile_clk <= '1' after prop_delay;
                            mem_clk <= '0' after prop_delay;
                            ir_clk <= '0' after prop_delay;
                            imm_clk <= '1' after prop_delay;
                            addr_clk <= '0' after prop_delay;
                            op1_clk <= '0' after prop_delay;
                            op2_clk <= '0' after prop_delay;
                            result_clk <= '0' after prop_delay;
                            pc_clk <= '0' after prop_delay, '1' after extra_prop_delay;
                            pc_mux <= "00" after extra_prop_delay; 

                        end if;
                        state := 1;
                    when 9 => 
                        pc_mux <= "00" after prop_delay;
                        pc_clk <= '1' after prop_delay;
                        state := 10;
                    when 10 => 
                        memaddr_mux <= "00" after prop_delay;
                        addr_mux <= '1' after prop_delay; 
                        regfile_clk <= '0' after prop_delay;
                        mem_clk <= '1' after prop_delay; 
                        mem_readnotwrite <= '1' after prop_delay; 
                        ir_clk <= '0' after prop_delay;
                        imm_clk <= '0' after prop_delay;
                        addr_clk <= '1' after prop_delay; 
                        pc_clk <= '0' after prop_delay; 
                        op1_clk <= '0' after prop_delay;
                        op2_clk <= '0' after prop_delay;
                        result_clk <= '0' after prop_delay;

                        state := 11;
                    when 11 => memaddr_mux <= "00" after prop_delay;
                        pc_mux <= "01" after prop_delay, "00" after extra_prop_delay;
                        regfile_index <= operand1 after prop_delay;
                        regfile_readnotwrite <= '1' after prop_delay; 
                        regfile_clk <= '1' after prop_delay;
                        mem_clk <= '1' after prop_delay; 
                        mem_readnotwrite <= '0' after prop_delay; 
                        ir_clk <= '0' after prop_delay;
                        imm_clk <= '0' after prop_delay;
                        addr_clk <= '0' after prop_delay; 
                        pc_clk <= '1' after prop_delay;
                        op1_clk <= '0' after prop_delay;
                        op2_clk <= '0' after prop_delay;
                        result_clk <= '0' after prop_delay;
                        state := 1;
                    when 12 =>
                        addr_mux <= '0' after prop_delay; 
                        regfile_index <= operand1 after prop_delay;
                        regfile_readnotwrite <= '1' after prop_delay;
                        regfile_clk <= '1' after prop_delay;
                        mem_clk <= '0' after prop_delay;
                        ir_clk <= '0' after prop_delay;
                        imm_clk <= '0' after prop_delay;
                        addr_clk <= '1' after prop_delay;
                        pc_clk <= '0' after prop_delay;
                        op1_clk <= '0' after prop_delay;
                        op2_clk <= '0' after prop_delay;
                        result_clk <= '0' after prop_delay;

                        state := 13;
                    when 13 =>
                        regfilein_mux <= "01" after prop_delay; 
                        memaddr_mux <= "01" after prop_delay; 
                        regfile_index <= destination after prop_delay;
                        regfile_readnotwrite <= '0' after prop_delay;
                        regfile_clk <= '1' after prop_delay;
                        mem_clk <= '1' after prop_delay;
                        mem_readnotwrite <= '1' after prop_delay;
                        ir_clk <= '0' after prop_delay;
                        imm_clk <= '0' after prop_delay;
                        addr_clk <= '0' after prop_delay; 
                        op1_clk <= '0' after prop_delay;
                        op2_clk <= '0' after prop_delay;
                        result_clk <= '0' after prop_delay;
                        pc_clk <= '0' after prop_delay, '1' after extra_prop_delay;
                        pc_mux <= "00" after extra_prop_delay;
                        
                        state := 1;
                    when 14 => 
                        addr_mux <= '0' after prop_delay;
                        regfile_index <= destination after prop_delay;
                        regfile_readnotwrite <= '1' after prop_delay;
                        regfile_clk <= '1' after prop_delay;
                        mem_clk <= '0' after prop_delay;
                        ir_clk <= '0' after prop_delay;
                        imm_clk <= '0' after prop_delay;
                        addr_clk <= '1' after prop_delay;
                        pc_clk <= '0' after prop_delay;
                        op1_clk <= '0' after prop_delay;
                        op2_clk <= '0' after prop_delay;
                        result_clk <= '0' after prop_delay;

                        state := 15;
                    
                    when 15 =>
                        memaddr_mux <= "00" after prop_delay;
                        pc_mux <= "01" after prop_delay, "00" after extra_prop_delay;
                        alu_func <= stor_op after prop_delay;
                        regfile_index <= operand1 after prop_delay;
                        regfile_readnotwrite <= '1' after prop_delay;
                        regfile_clk <= '1' after prop_delay;
                        mem_clk <= '1' after prop_delay;
                        mem_readnotwrite <= '0' after prop_delay;
                        ir_clk <= '0' after prop_delay;
                        imm_clk <= '0' after prop_delay;
                        addr_clk <= '0' after prop_delay; 
                        pc_clk <= '1' after prop_delay;
                        op1_clk <= '1' after prop_delay; 
                        op2_clk <= '1' after prop_delay;
                        result_clk <= '1' after prop_delay;

                        state := 1;
                       
                    when 16 => -- JMP or JZ (Step1): Increment PC --> PC+1
                        pc_mux <= "00" after prop_delay;
                        pc_clk <= '1' after prop_delay;
                        state := 17;
                        
                    when 17 => -- JMP or JZ (Step2):
                        pc_clk <= '0' after prop_delay;
                        memaddr_mux <= "00" after prop_delay; 
                        addr_mux <= '1' after prop_delay; 
                        regfile_clk <= '0' after prop_delay;
                        mem_clk <= '1' after prop_delay;
                        mem_readnotwrite <= '1' after prop_delay; 
                        ir_clk <= '0' after prop_delay;
                        imm_clk <= '0' after prop_delay;
                        addr_clk <= '1' after prop_delay;
                        op1_clk <= '0' after prop_delay;
                        op2_clk <= '0' after prop_delay;
                        result_clk <= '0' after prop_delay;
                        state := 18;
                        if (opcode = x"40") then -- JMP
                            state := 18;
                        else 
				state := 20;
                        end if; 
                        
                    when 18 => -- JMP or JZ (Step3):
                        if (opcode = x"40") then -- JMP
                            pc_mux <= "01" after prop_delay;
                            pc_clk <= '1' after prop_delay;
                        end if;
                        if (opcode = x"41") then -- JZ
                            if (alu_out = logical_true) then
                                pc_mux <= "01" after prop_delay;
                                pc_clk <= '1' after prop_delay;
                            else
                                pc_clk <= '1' after prop_delay;
                                pc_mux <= "00" after prop_delay;
                            end if;
                        end if;
                        state := 1;
                        
                    when 19 => -- NOOP: Only increments PC
                        pc_mux <= "00" after prop_delay;
                        pc_clk <= '1' after prop_delay;

                        state := 1;
                       
                    when 20 => -- JZ Intermediate Cycle
                        alu_func <= jz_op after prop_delay;
                        regfile_index <= operand1 after prop_delay;
                        regfile_readnotwrite <= '1' after prop_delay;
                        regfile_clk <= '1' after prop_delay;
                        mem_clk <= '0' after prop_delay;
                        ir_clk <= '0' after prop_delay;
                        imm_clk <= '0' after prop_delay;
                        addr_clk <= '0' after prop_delay;
                        pc_clk <= '0' after prop_delay;
                        op1_clk <= '1' after prop_delay;
                        op2_clk <= '1' after prop_delay;
                        result_clk <= '1' after prop_delay;

                        

                        state := 18;
                    when others => null;
                end case;
            elsif clock'event and clock = '0' then
            -- reset all register clocks. State 1 should set appropriate values during fetch
                regfile_clk <= '0' after prop_delay;
                mem_clk <= '0' after prop_delay;
                ir_clk <= '0' after prop_delay;
                imm_clk <= '0' after prop_delay;
                addr_clk <= '0' after prop_delay;
                pc_clk <= '0' after prop_delay;
                op1_clk <= '0' after prop_delay;
                op2_clk <= '0' after prop_delay;
                result_clk <= '0' after prop_delay;
            end if;
        end process behav;
end behavior;
