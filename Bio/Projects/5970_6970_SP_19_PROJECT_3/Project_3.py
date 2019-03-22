# This import is for reading multiple files in a folder
import os
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

#Calculates the mu
#Returns list of mu for the given Y where each element represents the 100 attributes (x)
#aList - 2D list that holds the each protein of the given Secondary Structure in the columns and its attributes in the rows 
#attr - Number of proteins that have the given Secondary Stucture
def calcMu(aList, attr):
    temp = [0 for i in range(100)]
    for i in range(len(aList)):
        for j in range(100):
            temp[j] += aList[i][j]
            if i == len(aList) - 1:
                temp[j] /= attr
    return temp

# Calculates the Variance
# Returns list of variance for the given Y where each element represents the 100 attributes (x)
# aList - 2D list that holds the each protein of the given Secondary Structure in the columns and its attributes in the rows 
# attr - Number of proteins that have the given Secondary Stucture
# mu -  list of mu for the given Y where each element represents the 100 attributes (x)
def calcVar(aList, attr, mu):
    temp = [0 for i in range(100)]
    for i in range(len(aList)):
        for j in range(100):
            temp[j] += ((aList[i][j] - mu[j])**2) 
            if i == len(aList) - 1:
                temp[j] /= attr
    return temp


# Calculates the P(x|Y) for each x and the find the product
# aList - list that holds the attributes of a protein
# mu -  list of mu for the given Y where each element represents the 100 attributes (x)
# var - list of variance for the given Y where each element represents the 100 attributes (x)
def calcP(aList, mu, var):
    temp = 1
    for i in range(len(aList)):
        hold = (math.exp(-((aList[i] - mu[i]) ** 2 ) / (2 * var[i])) / math.sqrt(2 * math.pi * var[i])) 
        temp *= hold
    return temp

# Gets their fasta and ss file paths
fastaPath = input("Enter the fasta path: ")
ssPath = input("Enter the ss path: ")
pssmPath = input("Enter the pssm path: ")

fastaFiles = []
script_dir = os.path.dirname(os.path.realpath('__file__')).replace('\\','/')

for i in os.listdir(script_dir + '/' + fastaPath):
    if i.endswith('.fasta'):
        fastaFiles.append(i)

ssFiles = []
for i in os.listdir(script_dir + '/' + ssPath):
    if i.endswith('.ss'):
        ssFiles.append(i)

pssmFiles = []
for i in os.listdir(script_dir + '/' + pssmPath):
    if i.endswith('.pssm'):
        pssmFiles.append(i)

if len(fastaFiles) != len(ssFiles) or len(fastaFiles) != len(pssmFiles):
    print("The number of .fasta files, .ss files and .pssm files do not match")
    quit()

numberOfTrainingData = int(.75 * len(fastaFiles))
numberOfTestingData = len(fastaFiles) - numberOfTrainingData

trainingFastaFiles = []
trainingSsFiles = []
trainingPssmFiles = []
testingFastaFiles = []
testingSsFiles = []
testingPssmFiles = []
count = 0
while count < numberOfTrainingData:
    trainingFastaFiles.append(fastaFiles[count])
    trainingSsFiles.append(ssFiles[count])
    trainingPssmFiles.append(pssmFiles[count])
    count += 1

testingFilesIndex = 0
while testingFilesIndex < numberOfTestingData:
    testingFastaFiles.append(fastaFiles[count])
    testingSsFiles.append(ssFiles[count])
    testingPssmFiles.append(pssmFiles[count])
    count += 1
    testingFilesIndex += 1


count = 0
total = 0
#Num of Helix, Strand, Coil
H = 0
E = 0
C = 0
# List to hold the info for the SS
Helix = []
Strand = []
Coil = []
#numberOfTrainingData = 1
while count < numberOfTrainingData:
    with open(fastaPath + "/" + trainingFastaFiles[count]) as fastaFile, open(ssPath + "/" + trainingSsFiles[count]) as ssFile, open(pssmPath + "/" + trainingPssmFiles[count]) as pssmFile:
        next(fastaFile)
        next(ssFile)
        
        # Needed to get to the first important line of the pssm file
        next(pssmFile)
        next(pssmFile)
        next(pssmFile)
        
        fastaLine = next(fastaFile).rstrip('\n')
        ssLine = next(ssFile).rstrip('\n')
        line = []
        for i in range(len(fastaLine)):
            pssmLine = next(pssmFile).split()
            line.append(list(map(int, pssmLine[2:22])))
        for i in range(len(fastaLine)):
            total += 1
            if ssLine[i] == 'H':
                H += 1
                Helix.append(getAttributes(i, len(fastaLine), line))
            
            elif ssLine[i] == 'E':
                E += 1
                Strand.append(getAttributes(i, len(fastaLine), line))
            
            elif ssLine[i] == 'C':
                C += 1
                Coil.append(getAttributes(i, len(fastaLine), line))
            
            else:
                #Error checking
                print("Invalid character")
                quit()
    count += 1
# P(H)
pH = H / total
#P(E)  
pE = E / total
#P(C)  
pC = C / total

# mu
muH = calcMu(Helix, H)
muE = calcMu(Strand, E)
muC = calcMu(Coil, C)
#print(muH)
# Var
varH = calcVar(Helix, H, muH)
varE = calcVar(Strand, E, muE)
varC = calcVar(Coil, C, muC)


f = open("training.txt", "w")
f.write('Helix\n')
f.write(' '.join(str(e) for e in muH))
f.write('\n')
f.write(' '.join(str(e) for e in varH))
f.write('\n' + str(pH))
   
f.write('\nStrand\n')
f.write(' '.join(str(e) for e in muE))
f.write('\n')
f.write(' '.join(str(e) for e in varE))

f.write('\n' + str(pE))
    
f.write('\nCoil\n')
f.write(' '.join(str(e) for e in muC))
f.write('\n')
f.write(' '.join(str(e) for e in varC))
f.write('\n' + str(pC))


count = 0
total = 0
correct = 0
while count < numberOfTestingData:
    with open(fastaPath + "/" + testingFastaFiles[count]) as fastaFile, open(ssPath + "/" + testingSsFiles[count]) as ssFile, open(pssmPath + "/" + testingPssmFiles[count]) as pssmFile:
        next(fastaFile)
        next(ssFile)
        
        # Needed to get to the first important line of the pssm file
        next(pssmFile)
        next(pssmFile)
        next(pssmFile)
        
        fastaLine = next(fastaFile).rstrip('\n')
        ssLine = next(ssFile).rstrip('\n')
        line = []
        for i in range(len(fastaLine)):
            pssmLine = next(pssmFile).split()
            line.append(list(map(int, pssmLine[2:22])))
        totalH = 0 
        for i in range(len(fastaLine)):
            testing = getAttributes(i, len(fastaLine), line)
            total += 1 
            perH = pH * calcP(testing, muH, varH)
            perE = pE * calcP(testing, muE, varE)
            perC = pC * calcP(testing, muC, varC)
            
            maxPer = max(perH,perE,perC) 
            if perH == maxPer:
                if 'H' == ssLine[i]:
                    correct += 1

            elif perE == maxPer:
                if 'E' == ssLine[i]:
                    correct += 1
            else:
                if 'C' == ssLine[i]:
                    correct += 1

    count += 1

print(correct/total)

#if __name__ == "__main__": main()