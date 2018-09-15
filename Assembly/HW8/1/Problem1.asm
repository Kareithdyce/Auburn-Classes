Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	
.code
main proc
mov cl, 3
mov eax, 0
		mov al, 45h			
		rol al, cl		; 	 al = 2A
		mov al, 7Ah
		mov cl, 1
		ror al, Cl 		; 	 al = 
 		stc
		mov al, 64h
		mov cl, 2
		rcl al, cl		; 	 al = 
		stc
		mov al, 3Dh
		mov cl, 1
		rcr al, cl		; 	 al = 

	invoke ExitProcess,0
main endp
end main