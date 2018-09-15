
INCLUDE Irvine32.inc
.386
;.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword
.data
Array1    BYTE 7h, 23h, 0E2h, 0D4h, 0A6h, 42h, 13h, 8h, 0AFh,  0C3h
Array2 	BYTE 4h, 10h,  0Ah,  34h,  17h, 2Ah, 0Ah, 3h,  0Dh,  0Eh 
Array3 	BYTE 10 DUP (?)
str1 byte "The first array. ", 0
str2 byte "The second array. ", 0
str3 byte "The thrid array. ", 0
.code
main proc
mov ecx, lengthof Array1
mov edi, offset Array1
mov ebx, offset Array2
mov esi, offset Array3
mov eax, 0
	L1:
		mov al, [edi]
		mov edx, offset str1
		call WriteString
		call WriteInt
		call Crlf
		mov al, [ebx]
		mov edx, offset str2
		call WriteString
		call WriteInt
		call Crlf
		mov al, [edi]
		mov ah, [ebx]
		sub al, ah
		mov ah, 0
		mov [esi], al
		mov edx, offset str3
		call WriteString
		call WriteInt
		call Crlf
		call Crlf
		inc edi
		inc ebx
		inc esi
		loop L1

invoke ExitProcess,0
main endp
end main
