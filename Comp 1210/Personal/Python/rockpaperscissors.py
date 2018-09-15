''' players = "3"
while players not in "12":
    players = input("One or player? ")
    if players not in "12":
        print("Error enter ")
 '''
import random
game_over = False
while not game_over:
    ans = " "
    cpu = ["rock", "paper", "scissors"] 
    hand = " "
    while hand not in cpu: 
        hand =  input("Choose Rock, Paper, or Scissors. ").lower()
        if hand not in cpu:
            print("Not a valid choice!! try again!!")
    i = random.randint(0 , 2)
    if cpu[i] == hand:
        print("Tie!! Try again!!")
        continue
    elif  (hand == "rock" and i == 2) or (hand == "paper" and i == 0) or (hand == "scissors" and i == 1):
        print("You win " + str(hand).upper() + " beats " + str(cpu[i]).upper())
    else:    
        print("You lose " + str(cpu[i]).upper() + " beats " + str(hand).upper())
    
    while ans.lower() not in "yesno": 
        ans = input("Play Again? ").lower()
    if ans in "no":
        game_over = True


