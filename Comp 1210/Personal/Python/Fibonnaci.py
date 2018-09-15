i0 = 1
i1 = 1
n = int(input("Which number in the fibonnaci sequence? "))
if n < 3:
    print(0) if n == 0 else print(1)
else:
    for i in range(2,n):
        temp = i0 + i1
        i0 = i1
        i1 = temp
    print(i1)
    
