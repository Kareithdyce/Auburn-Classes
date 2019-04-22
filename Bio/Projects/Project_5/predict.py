import sys
import math

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
    for i in range(len(aList)):
        temp *= (math.exp(-((aList[i] - mu[i]) ** 2 ) / (2 * var[i])) / math.sqrt(2 * math.pi * var[i]))
    return temp

def main(pssmPath, data):
    pairs = []
    subpair = []

    for x in range(len(data)):
        output = ""
        with open("training.txt") as data1, open(pssmPath + '/' + data[x][0]) as pssm:
            next(data1)
            next(pssm)
            next(pssm)
            next(pssm)

            muH = list(map(float, next(data1).split()))
            varH = list(map(float, next(data1).split()))
            pH = float(next(data1).rstrip('\n'))

            next(data1)
            muE = list(map(float, next(data1).split()))
            varE = list(map(float, next(data1).split()))
            pE = float(next(data1).rstrip('\n'))

            next(data1)
            muC = list(map(float, next(data1).split()))
            varC = list(map(float, next(data1).split()))
            pC = float(next(data1).rstrip('\n'))
            line = []

            pLineSize = 0
            pLine = next(pssm).split()
            while len(pLine) > 0:
                line.append(list(map(int, pLine[2:22])))
                pLine = next(pssm).split()
                pLineSize += 1

            portionH = 0
            portionE = 0
            portionC = 0
            for i in range(pLineSize):
                testing = getAttributes(i, pLineSize, line)
                perH = pH * calcP(testing, muH, varH)
                perE = pE * calcP(testing, muE, varE)
                perC = pC * calcP(testing, muC, varC)

                maxPer = max(perH, perE, perC)
                if perH == maxPer:
                    output += 'H'
                    portionH += 1
                elif perE == maxPer:
                    output += 'E'
                    portionE += 1
                else:
                    output += 'C'
                    portionC += 1
            result1 = ['{0:.4f}'.format(portionH / len(output)), '{0:.4f}'.format(portionE / len(output)),
                       '{0:.4f}'.format(portionC / len(output))]
        data1.close()
        pssm.close()

        output = ""
        with open("training.txt") as data1, open(pssmPath + '/' + data[x][1]) as pssm:
            next(data1)
            next(pssm)
            next(pssm)
            next(pssm)
            muH = list(map(float, next(data1).split()))
            varH = list(map(float, next(data1).split()))
            pH = float(next(data1).rstrip('\n'))
            next(data1)
            muE = list(map(float, next(data1).split()))
            varE = list(map(float, next(data1).split()))
            pE = float(next(data1).rstrip('\n'))
            next(data1)
            muC = list(map(float, next(data1).split()))
            varC = list(map(float, next(data1).split()))
            pC = float(next(data1).rstrip('\n'))
            line = []
            pLineSize = 0
            pLine = next(pssm).split()
            while len(pLine) > 0:
                line.append(list(map(int, pLine[2:22])))
                pLine = next(pssm).split()
                pLineSize += 1
            portionH = 0
            portionE = 0
            portionC = 0
            for i in range(pLineSize):
                testing = getAttributes(i, pLineSize, line)
                perH = pH * calcP(testing, muH, varH)
                perE = pE * calcP(testing, muE, varE)
                perC = pC * calcP(testing, muC, varC)
                maxPer = max(perH, perE, perC)
                if perH == maxPer:
                    output += 'H'
                    portionH += 1
                elif perE == maxPer:
                    output += 'E'
                    portionE += 1
                else:
                    output += 'C'
                    portionC += 1
            result2 = ['{0:.4f}'.format(portionH / len(output)), '{0:.4f}'.format(portionE / len(output)),
                       '{0:.4f}'.format(portionC / len(output))]
        subpair.append(result1)
        subpair.append(result2)
        pairs.append(subpair)
        subpair = []
    return pairs
