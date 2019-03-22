import sys
import os.path
import math

if len(sys.argv) < 3:
    print('Error 2 input files required')
    quit()
if not sys.argv[1].endswith('.fasta') or not sys.argv[2].endswith('.pssm'):
    print('Error incorrect input')
    quit()
if not os.path.isfile('training.txt'):
    print("Error please run Project_3.py before running this file")
    quit()


# Gets the attributes for the for the given protein
# Returns the list of attributes
# index - location of the protein in the sequence
# length - the numbers of proteins in the sequence
# pssm - the 2D list for the pssm
def getAttributes(index, length, pssm):
    nullSet = [-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]
    temp = []
    for j in range(index - 2, index + 3):
        if j < 0 or j >= length:
            temp.extend(nullSet)
        else:
            temp.extend(pssm[j])
    return temp


# Calculates the P(x|Y) for each x and the find the product
# aList - list that holds the attributes of a protein
# mu -  list of mu for the given Y where each element represents the 100 attributes (x)
# var - list of variance for the given Y where each element represents the 100 attributes (x)
def calcP(aList, mu, var):
    temp = 1
    su = 0
    for i in range(len(aList)):
        temp  *= (math.exp(-((aList[i] - mu[i]) ** 2 ) / (2 * var[i])) / math.sqrt(2 * math.pi * var[i])) 
    return temp

output = ""
with open("training.txt") as data, open(sys.argv[1]) as fasta, open(sys.argv[2]) as pssm:
    next(fasta)
    next(data)
    next(pssm)
    next(pssm)
    next(pssm)
    


    muH = list(map(float, next(data).split()))
    varH = list(map(float, next(data).split()))
    pH = float(next(data).rstrip('\n'))
    
    next(data)
    muE = list(map(float, next(data).split()))
    varE = list(map(float, next(data).split()))
    pE = float(next(data).rstrip('\n'))
    
    next(data)
    muC = list(map(float, next(data).split()))
    varC = list(map(float, next(data).split()))
    pC = float(next(data).rstrip('\n'))
    line = []
    
    fLine = next(fasta).rstrip('\n')
    for i in range(len(fLine)):
        pLine = next(pssm).split()
        line.append(list(map(int, pLine[2:22])))
    
    for i in range(len(fLine)):
        testing = getAttributes(i, len(fLine), line)
        perH = pH * calcP(testing, muH, varH)
        perE = pE * calcP(testing, muE, varE)
        perC = pC * calcP(testing, muC, varC)

        maxPer = max(perH,perE,perC) 
        if perH == maxPer:
            output += 'H'
        elif perE == maxPer:
            output += 'E'
        else:
            output += 'C'    

    print("Predicted Output: " + output)
    if(len(sys.argv) >= 4):
        with open(sys.argv[3]) as ss:
            next(ss)
            sLine = next(ss).rstrip('\n')
            print("Actual Sequence: " + sLine)
            if len(sLine) != len(output):
                print("Error")
                quit()
            correct = 0
            for i in range(len(sLine)):
                if sLine[i] == output[i]:
                    correct += 1
            print(correct / len(sLine))