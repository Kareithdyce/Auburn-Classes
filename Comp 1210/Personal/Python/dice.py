import random
ans = ""
while ans.lower() != "n":
    i = random.randrange(1 , 7)
    print("Your number is " + str(i))
    ans = input("Keep going?(Y/N): ")
    
