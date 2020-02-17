add wave -position end  sim:/reg_file/data_in
add wave -position end  sim:/reg_file/readnotwrite
add wave -position end  sim:/reg_file/clock
add wave -position end  sim:/reg_file/data_out
add wave -position end  sim:/reg_file/reg_number

force -freeze sim:/reg_file/clock 1 1, 0 {100 ns} -r 200
force -freeze sim:/reg_file/readnotwrite 0 0
force -freeze sim:/reg_file/data_in 32'h11111111 0
force -freeze sim:/reg_file/data_in 32'h22222222 100
force -freeze sim:/reg_file/reg_number 5'h00 0
run 200 ns

force -freeze sim:/reg_file/data_in 32'h33333333 0
force -freeze sim:/reg_file/data_in 32'h44444444 100
force -freeze sim:/reg_file/readnotwrite 1 0
run 200 ns