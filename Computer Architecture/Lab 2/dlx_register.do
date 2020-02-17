add wave -position insertpoint  \
sim:/dlx_register/in_val  \
sim:/dlx_register/clock  \
sim:/dlx_register/out_val  \

force -freeze sim:/dlx_register/in_val 32'h00000000 0
force -freeze sim:/dlx_register/in_val 32'h11111111 50
force -freeze sim:/dlx_register/clock 1 1, 0 {50 ns} -r 100
run 100 ns

force -freeze sim:/dlx_register/in_val 32'hFFFFFFFF 0
force -freeze sim:/dlx_register/in_val 32'h22222222 50
force -freeze sim:/dlx_register/clock 1 0, 0 {50 ns} -r 100
run 100 ns