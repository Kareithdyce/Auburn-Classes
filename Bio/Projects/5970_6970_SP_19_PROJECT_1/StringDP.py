class Pos:
    score = 0
    prev = None
    char1 = ''
    char2 = ''
    matched = ''
    
    #Contstructor
    def __init__(self, scoreIn, prevNode):
        self.score = scoreIn
        self.prev = prevNode
        
    #toString
    def __str__(self):
        return str(self.score)
    
    #lets toString work for strings
    def __repr__(self):
        return str(self)    
#handles diagonial shifts
def shiftD(value, letter1, letter2):
    if letter1 == letter2 :
        return value +1
    return value - 1
    

def match(var1, var2):
    m = []
    numH = -1
    numV = -1
    #Base Case
    for i in range(len(var1)+1):
        m.append([])

        for j in range(len(var2)+1):
            if(i == 0 and j == 0):
                m[i].append(Pos(0, None))
            elif(i == 0):
                m[i].append(Pos(numH, m[i][j-1]))
                m[i][j].char1 = '-'
                m[i][j].char2 = var2[j-1]               
                numH-= 1
            elif(j == 0):
                m[i].append(Pos(numV, m[i-1][j]))
                m[i][j].char1 = var1[i-1]
                m[i][j].char2 = '-'
                numV-= 1    
            else:
                m[i].append(Pos(0, None))
            m[i][j].matched = "*"
        
    #i - col
    #j - row
    #fills up the DP table
    for i in range(1, len(var1) + 1):
        for j in range(1, len(var2) + 1):
            score = shiftD(m[i-1][j-1].score, var1[i-1], var2[j-1])
            p = m[i-1][j-1]
            c1 = var1[i-1]
            c2 = var2[j-1]
            
            if(score < m[i][j-1].score - 1) :
                score = m[i][j-1].score - 1
                p = m[i][j-1]
                c1 = '-'
                c2 = var2[j-1]

            elif(score < m[i-1][j].score - 1):
                score = m[i-1][j].score - 1 
                p = m[i-1][j] 
                c1 = var1[i-1]
                c2 = '-'
   
    
            m[i][j] = Pos(score, p)
            m[i][j].char1 = c1
            m[i][j].char2 = c2
            
            if(c1 == c2):
                m[i][j].matched = "|"
            else:
                m[i][j].matched = "*"
    
    #just outputs the DP table
    for i in range(len(var1)+1):
        print(m[i])
        

    step = m[len(var1)][len(var2)]
    path = []
    str1 = ""
    str2 = ""
    str3 = ""
    print("Score: " +str(step))
    #This is for outputing the matches and stringss
    while(step.prev != None):
        str1 += step.char1  
        str2 += step.matched
        str3 += step.char2  
        step = step.prev

    #reverses the string
    path.append(str1[::-1])
    path.append(str2[::-1])
    path.append(str3[::-1])
    return path

            

s1 = "TAGTCACG"
s2 = "AGACTGTC"
l = match(s1,s2)
limit = 80
for i in range(0, len(l[0]), limit):
    for j in range(len(l)):
        print(l[j][i:i+limit])
    print("")
