games = "tictactoerockpaperscissorsnumguess"
game = "@"
while not game in games:
    game = input("Which game would you like to play? ")
if game in "tictactoe":
    exec(open("tictactoe.py").read())
elif game in "rockpaperscissors":     
   exec(open("rockpaperscissors.py").read())
elif game in "numguess":     
   exec(open("numguess.py").read())
