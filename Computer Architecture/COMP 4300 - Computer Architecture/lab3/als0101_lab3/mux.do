add wave -position insertpoint \
mux/input_0 \
mux/input_1 \
mux/which \
mux/output \

force -freeze mux/input_0 32'hAAAAAAAA 0
force -freeze mux/input_1 32'hBBBBBBBB 0
force -freeze mux/which 1'h0 0
force -freeze mux/which 1'h1 50
force -freeze mux/which 1'h0 100
force -freeze mux/which 1'h1 150
run 200 ns