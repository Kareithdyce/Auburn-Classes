.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

Include Irvine32.inc
Title My first assembly program

.data
str1 BYTE "Kareith Dyce",0

.code 
main proc
	mov edx, offset str1
	Call WriteString
	exit
main ENDP
END main