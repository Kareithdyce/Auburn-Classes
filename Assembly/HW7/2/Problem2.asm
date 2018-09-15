Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword
.data
	KEY = 162
	my_name BYTE "Kareith Dyce", 0
.code
main proc
mov ecx, lengthof my_name
mov ebx, 0
mov edx, offset my_name
call WriteString
call crlf
L1:
	XOR my_name[ebx], KEY 		
	inc ebx
	loop L1
mov ecx, lengthof my_name
mov edx, offset my_name
call WriteString
mov ebx, 0
call crlf

L2:
	XOR my_name[ebx], KEY 		
	inc ebx
	loop L2

mov edx, offset my_name
call WriteString
	invoke ExitProcess,0
main endp
end main