Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
MyArray SWORD 10, 20, 30, 40 ,50, 60, 70, 80, 90, 100
MyArray_size DWORD lengthof MyArray 
Swap SWORD 10 DUP(?)
size_array DWORD type MyArray
counter SWORD 0
back DWORD ?
first BYTE "The original array is ",0
second BYTE "The swapped array is ",0
spacing BYTE " ",0

.code
main proc
mov eax, size_array
shl eax, 1
mov back, eax
mov ebx, 0
mov ecx, MyArray_size
L1:
	mov ax, MyArray[ebx]
	mov Swap[ebx], ax
	cmp counter, 3
	
	jne next
		mov ax, Swap[ebx]
		mov edx, ebx
		sub ebx, back
		mov Swap[ebx], ax
		mov ax, MyArray[ebx]
		mov Swap[edx], ax
		mov bx, dx
		mov counter, -1

	next:
		add ebx,size_array
		inc counter
		loop L1
mov ecx, MyArray_size
mov ebx,0 
mov edx, offset first
Call WriteString
L2:
	movsx eax, MyArray[ebx]
	Call writeInt
	add ebx, size_array  
	mov edx, offset spacing
	Call WriteString
	loop L2
Call crlf
mov ecx, MyArray_size
mov ebx, 0
mov edx, offset second
Call WriteString
L3:
	movsx eax, Swap[ebx]
	Call writeInt
	add ebx, size_array  
	mov edx, offset spacing
	Call WriteString
	loop L3

	invoke ExitProcess,0
main endp
end main