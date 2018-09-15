Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword


.data
A1		BYTE "Array1 is ",0
A2		BYTE "Array2 is ",0
A3		BYTE "Array3 is ",0
Apple	WORD 	1214h, 3423h, 6578h, 5699h, 2005h
Berry	WORD 	4125h, 2345h, 12BCh, 0CDF1h, 1009h
Cherry	WORD 	5 dup(0)

.code
Extended_Sub PROC
pushad
clc ; clear the Carry flag

L1: mov ax,[esi] ; get the first integer
sbb ax,[edi] ; add the second integer
pushfd ; save the Carry flag
mov [ebx],ax ; store partial sum
add esi,1 ; advance all three pointers
add edi,1
add ebx,1
popfd ; restore the Carry flag
loop L1 ; repeat the loop
mov byte ptr [ebx],0 ; clear high byte of sum
sbb byte ptr [ebx],0 ; add any leftover carry
popad
ret
Extended_Sub endp

Display_Dif PROC
pushad
; point to the last array element
add esi,ecx
sub esi,TYPE BYTE
mov ebx,TYPE BYTE
L1: mov ax,[esi] ; get an array byte
call WriteHexB ; display it
sub esi,TYPE BYTE ; point to previous byte
loop L1
popad
ret
Display_Dif ENDP


main PROC
mov edx, OFFSET A1
call writeString
mov esi, OFFSET Apple ; first operand
mov ecx, LENGTHOF Apple ; number of bytes
call Display_Dif

Call crlf

mov edx, OFFSET A2
call writeString
mov esi, OFFSET Berry ; first operand
mov ecx, LENGTHOF Berry ; number of bytes
call Display_Dif
Call crlf

mov esi, OFFSET Apple ; first operand
mov edi, OFFSET Berry; second operand
mov ebx, OFFSET Cherry; sum operand
call Extended_Sub
mov esi, OFFSET Cherry
mov ecx, LENGTHOF Cherry

mov edx, OFFSET A3
call writeString
call Display_Dif
call Crlf




	invoke ExitProcess,0
main endp
end main