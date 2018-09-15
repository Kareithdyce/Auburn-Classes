Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	Pos SWORD 	 -2, -4, -6, -8
	ng SWORD		  1,  3,  5,  7
	mix1 SWORD -8,5,-3, 2,5,-6,8
	mix2 SWORD -1,3,-4,12,-5,3,-4
	addS DWORD TYPE mix2
	found BYTE "positive even number found ", 0
	notfound BYTE "no positive even number found", 0

	
.code
main proc
mov ecx, lengthof mix2
mov ebx, 0
mov eax, 0
L1:
	movsx eax, mix2[ebx]
	cmp eax, 0
	jle negative
		mov dx, 0
		push ecx
		mov cx, 2
		div cx
		pop ecx
		cmp dx, 0
		jne negative
		mov cx, 2
		mul cx
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