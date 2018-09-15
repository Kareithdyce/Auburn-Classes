Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	Xw Byte "X is  ",0
	Yw Byte "Y is  ",0
	S1 Byte "Sum1 is ",0
	S2 BYTE "Sum2 is ",0
	X SDWORD -7h
	Y SDWORD -3h
.code
main proc
mov eax, X
mov edx, offset Xw
Call writeString
call WriteInt
Call Crlf
mov eax, Y
mov edx, offset Yw
Call writeString
call WriteInt
Call Crlf

;Way 1
mov eax, 0
mov edx, 0
mov eax, X
mov ebx, X
imul ebx
push eax
push edx
mov eax, 0
mov edx, 0
mov eax, Y
mov ebx, Y
imul ebx
pop ecx ;edx1
pop ebx; eax1
add eax, ebx
adc edx, 0
add edx,ecx
push eax;
push edx;
mov eax, X
mov ebx, Y
mov ecx, 2
imul ebx
imul ecx
pop ecx
pop ebx
add eax, ebx
mov edx, offset s1
call WriteString
Call WriteInt
Call Crlf


;Way 2
mov eax, 0
mov edx, 0
mov eax, X
add eax, Y
mov ebx, eax
imul ebx
mov edx, offset S2
Call WriteString
call WriteInt
	invoke ExitProcess,0
main endp
end main