import random as rn

def rollDice():
     return rn.randrange(1 , 4)
    
class Node():
    name = ""
    north = None 
    south = None
    east = None
    west = None
    hidden = None

    def __init__(self, nname):
        self.name = nname

    def __str__(self):
        return self.name

    def setNorth(self, node):
        if node is not '*':
            self.north = node 
    
    def setEast(self, node):
        if node is not '*':
            self.east = node
    
    def setSouth(self, node):
       if node is not '*':
            self.south = node

    def setWest(self, node):
        if node is not '*':
            self.west = node
    
    def setHidden(self, node):
        if node is not '*':
            self.hidden = node

def find_node(node_in, node_list):
    for i in node_list:
        if node_in == i.name:
            return i

def move_range(curr_pos):
    mvmnt = []
    if(curr_pos.north is not None): 
        mvmnt.append("NORTH")
    if(curr_pos.east is not None): 
        mvmnt.append("EAST")
    if(curr_pos.south is not None): 
        mvmnt.append("SOUTH")
    if(curr_pos.west is not None): 
        mvmnt.append("WEST")
        
    return mvmnt

node_info = [] 
nodes = []
file_name = 'maze.txt'
#reads in the text file that contains the map
with open(file_name, 'r') as f:
    num_nodes = int(f.readline())
    start = f.readline().strip()
    end = f.readline().strip()
#stores the nodes
    for i in range(num_nodes):  
        node_info.append((f.readline().strip().split(' ')))
        n = Node(str(node_info[i][0]))
        nodes.append(n)
#links the nodes together
    for i in range(len(nodes)):
        nodes[i].setNorth(find_node(node_info[i][1], nodes))
        nodes[i].setEast(find_node(node_info[i][2], nodes))
        nodes[i].setSouth(find_node(node_info[i][3], nodes))
        nodes[i].setWest(find_node(node_info[i][4], nodes))
        nodes[i].setHidden(find_node(node_info[i][5], nodes))
        
    f.close()

start = find_node(start, nodes)
end = find_node(end, nodes)

class Player():
    plname  = " "
    pos = None
    def __init__(self, p_name):
        self.plname = p_name
        self.pos = start

    def __str__(self):
        return self.plname       

    def set_pos(self, Position):
        self.pos = Position 

game_over = False
players = 0
while players < 2:
    players =  int(input("How many players? "))
    if players < 2:
        print("Error must have at least 2 players!!")
p_array = []

for i in range(players):
    nme = input("Enter your name Player "+ str(i+1)+ ": ")
    p_array.append(Player(nme))
       
while not game_over:
    for i in range(players):
        if game_over:
            break
        
        print("It is " + str(p_array[i]) + "'s turn!")
        input("Press Enter to roll the dice.")
        turn = rollDice()
        
        
        while turn > 0:
            print("You have "+str(turn)+ " move(s) this turn")
            #print(p_array[i].pos)
            #print(p_array[i].pos.east)
                    
            move = []
            move = move_range(p_array[i].pos)
            steps = " "
            for j in range(len(move)):
                if j != len(move)-1:
                    steps += str(move[j])+ " or "
                else:
                    steps += str(move[j])+ "."
            print("You are at " + str(p_array[i].pos) + ". You can move " + str(steps.strip()))

            dire = " "
            while dire not in move:
                dire = input("Where will you move? ")
                dire = dire.upper()
                if dire not in move:
                    print("Error enter a valid direction")
                elif dire == "NORTH":
                    p_array[i].set_pos(p_array[i].pos.north)   
                elif dire == "EAST":
                    p_array[i].set_pos(p_array[i].pos.east)  
                elif dire == "SOUTH":
                    p_array[i].set_pos(p_array[i].pos.south)  
                elif dire == "WEST":
                    p_array[i].set_pos(p_array[i].pos.west) 
                
            turn -= 1
            if turn == 0 and p_array[i].pos.hidden != None:
                print("A mystical light surronds you and transports you to a distant land")
                p_array[i].set_pos(p_array[i].pos.hidden)
            if p_array[i].pos.name == end.name: 
                winner = str(p_array[i]) + " is the winner!! Congrats!!"
                game_over = True  
                break      
    
        if i == players-1 :
            i = 0  
print(winner)        