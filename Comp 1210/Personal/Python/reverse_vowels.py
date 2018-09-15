#def reverse(rev):
rev = input("Enter a sentence: ")
rev = list(rev)
left = 0
right = len(rev)-1
vowels = "aeiouAEIOU"
while(left < right):
    if(not rev[left] in vowels):
        left+=1
    elif(not rev[right] in vowels):
        right-=1
    else:
        temp = rev[right]
        rev[right] = rev[left]
        rev[left] = temp
        right -= 1
        left += 1
rev = "".join(rev)
print(rev)