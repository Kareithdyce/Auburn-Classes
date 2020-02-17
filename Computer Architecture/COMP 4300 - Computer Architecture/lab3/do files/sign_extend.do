add wave -position interpoint \
sign_extend/input \
sign_extend/output \

force -freeze sign_extend/input 16'h7FFF 0
force -freeze sign_extend/input 16'h8000 50
force -freeze sign_extend/input 16'hFFFF 100
force -freeze sign_extend/input 16'h1111 150
run 200 ns