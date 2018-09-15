Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword


.data
ID BYTE "The ID is ",0
revId BYTE "The reversed ID is ",0
sum BYTE "The sum is ",0
myAuburnID BYTE 09h,03h,70h,01h,62h
myAuburnIDRev BYTE 5 DUP(?)
result BYTE 5 DUP(?)
len DWORD LENGTHOF myAuburnID
carry BYTE 0

.code 

main proc

	mov edx, offset ID
	call writestring
	mov eax, 0
	mov ecx, len
	mov edx, 0
	mov ebx, 1

	L1:
	mov al, myAuburnId[edx]
	call writehexB
	inc edx 
	loop L1
	
	call crlf
	mov edx, offset revID
	call writestring
	
	mov ecx, len
	mov edx, 0
	
	L2:
	mov al, myAuburnId[ecx-1]
	rol al, 4
	mov myAuburnIdRev[edx], al
	inc edx 
	loop L2
	
	mov ecx, len
	mov edx, 0

	L3:
	mov al, myAuburnIdRev[edx]
	call writehexB
	inc edx 
	loop L3
	
	mov ecx, len
	mov edx, 0
	
	L4:
	mov al, myAuburnIdRev[ecx-1]
	add al, dl
	mov dl, 0 
	add al, myAuburnId[ecx-1]
	daa
	mov result[ecx-1], al
	jnc skip
	mov dl, 1
	skip: 
	loop L4
	
	
	call crlf
	mov edx, offset sum
	call writestring
	mov ecx, len
	mov edx, 0
	

	L5:
	mov al, result[edx]
	call writehexB
	inc edx 
	loop L5
	
	main ENDP
END main