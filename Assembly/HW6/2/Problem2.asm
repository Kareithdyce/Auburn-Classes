Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword


.data
PromptUser  BYTE "Please enter a value:", 0
Oranges     SWORD 15 DUP(?) 

.code
main proc
mov ebx, 0
mov ecx, lengthof Oranges
L1:
	mov edx, offset PromptUser
	Call WriteString
	Call ReadInt
	mov Oranges[ebx], ax
	add ebx, type Oranges
	Loop L1

mov ebx, 0
mov ecx, lengthof Oranges
L2:	
	movsx eax, Oranges[ebx]
	;and eax, sign
	call writeInt
	call crlf
	add ebx, type Oranges
	loop L2	
	invoke ExitProcess,0
main endp
end main