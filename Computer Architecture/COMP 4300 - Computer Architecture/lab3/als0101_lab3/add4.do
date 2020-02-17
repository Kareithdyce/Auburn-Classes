add wave -position interpoint \
add4/input \
add4/output \

force -freeze add4/input 32'h00000000 0
force -freeze add4/input 32'h0000000C 50
force -freeze add4/input 32'hFFFFFFFC 100
force -freeze add4/input 32'hFFFFFFFF 150
run 200 ns
