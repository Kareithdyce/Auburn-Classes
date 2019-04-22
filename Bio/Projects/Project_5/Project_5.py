import os  # This import is for reading multiple files in a folder
import math
import Project_2
import predict


# This method prompts the user for the folder names of .fasta, .pssm, and TM Align files.
#
# Input --> None
#
# Output --> a 3D list of where in output[0][0], output[0][1], and output[0][2] are the fasta, pssm,
#            and TM Align folder names, respectively. In addition, output[1][0], output[1][1], and output[1][2] are
#            the .fasta, .pssm, and TM Align files, respectively.
def read_Files():
    print("The following folders need to be in the same directory as the script: ")
    fastaPath = input("Enter the fasta folder's name: ")
    pssmPath = input("Enter the pssm folder's name: ")
    tmalignPath = input("Enter the TM Align folder's name: ")

    # This is the section where the variables for the .fasta, .pssm, and TM Align files are initialized and will be
    # saved to.
    fastaFiles = []
    pssmFiles = []
    tmAlignFiles = []

    scriptDir = os.path.dirname(os.path.realpath('__file__'))   # Gets the file path of this Python script

    # Gets all the .fasta files with the folder name saved in the 'fastaPath' variable
    for i in os.listdir(scriptDir + '/' + fastaPath):
        if i.endswith('.fasta'):
            fastaFiles.append(i)

    # Gets all the .pssm files with the folder name saved in the 'pssmPath' variable
    for i in os.listdir(scriptDir + '/' + pssmPath):
        if i.endswith('.pssm'):
            pssmFiles.append(i)

    # Gets all the TM Align files with the folder name saved in the 'tmalignPath' variable
    for i in os.listdir(scriptDir + '/' + tmalignPath):
        tmAlignFiles.append(i)

    # Checks if the number of .fasta files is equal to the number of .pssm files
    if len(fastaFiles) != len(pssmFiles):
        print("The number of .fasta files doesn't math the number of .pssm files.")
        quit()  # Terminates the script

    # Checks if the number of TM Align files matches the number of .pssm and .fasta files
    if len(fastaFiles) * (len(fastaFiles) - 1) != len(tmAlignFiles):
        print("The number of TM Align files doesn't match with the number of .fasta and .pssm files.")
        quit()  # Terminates the script

    output = [[fastaPath, pssmPath, tmalignPath],
              [fastaFiles, pssmFiles, tmAlignFiles]]

    return output


# This method gets all the unique pairs of .fasta, .pssm, and TM Align files and
# curates them between training and testing data.
#
# Input --> files: this parameter is a list of all the .fasta, .pssm, and TM Align files are saved to
#           portionOfTraining: this parameter is the portion of our unique pairs that will go to our training data
#
# Output --> a 3D list where in output[0][] and output[1][] are training and testing data, respectively.
#            In addition, output[][0], output[][1], and output[][2] are unique pairs for .fasta, .pssm, and
#            TM Align files, respectively.
def get_Training_and_Testing_Data(files, portionOfTraining):

    # Initializing lists to put in unique pairs for our fasta, pssm, and TM Align files
    uniquePairsFasta = []
    uniquePairsPssm = []
    uniquePairsTMAlign = []

    # This code block finds all the unique pairs of our fasta and pssm files
    for i in range(len(files[1][0])):
        for j in range(i + 1, len(files[1][0])):
            uniquePair = [files[1][0][i], files[1][0][j]]
            uniquePairsFasta.append(uniquePair)

            uniquePair = [files[1][1][i], files[1][1][j]]
            uniquePairsPssm.append(uniquePair)

    skip = 0  # This is how many files we need to skip to avoid duplicates when going through the TM Align folder
    newFirstProtein = len(files[1][1]) - 1
    counter = 0
    changeFirstProteinCounter = 0
    while counter < len(files[1][2]):
        uniquePairsTMAlign.append(files[1][2][counter])
        counter += 1
        changeFirstProteinCounter += 1

        # This is if the 1st protein in the TM Align folder changes
        if changeFirstProteinCounter == newFirstProtein:
            skip += 1
            changeFirstProteinCounter = 0
            newFirstProtein -= 1
            counter += skip

    numberOfTrainingData = int(portionOfTraining * len(uniquePairsFasta))

    trainingUniquePairsFasta = []
    trainingUniquePairsPssm = []
    trainingUniquePairsTMAlign = []

    testingUniquePairsFasta = []
    testingUniquePairsPssm = []
    testingUniquePairsTMAlign = []

    # Curates the unique pairs to training data
    for i in range(numberOfTrainingData):
        trainingUniquePairsFasta.append(uniquePairsFasta[i])
        trainingUniquePairsPssm.append(uniquePairsPssm[i])
        trainingUniquePairsTMAlign.append(uniquePairsTMAlign[i])

    # Curates the unique pairs to testing data
    for i in range(numberOfTrainingData, len(uniquePairsFasta)):
        testingUniquePairsFasta.append(uniquePairsFasta[i])
        testingUniquePairsPssm.append(uniquePairsPssm[i])
        testingUniquePairsTMAlign.append(uniquePairsTMAlign[i])

    output = [[trainingUniquePairsFasta, trainingUniquePairsPssm, trainingUniquePairsTMAlign],
              [testingUniquePairsFasta, testingUniquePairsPssm, testingUniquePairsTMAlign]]

    return output


# This method goes through all the unique pairs the script collected in the TM Align folder between training and testing
# data and get the TM score averages of those pairs
#
# Input --> folderPath: this parameter is the folder name of the TM Align folder
#           trainingData: this parameter is a list of all the TM Align files for our training data
#           testingData: this parameter is a list of all the TM Align files for our testing data
#
# Output --> a 2D list where output[0][] and output[1][] are the TM Align averages of training and
#            testing, respectively.
def get_TM_Averages(folderPath, trainingData, testingData):

    # These are the variables where the TM Align scores will be stored in (training and testing)
    trainingTMAverages = []
    testingTMAverages = []

    # Goes through the TM Align files for the training data
    for i in range(len(trainingData)):
        with open(folderPath + '/' + trainingData[i]) as TMAlignFile:
            while True:
                line = TMAlignFile.readline()  # This while loop will keep scanning through the file, line by line

                # This looks for the line in the TM Align file where the scores appear
                if line.startswith('TM-score='):
                    TMAlignLine1 = line
                    TMAlignLine2 = TMAlignFile.readline()
                    break
            TMAlignScore1 = TMAlignLine1.split(" ")
            TMAlignScore2 = TMAlignLine2.split(" ")

            # Calculates the average of the 2 TM Align scores
            TMScoreAverage = (float(TMAlignScore1[1]) + float(TMAlignScore2[1])) / 2

            trainingTMAverages.append(TMScoreAverage)
    TMAlignFile.close()

    # Goes through the TM Align files for the testing data
    for i in range(len(testingData)):
        with open(folderPath + '/' + testingData[i]) as TMAlignFile:
            while True:
                line = TMAlignFile.readline()  # This while loop will keep scanning through the file, line by line

                # This looks for the line in the TM Align file where the scores appear
                if line.startswith('TM-score='):
                    TMAlignLine1 = line
                    TMAlignLine2 = TMAlignFile.readline()
                    break
            TMAlignScore1 = TMAlignLine1.split(" ")
            TMAlignScore2 = TMAlignLine2.split(" ")

            # Calculates the average of the 2 TM Align scores
            TMScoreAverage = (float(TMAlignScore1[1]) + float(TMAlignScore2[1])) / 2

            testingTMAverages.append(TMScoreAverage)
    TMAlignFile.close()

    output = [trainingTMAverages, testingTMAverages]

    return output


# This method, for each unique protein pair, gets the averages of the 20 pssm
# "weighted observed percentages rounded down", and scales them down to be between 0 and 1 (i.e. divide by 100)
# from the .pssm file.
#
# Input --> folderPath: this parameter is the folder name of the Pssm folder
#           trainingData: this parameter is a list of all the .pssm files for our training data
#           testingData: this parameter is a list of all the .pssm files for our testing data
#
# Output --> a 3D list where in output[0] and output[1] are the training and testing data for our 20 pssm attributes
#            averages. Within the traning and testing data (i.e. output[0][n] or output[1][n] with n being a random
#            index), it has a length 2. Inside these indices (i.e. output[0][0][0] and output[0][0][1]) are the 20
#            averaged pssm values for each unique pairs.
def get_Average_Pssm_Weights(folderPath, trainingData, testingData):

    # These  are the variables where a list of the pssm values will be stored
    trainingPssmList = []
    testingPssmList = []

    # These are used to separate the unique pairs of proteins with training and testing
    trainingOutput = []
    testingOutput = []

    temp = []

    # This section of code is for getting the pssm weight averages for the training data
    for i in range(len(trainingData)):

        # This code block is to get the 20 pssm averages for the 1st protein of the protein pair in training data
        with open(folderPath + '/' + trainingData[i][0]) as PssmFile:
            PssmFile.readline()
            PssmFile.readline()
            PssmFile.readline()

            while True:
                line = PssmFile.readline()
                if line != "\n":
                    pssmLine = line.split()

                    # Gets the "weighted observed percentages rounded down" values
                    trainingPssmList.append(list(map(int, pssmLine[22:42])))
                else:
                    break

            pssmSum = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

            # Loops through all the rows with the 20 pssm attributes
            for j in range(len(trainingPssmList)):
                for k in range(len(trainingPssmList[j])):
                    pssmSum[k] += trainingPssmList[j][k]  # Gets the sum of each column of the pssm attributes

            for j in range(len(pssmSum)):
                pssmSum[j] /= len(trainingPssmList)  # Gets the averages of each pssm value
                pssmSum[j] /= 100  # scale the pssm averages so they will be between [0,1)

            temp.append(pssmSum)

        PssmFile.close()
        trainingPssmList.clear()

        # This code block is to get the 20 pssm averages for the 2nd protein of the protein pair in training data
        with open(folderPath + '/' + trainingData[i][1]) as PssmFile:
            PssmFile.readline()
            PssmFile.readline()
            PssmFile.readline()

            while True:
                line = PssmFile.readline()
                if line != "\n":
                    pssmLine = line.split()

                    # Gets the "weighted observed percentages rounded down" values
                    trainingPssmList.append(list(map(int, pssmLine[22:42])))
                else:
                    break

            pssmSum = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

            for j in range(len(trainingPssmList)):
                for k in range(len(trainingPssmList[j])):
                    pssmSum[k] += trainingPssmList[j][k]  # Gets the sum of each column of the pssm attributes

            for j in range(len(pssmSum)):
                pssmSum[j] /= len(trainingPssmList)  # Gets the averages of each pssm value
                pssmSum[j] /= 100  # scale the pssm averages so they will be between [0,1)

            temp.append(pssmSum)

        trainingOutput.append(temp)
        PssmFile.close()
        trainingPssmList.clear()
        temp = []

    # This section of code is for getting the pssm weight averages for the testing data
    for i in range(len(testingData)):

        # This code block is to get the 20 pssm averages for the 1st protein of the protein pair in testing data
        with open(folderPath + '/' + testingData[i][0]) as PssmFile:
            PssmFile.readline()
            PssmFile.readline()
            PssmFile.readline()

            while True:
                line = PssmFile.readline()
                if line != "\n":
                    pssmLine = line.split()

                    # Gets the "weighted observed percentages rounded down" values
                    testingPssmList.append(list(map(int, pssmLine[22:42])))
                else:
                    break

            pssmSum = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

            for j in range(len(testingPssmList)):
                for k in range(len(testingPssmList[j])):
                    pssmSum[k] += testingPssmList[j][k]  # Gets the sum of each column of the pssm attributes

            for j in range(len(pssmSum)):
                pssmSum[j] /= len(testingPssmList)  # Gets the averages of each pssm value
                pssmSum[j] /= 100  # scale the pssm averages so they will be between [0,1)

            temp.append(pssmSum)

        PssmFile.close()
        testingPssmList.clear()

        # This code block is to get the 20 pssm averages for the 2nd protein of the protein pair in testing data
        with open(folderPath + '/' + testingData[i][1]) as PssmFile:
            PssmFile.readline()
            PssmFile.readline()
            PssmFile.readline()
            while True:
                line = PssmFile.readline()
                if line != "\n":
                    pssmLine = line.split()

                    # Gets the "weighted observed percentages rounded down" values
                    testingPssmList.append(list(map(int, pssmLine[22:42])))
                else:
                    break

            pssmSum = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

            for j in range(len(testingPssmList)):
                for k in range(len(testingPssmList[j])):
                    pssmSum[k] += testingPssmList[j][k]  # Gets the sum of each column of the pssm attributes

            for j in range(len(pssmSum)):
                pssmSum[j] /= len(testingPssmList)  # Gets the averages of each pssm value
                pssmSum[j] /= 100  # scale the pssm averages so they will be between [0,1)

            temp.append(pssmSum)

        testingOutput.append(temp)
        PssmFile.close()
        testingPssmList.clear()
        temp = []

    output = [trainingOutput, testingOutput]

    return output


def get_SS(folderPath, trainingData, testingData):
    trainingSS = predict.main(folderPath, trainingData)
    testingSS = predict.main(folderPath, testingData)
    output = [trainingSS, testingSS]
    return output


def get_SA(folderPath, trainingData, testingData):
    saPath = input("Enter the sa folder's name: ")
    testingFiles = Project_2.main(folderPath, trainingData, testingData, saPath)
    def get_Feature_Matrix(numberOfData, allfiles, fastaPath, saPath):
        amino_acids = {
            'A': "0100001100",
            'C': "0100001000",
            'D': "0010111000",
            'E': "0010110000",
            'F': "0100000010",
            'G': "0100001100",
            'H': "0011010010",
            'I': "0100000001",
            'K': "0011010000",
            'L': "0100000001",
            'M': "0100000000",
            'N': "0010001000",
            'P': "1100001000",
            'Q': "0010000000",
            'R': "0011010000",
            'S': "0010001100",
            'T': "0110001000",
            'V': "0100001001",
            'W': "0100000010",
            'Y': "0110000010"
        }
        featureMatrix = []
        count = 0
        while count < numberOfData:
            with open(fastaPath + "/" + allfiles[0][count]) as fastaFile, open(
                    saPath + "/" + allfiles[1][count]) as saFile:
                fastaFile.readline()
                saFile.readline()
                fastaLine = fastaFile.readline()
                fastaLine = fastaLine.rstrip('\n')
                saLine = saFile.readline()
                saLine = saLine.rstrip('\n')

                index = 0
                while index < len(saLine):
                    aminoLetter = fastaLine[index]
                    a = saLine[index]
                    if a == 'B':
                        a = '0'
                    else:
                        a = '1'
                    featureMatrix.append(aminoLetter + amino_acids.get(aminoLetter) + a)
                    index += 1
            count += 1

            fastaFile.close()
            saFile.close()
    output = get_Feature_Matrix(len(trainingData), testingFiles, folderPath, saPath)
    output = Project_2.get_Output(trainingData, testingData)

    return output


def calcWeightVector(wi, features, predictions, columns, counter):
    w0 = 0.01  # changeable
    n = 0.00001  # changeable
    WiXiSum = 0

    # Calculating sum of Xi rows
    for k in range(len(features[counter])):
            WiXiSum += float(wi[k]) * float(features[counter][k])

    for j in range(1, 25):
        wi[j] = (float(wi[j - 1]) + (2 * n * float(columns[j - 1])) * (float(predictions[int(counter/2)]) - WiXiSum))

    return wi

########################################################################################################################
########################################################################################################################
########################################################################################################################

#### This is where the "main" method begins ####
portion_Of_Training = 0.75

files = read_Files()

data = get_Training_and_Testing_Data(files, portion_Of_Training)

print("\nPlease wait 1 minute...\n")

TMAlignAverages = get_TM_Averages(files[0][2], data[0][2], data[1][2])

pssmAverages = get_Average_Pssm_Weights(files[0][1], data[0][1], data[1][1])

saValues = get_SA(files[0][0], data[0][0], data[1][0])

print("\nPlease wait again for about 25 to 30 minutes to calculate more data...\n")

ssValue = get_SS(files[0][1], data[0][1], data[1][1])

features2 = []
for i in range(len(TMAlignAverages[0])):
    features = []
    for j in range(25):
        if j < 20:
            features.append(pssmAverages[0][i][0][j])
        elif j > 19 and j < 22:
            features.append(saValues[0][i][j - 20])
        elif j >21:
            features.append(ssValue[0][i][0][j - 22])
    features2.append(features)
    features = []
    for j in range(25):
        if j < 20:
            features.append(pssmAverages[0][i][1][j])
        elif j > 19 and j < 22:
            features.append(saValues[0][i][j - 20])
        elif j > 21:
            features.append(ssValue[0][i][1][j - 22])
    features2.append(features)

sumAttributes = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
for i in range(len(pssmAverages[0])):
    for j in range(20):
        sumAttributes[j] += pssmAverages[0][i][0][j]
        sumAttributes[j] += pssmAverages[0][i][1][j]

for i in range(len(saValues[0])):
    for j in range(20, 22):
        sumAttributes[j] += float(saValues[0][i][j - 20])
        sumAttributes[j] += float(saValues[0][i][j - 20])

for i in range(len(ssValue[0])):
    for j in range(22, 25):
        sumAttributes[j] += float(ssValue[0][i][0][j - 22])
        sumAttributes[j] += float(ssValue[0][i][1][j - 22])

for i in range(len(sumAttributes)):
    sumAttributes[i] /= 8381

w0 = 0.01
weights = []
for i in range(25):
    weights.append(w0)

for i in range(len(features2)):
    weights = calcWeightVector(weights, features2, TMAlignAverages[0], sumAttributes, i)




## This is where testing happens ##
features2 = []
for i in range(len(TMAlignAverages[1])):
    features = []
    for j in range(25):
        if j < 20:
            features.append(pssmAverages[1][i][0][j])
        elif j > 19 and j < 22:
            features.append(saValues[1][i][j - 20])
        elif j >21:
            features.append(ssValue[1][i][0][j - 22])
    features2.append(features)
    features = []
    for j in range(25):
        if j < 20:
            features.append(pssmAverages[1][i][1][j])
        elif j > 19 and j < 22:
            features.append(saValues[1][i][j - 20])
        elif j > 21:
            features.append(ssValue[1][i][1][j - 22])
    features2.append(features)

result = 0
for i in range(len(TMAlignAverages[1])):
    wixiSum = 0
    for j in range(25):
        wixiSum += w0 + (float(weights[j]) * float(features2[i][j]))
    result += math.pow(TMAlignAverages[1][i] - wixiSum, 2)

meanAccuracy = result / len(TMAlignAverages[1])

print ("stop")



print("lol")