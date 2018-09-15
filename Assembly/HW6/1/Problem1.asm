.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	Dog SWORD 	 8
	Cat SWORD	-25
	Mouse SWORD   -36
	Horse SWORD   -102


.code
main proc
	movzx	edx, Dog				
	movzx	eax, Cat
	movzx	ebx,	Mouse		
	movzx	ecx,	Horse	
	add edx, eax
	sub edx, ebx
	add edx, ecx

	invoke ExitProcess,0
main endp
end main