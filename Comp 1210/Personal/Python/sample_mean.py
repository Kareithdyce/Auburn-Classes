Numbers = [123, 87, 96, 24,104,16,85,55,62,109]
Total = 0
Count = 0
for Num in Numbers:
    Total += Num
    Count+=1
    Average = float(Total)/float(Count)
print("Average: ", Average)