Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
Oranges     SWORD 10 DUP(?) 
size_O	DWORD type Oranges
prompt  	BYTE "Please input a value: ", 0
spacing 	BYTE ", ",0; 
Minimum 	BYTE "The minimum value of inputs is: ", 0  
locate BYTE " and is located at index: ",0
Maximum	BYTE "The maximum value of inputs is: ",0
MinimumN 	BYTE "Next to minimum ", 0
MaximumN  BYTE "Next to maximum ", 0

indexNMin WORD ? 
indexNMax WORD ? 
indexMin WORD ? 
indexMax WORD ? 
counter WORD 0
nMin  SWORD 7fffh
nMax  SWORD 8000h
min	 SWORD 7fffh
max	 SWORD 8000h

.code
main proc
mov ecx, LENGTHOF Oranges
mov ebx,0
L1:
	mov edx, offset prompt
	call WriteString
	call ReadInt
	mov Oranges[ebx], ax
	mov ax, min
	cmp Oranges[ebx], ax
	jge next
	mov ax, Oranges[ebx]
	mov min, ax
	mov ax, counter
	mov indexMin, ax
	next:
		mov ax, max
		cmp Oranges[ebx], ax
		jle other
		mov ax, Oranges[ebx]
		mov max, ax
		mov ax, counter
		mov indexMax, ax
	
	other:
		add ebx, size_O
		inc counter
		loop L1	
mov ecx, LENGTHOF Oranges
mov ebx, 0
mov counter, 0
L2:
	mov ax, Oranges[ebx]
	cmp ax, min
	je next2
	mov ax, nMin
	cmp ax, Oranges[ebx]
	jle next2
	mov ax, Oranges[ebx]
	mov nMin, ax
	mov ax, counter
	mov indexNMin, ax
	next2:
		mov ax, Oranges[ebx]
		cmp ax, max
		je other2
		mov ax, nMax
		cmp ax, Oranges[ebx]
		jge other2
		mov ax, Oranges[ebx]
		mov nMax, ax
		mov ax, counter
		mov indexNMax, ax
	other2:	
		inc counter	
		add ebx, size_O
		loop L2
mov edx, offset Minimum
call WriteString
movsx eax, min
call WriteInt
mov edx, offset locate
Call WriteString
movsx eax, indexMin
call WriteInt
call crlf

mov edx, offset Maximum
call WriteString
movsx eax, max
call WriteInt
mov edx, offset locate
Call WriteString
movsx eax, indexMax
call WriteInt
call crlf

mov edx, offset MinimumN
call WriteString
movsx eax, nMin
call WriteInt
mov edx, offset locate
Call WriteString
movsx eax, indexNMin
call WriteInt
call crlf

mov edx, offset MaximumN
call WriteString
movsx eax, nMax
call WriteInt
mov edx, offset locate
Call WriteString
movsx eax, indexNMax
call WriteInt
invoke ExitProcess,0
main endp
end main