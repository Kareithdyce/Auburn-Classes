Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword
	

.data
prompt BYTE "My string is: ", 0
look BYTE "Looking for ",0
myName BYTE "finals",0
found BYTE " Found",0
notFound BYTE "Not Found", 0

.code
main Proc
	mov edx, OFFSET prompt
	call writestring
	mov edx, OFFSET myName
	call writestring
	call crlf
	mov edi,OFFSET myName
	INVOKE Str_length,  ADDR myName 
	mov ecx, eax
	mov edx, offset look
	call writestring
	mov al, 'l'	
	call writechar
	call crlf
	cld
	repne scasb	
	jnz quit
	dec edi	
	mov al, [edi]
	call writechar
	mov edx, offset found
	call writestring
	jmp fin
	quit:
		mov edx, offset notFound
		call writestring
	fin:
		invoke ExitProcess,0
main endp

end main