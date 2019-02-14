arr = []
#arr.append([])
num = 0
for i in range(5):
    arr.append([])
        
    for j in range(5):
        arr[i].append(num)    
        num+=1
#for i in range(len(arr)):
#    print(arr[i])

print(arr[3][3])
