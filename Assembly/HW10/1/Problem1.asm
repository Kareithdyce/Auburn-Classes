Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword
	
.data
	CaseTable WORD 89	
	DWORD Process_A	
	EntrySize = ($ - CaseTable)
	WORD 79
	DWORD Process_B
	WORD 69	
	DWORD Process_C
	WORD 59
	DWORD Process_D
	WORD 0
	DWORD Process_F 
NumberOfEntries = ($ - CaseTable) / EntrySize
msgA BYTE "Your letter grade is A", 0
msgB BYTE "Your letter grade is B", 0
msgC BYTE "Your letter grade is C", 0
msgD BYTE "Your letter grade is D", 0
msgF BYTE "Your letter grade is F", 0

string1 Byte "Enter a your score(0-99) ", 0
string2 Byte "Your orginal score is ", 0
string3 Byte "Your final score is ", 0
string4 Byte "Your score invalid ", 0
num WORD ?

.code
Process_A PROC
	mov edx,OFFSET msgA
	ret
Process_A ENDP

Process_B PROC
	mov edx,OFFSET msgB
	ret
Process_B ENDP

Process_C PROC
	mov edx,OFFSET msgC
	ret
Process_C ENDP

Process_D PROC
	mov edx,OFFSET msgD
	ret
Process_D ENDP

Process_F PROC
	mov edx,OFFSET msgF
	ret
Process_F ENDP

main PROC
	mov edx, offset string1
	Call WriteString
	Call ReadInt
	mov num, ax
	inc eax
	cmp ax, 100
	ja L3
	mov ebx, OFFSET CaseTable 
	mov ecx, NumberOfEntries
	L1:
		cmp ax, [ebx] 
		jbe L2 ; 
		call NEAR PTR [ebx + 2]
		call WriteString
		call Crlf
		jmp L4 ; exit the search
	L2:
		add ebx,EntrySize 
		loop L1
	L3:
		mov edx, OFFSET string4
		call writestring
		call Crlf
	L4:
		mov edx, offset string3
		call Writestring
		call Writeint
		call Crlf
		
		mov edx, offset string2
		movzx eax, num
		call Writestring
		call Writeint
	invoke ExitProcess,0
main endp
end main