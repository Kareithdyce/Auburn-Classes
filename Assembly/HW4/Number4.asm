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
var1 SBYTE	-1, -2, -3, -4
var2 WORD		1000h, 2000h, 3000h, 4000h
var3 SWORD	-21, -42
var4 DWORD	10A0h, 20B0h, 30C0h, 40D0h




.code
main proc
;mov esi, OFFSET myBytes					
;mov ax, WORD PTR [esi+2]		; A.	AX = 4433h		
;mov eax, DWORD PTR myWords		; B.	EAX = 56781234h 		
;mov esi, myPointer
;mov eax, 0
;mov ax, WORD PTR [esi+3]		; C.	AX = CD54h

;mov ax, WORD PTR [esi+1]		; D.	AX = 00ABh 		
;mov ax, WORD PTR [esi-6]		; E.	AX = ABCDh		

;mov ax, TYPE  myDoubles
mov eax, 0
mov ax, var2				; AX = 1000h 
mov ax, [var2 + 1]	
mov ax, [var2 + 2]
mov ax, [var2 + 3]
mov ax, [var2 + 4]			; AX = 3000h
mov ax, var3				; AX = -21
mov ax, [var3-1]			; AX = 4000h


invoke ExitProcess,0
main endp
end main