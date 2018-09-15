Include Irvine32.inc
.386
.stack 4096
ExitProcess proto,dwExitCode:dword
.data

.code
main proc
mov eax, 10
mov ebx, eax
shl ebx, 4
add eax, ebx
	invoke ExitProcess,0
main endp
end main