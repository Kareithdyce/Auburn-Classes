add wave -position insertpoint  \
sim:/pcplusone/input  \
sim:/pcplusone/clock  \
sim:/pcplusone/output  \

force -freeze sim:/pcplusone/input 32'h55555555 0
force -freeze sim:/pcplusone/clock 0 1, 0 {50 ns} -r 100
run 100 ns

force -freeze sim:/pcplusone/input 32'h00000000 0
force -freeze sim:/pcplusone/clock 1 1, 0 {50 ns} -r 100
run 100 ns

force -freeze sim:/pcplusone/input 32'hFFFFFFFF 0
force -freeze sim:/pcplusone/clock 1 0, 0 {50 ns} -r 100
run 100 ns