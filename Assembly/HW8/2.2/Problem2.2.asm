Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword
.data

.code
main proc
mov eax, 0A24E6C8Dh
mov edx, 0
mov ecx, 8
L1:
	rol eax, 4	
	mov ebx, 0
	mov bl, al
	rol ebx, 4
	mov bh, 0
	ror ebx, 4
	cmp ebx, 10
	jae skip
	cmp dh, 10
	jae part2
	rol dh, 4
	add dh, bl
	jmp skip
	part2:
	rol dl, 4
	add dl, bl

	skip:
	loop L1
	invoke ExitProcess,0
main endp
end main