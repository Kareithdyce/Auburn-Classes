Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword
	
.data
myName BYTE "Kareith Dyce", 0
copy BYTE SIZEOF myName DUP(0)


.code

main PROC
	INVOKE Str_copy, ADDR myName, ADDR copy
	mov edx, OFFSET copy
	Call WriteString
	invoke ExitProcess,0
main endp

end main