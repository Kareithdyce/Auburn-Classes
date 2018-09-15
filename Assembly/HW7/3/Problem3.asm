Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
v BYTE "val1 is ",0
a BYTE "apple is ",0
p BYTE "pear is ",0
c1 BYTE "cx is ",0
b1 BYTE "bx is ",0

val1 SWORD 1
apple SWORD ?
pear SWORD ?

.code
main proc
		mov ecx, 0
		mov ebx, 1
		cmp bx, cx
		jne next
		cmp cx, val1
		jl next
		mov apple, 1
		jmp num2
	
	next:
		mov apple, 0
	
	num2:
		cmp bx, cx
		je next2
		cmp cx, val1
		jge next2
		mov pear, 0
		jmp num3

	next2:
		mov pear, 1
	num3:
		mov eax, ecx
		mov edx, offset c1
		call writestring
		call writeint
		call crlf
		
		mov eax, ebx
		mov edx, offset b1
		call writestring
		call writeint
		call crlf
		
		movsx eax, apple
		mov edx, offset a
		call writestring
		call writeint
		call crlf
		movsx eax, pear
		mov edx, offset p
		call writestring
		call writeint
		call crlf
		movsx eax, val1
		mov edx, offset v 
		call writestring
		call writeint
	invoke ExitProcess,0
main endp
end main