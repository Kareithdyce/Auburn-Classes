Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
method1 BYTE "The sum using method 1 is ",0
method2 BYTE "The sum using method 2 is ",0
diff BYTE "The difference between the two methods is ",0

base WORD 12
height WORD 20
sum1 WORD ?
sum2 WORD ?

.code 
main proc

	movzx ebx, height
	movzx ecx, base
	mov eax, ecx
	mul bx
	shr ax, 1	
	add sum1, ax
	shr ax, 2

	add ax, sum1
	mov sum1, ax
	mov edx, offset method1
	call writestring
	call writeint
	call crlf
	
	movzx ebx, height
	movzx ecx, base
	
	L1:
	mov eax, ecx
	mul bx
	shr ax, 1
	jz next
	add sum2, ax
	shr bx, 1
	shr cx, 1
	jmp L1
next:
movzx eax, sum2
mov edx, offset method2
call writestring
call writeint
call crlf
	

mov ax, sum2
sub ax, sum1
mov edx, offset diff
call writestring
call writeint

	main ENDP
END main