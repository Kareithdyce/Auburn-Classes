
INCLUDE Irvine32.inc
.386
;.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword
.data
str1 BYTE "Enter the number of odd integers to sum. ",0
Beta	DWORD 11223344h
Zeta	WORD 124h
sum DWORD 0

.code
main proc
	mov eax, Beta
	add eax, DWORD PTR Zeta
	;mov edx, offset str1
	;call WriteString
	;call ReadInt
	;mov ecx, eax
	;mov eax, -1
	
		;L1:	
			;add eax, 2
			;add sum, eax
			;loop L1
	;mov eax,sum 
	;call WriteInt
			

	invoke ExitProcess,0
	main endp
end main
