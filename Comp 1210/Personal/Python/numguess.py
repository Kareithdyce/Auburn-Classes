import random
num = random.randrange(100)
guess = -1
while guess != num:
    guess = input("Guess a random number between 0-100: ")
    if int(guess) > num:
        print("You guess is too high")
    if int(guess) < num: 
        print("You guess is too low")    
    if int(guess) == num:
        break
print("You got it!!")