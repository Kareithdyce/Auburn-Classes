Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword
	
.data
prompt BYTE "Enter a temperature in Celsius: ", 0  
promptC BYTE "The temperature in Celsius is ", 0  

temp SWORD  ? 
r word ?
remain BYTE "/5"
.code
main PROC
	mov edx, offset prompt
	call writestring
	call readInt
	mov temp, ax
	mov bx, 9
	imul bx
	mov dx, 0
	mov ebx, 5
	idiv bx
	mov r, dx
	add ax, 32
	mov edx, offset promptC
	call crlf
	call WriteString
	call writeint
	mov ax, r
	call writeint
	mov edx, offset remain
	call WriteString
	invoke ExitProcess,0
main endp

end main