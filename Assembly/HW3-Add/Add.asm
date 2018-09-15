Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
val1 WORD 1
apple WORD ?
pear WORD ?

.code
main proc
	
	invoke ExitProcess,0
main endp
end main