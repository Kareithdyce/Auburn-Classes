; AddTwo.asm - adds two 32-bit integers.
; Chapter 3 example

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
myBytes	BYTE	11h, 22h, 33h, 44h
myWords	WORD	1234h, 5678h, ABCDh, EF01h, 2345h
myDoubles	DWORD	AB23h, BC34h, CD54h, 8967h, 6F6Ah
myPointer	DWORD	myDoubles




.code
mov esi, OFFSET myBytes					
mov ax, WORD PTR [esi+2]		; A.	AX = 0033h		
mov eax, DWORD PTR myWords		; B.	EAX = 00001234h 		
mov esi, myPointer
mov ax, WORD PTR [esi+8]		; C.	AX =		
mov ax, WORD PTR [esi+1]		; D.	AX = 		
mov ax, WORD PTR [esi-6]		; E.	AX = 		


	invoke ExitProcess,0
main endp
end main