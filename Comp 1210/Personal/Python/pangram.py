pangram = input("Enter a sentence: ")
pangram.lower()
alpha = "abcedfghijklmonpqrstuvwxyz"
count = 0
for i in alpha:
    if i in pangram:
        count+=1
print("This is a pangram!") if count == 26 else print("Not a pangram")   
#print(count)