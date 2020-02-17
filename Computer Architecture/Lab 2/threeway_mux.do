add wave -position end  sim:/threeway_mux/input_2
add wave -position end  sim:/threeway_mux/input_1
add wave -position end  sim:/threeway_mux/input_0
add wave -position end  sim:/threeway_mux/which
add wave -position end  sim:/threeway_mux/output

force -freeze sim:/threeway_mux/which 32'h00000002
force -freeze sim:/threeway_mux/input_1 32'h11111111 0
force -freeze sim:/threeway_mux/input_2 32'h22222222 0
force -freeze sim:/threeway_mux/input_0 32'h00000000 50
run 100 ns

force -freeze sim:/threeway_mux/which 32'h00000001
force -freeze sim:/threeway_mux/input_1 32'h11111111 0
force -freeze sim:/threeway_mux/input_2 32'h22222222 0
force -freeze sim:/threeway_mux/input_0 32'h00000000 50
run 100 ns

force -freeze sim:/threeway_mux/which 32'h00000000
force -freeze sim:/threeway_mux/input_1 32'h11111111 0
force -freeze sim:/threeway_mux/input_2 32'h22222222 0
force -freeze sim:/threeway_mux/input_0 32'h00000000 50
run 100 ns