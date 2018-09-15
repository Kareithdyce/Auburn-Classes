win1 = 0
win2 = 0
draws = 0
turns = 0
def wins():
    print("Player 1 has won " + str(win1) + " time(s)")
    print("Player 2 has won " + str(win2) + " time(s)")
    print("The game has drawn " + str(draws) + " time(s)")
        
def draw_board(r, c, grid = None):
    row = " ---"
    col = "|"
    X = " X "
    O = " O "
    B = "   "

    fil = col + B
    if grid == None:
        for i in range(c):
            print(row * r)
            if i != c - 1:
                print((c-1) * fil + col)
    else :
        for i in range(4):
            print(row * r)
            if i < 3:
                line = ""
                for j in range(len(grid)):
                    if grid[i][j] == 1:
                        fil = col + X
                    elif grid[i][j] == 2:
                        fil = col + O
                    else:
                        fil = col + B
                    line += fil
                    
                    if j == len(grid)-1:
                        print(line + col)
        
def clear_O(grid): 
    for i in range(len(grid)):
        for j in range(len(grid)):
            if grid[i][j] == 2:
                grid[i][j] = 0

def clear_X(grid):
    for i in range(len(grid)):
        for j in range(len(grid)):
            if grid[i][j] == 1:
                grid[i][j] = 0

def winnerTicTac(grid):
    for i in range(3):
        if (0 != grid[0][0] == grid[1][1] == grid[2][2] or
            0 != grid[2][0] == grid[1][1] == grid[0][2] or
            0 != grid[i][0] == grid[i][1] == grid[i][2] or
            0 != grid[0][i] == grid[1][i] == grid[2][i]):
            return True
    else:
        return False
    
rows = 3#int(input("How many rows and columns do you want?: "))
columns = rows + 1 
ans = " "   

while ans not in "Nn" :
    draw_board(rows, columns)
    if rows == 3 :
        game = [[0,0,0],
                [0,0,0],
                [0,0,0]]
        while not winnerTicTac(game):
            valid_move = False
            while not valid_move:
                x = int(input("Player 1 (X) what is your move (row): "))-1
                y = int(input("Player 1 (X) what is your move (col): "))-1 
                if x >= 3 or y >= 3 or x < 0 or y < 0 :
                    print("Not a valid move make sure x and y are between 1 and 3!!")

                elif game[x][y] == 0:
                    game[x][y] = 1
                    draw_board(3,3,game)
                    turns += 1
                    #print(turns)
                    break
                else:
                    print("Error space is taken try again!!")
            if winnerTicTac(game):
                print("You Won Player 1!!")
                win1+=1
                clear_O(game)
                draw_board(3,3,game)
                break
            if turns == 9:
                draws += 1
                print("Tie game!! Restarting!! Your turn Player 2!!")
                clear_O(game)
                clear_X(game)
                draw_board(3,3,game)
                turns = 0

            while not valid_move:
                x = int(input("Player 2 (O) what is your move (row): "))-1
                y = int(input("Player 2 (O) what is your move (col): "))-1 
                if x >= 3 or y >= 3 or x < 0 or y < 0 :
                    print("Not a valid move make sure x and y are between 1 and 3!!")

                elif game[x][y] == 0:
                    game[x][y] = 2
                    draw_board(3,3,game)
                    turns+=1
                    break
                else:
                    print("Error space is taken try again!!")
            if winnerTicTac(game):
                print("You Won Player 2!!")
                win2+=1
                clear_X(game)
                draw_board(3,3,game)
                break
            
            if turns == 9:
                draws += 1
                print("Tie game!! Restarting!! Your turn Player 1!!")
                clear_O(game)
                clear_X(game)
                draw_board(3,3,game)
                turns = 0

    wins()            
    ans = input("Play Again? (Y/N): ") 
