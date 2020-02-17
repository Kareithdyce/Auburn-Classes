add wave -position interpoint \
regfile/clock \
regfile/read_notwrite \
regfile/regA \
regfile/regB \
regfile/data_in \
regfile/dataA_out \
regfile/dataB_out \

force -freeze regfile/clock 1'h1 0
force -freeze regfile/regA 5'h2 0
force -freeze regfile/read_notwrite 1'h1 0
force -freeze regfile/data_in 32'hAAAAAAAA 50
force -freeze regfile/read_notwrite 1'h0 50
force -freeze regfile/read_notwrite 1'h1 100
force -freeze regfile/read_notwrite 1'h0 150
force -freeze regfile/regA 5'h3 200
force -freeze regfile/data_in 32'hBBBBBBBB 250
force -freeze regfile/read_notwrite 1'h0 250
force -freeze regfile/read_notwrite 1'h1 300
force -freeze regfile/clock 1'h0 350
force -freeze regfile/read_notwrite 1'h1 350
force -freeze regfile/regA 5'h2 350
force -freeze regfile/clock 1'h1 400
run 500 ns
