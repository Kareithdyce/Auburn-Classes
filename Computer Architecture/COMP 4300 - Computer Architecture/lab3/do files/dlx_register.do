add wave -position interpoint \
dlx_register/clock \
dlx_register/in_val \
dlx_register/out_val \

force -freeze dlx_register/clock 1'h1 0
force -freeze dlx_register/in_val 32'hAAAAAAAA 0
force -freeze dlx_register/clock 1'h0 50
force -freeze dlx_register/in_val 32'h00000000 100
force -freeze dlx_register/clock 1'h1 150
run 200 ns
