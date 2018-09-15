; AddTwo.asm - adds two 32-bit integers.
; Chapter 3 example

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
myBytes	BYTE	11h, 22h, 33h, 44h
myWords	WORD	1234h, 5678h, 0ABCDh, 0EF01h, 2345h
myDoubles	DWORD	0AB23h, 0BC34h, 0CD54h, 8967h, 6F6Ah
myPointer	DWORD	myDoubles




.code
main proc
mov esi, OFFSET myBytes					
mov ax, WORD PTR [esi+2]		; A.	AX = 4433h		
mov eax, DWORD PTR myWords		; B.	EAX = 56781234h 		
mov esi, myPointer
mov eax, 0
mov ax, WORD PTR [esi+8]		; C.	AX = CD54h

; mov ax, WORD PTR [esi+1]		; D.	AX = 00ABh 		
;mov ax, WORD PTR [esi-6]		; E.	AX = ABCDh		

mov eax, 0
mov ax, TYPE  myDoubles


invoke ExitProcess,0
main endp
end main