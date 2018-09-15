Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword
	
.data
	array DWORD 10,11,12,13,14,15,16,17,18,19
	arraySize DWORD SIZE array
	temp DWORD ?
	lenArray DWORD LENGTHOF array 
	word1 BYTE "The first array is ", 0
	word2 BYTE "The second array is ", 0
	
.code
ArraySeries PROC
	push	ebp
	mov	ebp,esp
	mov ecx, [ebp+8]
	mov esi, [ebp+12]
	
	mov eax,  1
	mov ebx,  0
	mov temp, 0
	
	mov [esi], eax
	mov edx, 4

	L1:
	mov temp, eax
	add eax, ebx
	mov [esi+edx], eax
	;call writeInt
	add edx, 4
	mov ebx, temp
	loop L1

	
	pop ebp
	ret
ArraySeries endp 

main PROC
	mov edx, offset word1
	call writestring
	call crlf
	mov ecx, lenArray
	mov edx, 0
	
	L1:
	mov eax, array[edx]
	Call WriteInt
	add edx, 4
	loop L1

	mov eax, offset array
	mov ebx, lenArray
	call crlf
	push eax
	push ebx 
	call ArraySeries
	pop ebx
	pop eax
	


	call crlf
	
	mov edx, offset word2
	call writestring
	mov ecx, lenArray
	mov edx, 0
	

	mov ecx, lenArray 
	mov edx, 0
	mov eax, 0
	
	call crlf
	
	L2:
	mov eax, array[edx]
	Call WriteInt
	add edx, 4
	loop L2
	
	invoke ExitProcess,0
main endp
end main