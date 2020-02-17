add wave -position end  sim:/mux/input_1
add wave -position end  sim:/mux/input_0
add wave -position end  sim:/mux/which
add wave -position end  sim:/mux/output

force -freeze sim:/mux/which 32'h00000001 0
force -freeze sim:/mux/input_1 32'h11111111 0
force -freeze sim:/mux/input_0 32'h00000000 50
run 100 ns

force -freeze sim:/mux/which 32'h00000000 0
force -freeze sim:/mux/input_1 32'h11111111 0
force -freeze sim:/mux/input_0 32'h00000000 50
run 100 ns