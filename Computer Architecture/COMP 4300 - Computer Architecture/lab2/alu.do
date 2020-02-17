add wave -position interpoint \
alu/operand1 \
alu/operand2 \
alu/operation \
alu/signed \
alu/result \
alu/error

force -freeze alu/operand1 32'h00000005 0
force -freeze alu/operand2 32'h00000006 0
force -freeze alu/operation 4'h0 0
force -freeze alu/signed 1'h0 0
run 100 ns

force -freeze alu/operand1 32'hFFFFFFFF 0
force -freeze alu/operand2 32'h00000001 0
force -freeze alu/operation 4'h0 0
force -freeze alu/signed 1'h0 0
run 100 ns

force -freeze alu/operand1 32'hFFFFFFF6 0
force -freeze alu/operand2 32'hFFFFFFF3 0
force -freeze alu/operation 4'h0 0
force -freeze alu/signed 1'h1 0
run 100 ns

force -freeze alu/operand1 32'h80000000 0
force -freeze alu/operand2 32'h80000001 0
force -freeze alu/operation 4'h0 0
force -freeze alu/signed 1'h1 0
run 100 ns

force -freeze alu/operand1 32'h7FFFFFFF 0
force -freeze alu/operand2 32'h00000001 0
force -freeze alu/operation 4'h0 0
force -freeze alu/signed 1'h1 0
run 100 ns

force -freeze alu/operand1 32'hAAAAAAAA 0
force -freeze alu/operand2 32'h11111111 0
force -freeze alu/operation 4'h1 0
force -freeze alu/signed 1'h0 0
run 100 ns

force -freeze alu/operand1 32'h00000001 0
force -freeze alu/operand2 32'h0000000A 0
force -freeze alu/operation 4'h1 0
force -freeze alu/signed 1'h0 0
run 100 ns

force -freeze alu/operand1 32'h0AAAAAAA 0
force -freeze alu/operand2 32'h11111111 0
force -freeze alu/operation 4'h1 0
force -freeze alu/signed 1'h1 0
run 100 ns

force -freeze alu/operand1 32'hFFFFFFF6 0
force -freeze alu/operand2 32'hFFFFFFFB 0
force -freeze alu/operation 4'h1 0
force -freeze alu/signed 1'h1 0
run 100 ns

force -freeze alu/operand1 32'h7FFFFFFF 0
force -freeze alu/operand2 32'h80000000 0
force -freeze alu/operation 4'h1 0
force -freeze alu/signed 1'h1 0
run 100 ns

force -freeze alu/operand1 32'h80000000 0
force -freeze alu/operand2 32'h7FFFFFFF 0
force -freeze alu/operation 4'h1 0
force -freeze alu/signed 1'h1 0
run 100 ns

force -freeze alu/operand1 32'h99999999 0
force -freeze alu/operand2 32'h55555555 0
force -freeze alu/operation 4'h2 0
force -freeze alu/signed 1'h0 0
run 100 ns

force -freeze alu/operand1 32'hEEEEEEEE 0
force -freeze alu/operand2 32'h11111111 0
force -freeze alu/operation 4'h2 0
force -freeze alu/signed 1'h0 0
run 100 ns

force -freeze alu/operand1 32'h99999999 0
force -freeze alu/operand2 32'h55555555 0
force -freeze alu/operation 4'h3 0
force -freeze alu/signed 1'h0 0
run 100 ns

force -freeze alu/operand1 32'hEEEEEEEE 0
force -freeze alu/operand2 32'h11111111 0
force -freeze alu/operation 4'h3 0
force -freeze alu/signed 1'h0 0
run 100 ns

force -freeze alu/operand1 32'hFFFFFFFF 0
force -freeze alu/operand2 32'h22222222 0
force -freeze alu/operation 4'hB 0
force -freeze alu/signed 1'h0 0
run 100 ns

force -freeze alu/operand1 32'h33333333 0
force -freeze alu/operand2 32'hAAAAAAAA 0
force -freeze alu/operation 4'hB 0
force -freeze alu/signed 1'h0 0
run 100 ns

force -freeze alu/operand1 32'h00000003 0
force -freeze alu/operand2 32'h00000005 0
force -freeze alu/operation 4'hE 0
force -freeze alu/signed 1'h0 0
run 100 ns

force -freeze alu/operand1 32'hFFFFFFFF 0
force -freeze alu/operand2 32'h00000002 0
force -freeze alu/operation 4'hE 0
force -freeze alu/signed 1'h0 0
run 100 ns

force -freeze alu/operand1 32'hFFFFFFFD 0
force -freeze alu/operand2 32'hFFFFFFFB 0
force -freeze alu/operation 4'hE 0
force -freeze alu/signed 1'h1 0
run 100 ns

force -freeze alu/operand1 32'hFFFFFFFD 0
force -freeze alu/operand2 32'h00000005 0
force -freeze alu/operation 4'hE 0
force -freeze alu/signed 1'h1 0
run 100 ns

force -freeze alu/operand1 32'h80000000 0
force -freeze alu/operand2 32'h00000002 0
force -freeze alu/operation 4'hE 0
force -freeze alu/signed 1'h1 0
run 100 ns

force -freeze alu/operand1 32'h7FFFFFFF 0
force -freeze alu/operand2 32'h00000002 0
force -freeze alu/operation 4'hE 0
force -freeze alu/signed 1'h1 0
run 100 ns