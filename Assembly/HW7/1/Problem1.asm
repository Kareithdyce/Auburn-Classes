Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	Pos WORD 	3,5,7,9,11
	ng WORD   2,4,5,6,7,8
	mix2 WORD 4,12,5,3,4
	addS DWORD TYPE mix2
	found BYTE " Odd integer found", 0
	notfound BYTE "No odd integer found", 0

	
.code
main proc
mov ecx, lengthof mix2
mov ebx, 0
mov eax, 0
L1:
	movsx eax, mix2[ebx]
	mov dx, 0
	push ecx
	mov cx, 2
	div cx
	pop ecx
	cmp dx, 0
	je negative
	mov cx, 2
	mul cx
	inc ax
	mov edx, offset found
	Call Writestring
	Call WriteInt
	jmp next
	negative:
		add ebx, addS
	loop L1

	mov edx, offset notfound
	Call Writestring
next:

	invoke ExitProcess,0
main endp
end main