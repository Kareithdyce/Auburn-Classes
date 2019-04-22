import os
import math

def main():

    # Gets their pssm and rr file paths
    pssmPath = input("Enter the pssm path: ")
    rrPath = input("Enter the rr path: ")

    script_dir = os.path.dirname(os.path.realpath('__file__')).replace('\\', '/')

    rrFiles = []
    for i in os.listdir(script_dir + '/' + rrPath):
        if i.endswith('.rr'):
            rrFiles.append(i)

    pssmFiles = []
    for i in os.listdir(script_dir + '/' + pssmPath):
        if i.endswith('.pssm'):
            pssmFiles.append(i)

    if len(pssmFiles) != len(rrFiles):
        print("The number of .pssm files and .rr files do not match")
        quit()

    numberOfTrainingData = int(.75 * len(pssmFiles))
    numberOfTestingData = len(pssmFiles) - numberOfTrainingData

    trainingRrFiles = []
    trainingPssmFiles = []
    testingRrFiles = []
    testingPssmFiles = []

    count = 0
    while count < numberOfTrainingData:
        trainingPssmFiles.append(pssmFiles[count])
        trainingRrFiles.append(rrFiles[count])
        count += 1

    testingFilesIndex = 0
    while testingFilesIndex < numberOfTestingData:
        testingPssmFiles.append(pssmFiles[count])
        testingRrFiles.append(rrFiles[count])
        count += 1
        testingFilesIndex += 1

    count = 0
    total = 0

    # List to hold the info for the features
    features = []
    contact = []
    noContact = []
    predictions = []
    columns = []

    print("Loading... This may take a couple of minutes\n")

    # Parsing raw data for training
    while count < numberOfTrainingData:
        with open(rrPath + "/" + trainingRrFiles[count]) as rrFile, \
                open(pssmPath + "/" + trainingPssmFiles[count]) as pssmFile:

            # Needed to get to the first important line of the pssm file
            next(rrFile)
            next(pssmFile)
            next(pssmFile)
            next(pssmFile)

            pssmVals = []
            sequence = ""
            pairs = {}

            # Extracting residue and pssm values
            for nextLine in pssmFile:
                if nextLine != "\n":
                    pssmLine = nextLine.split()
                    pssmVals.append(list(map(int, pssmLine[2:22])))
                    sequence += pssmLine[1]
                else:
                    break

            # Extracting contact pairs
            for nextLine in rrFile:
                if nextLine != "\n":
                    rrLine = nextLine.split()
                    pairs[rrLine[0]+","+rrLine[1]] = float(rrLine[4])
                else:
                    break
            # Balancing data for feature generation
            for i in range(1, len(sequence)):
                for j in range(i + 1, len(sequence) + 1):
                    pair = str(i) + "," + str(j)
                    if abs(i - j) > 5:
                        if pair in pairs:
                            total += 1
                            features.append(getAttributes(i - 1, j - 1, len(sequence), pssmVals))
                            contact.append(getAttributes(i - 1, j - 1, len(sequence), pssmVals))
                            predictions.append(1)
                        elif len(noContact) - len(contact) <= 0:
                            total += 1
                            features.append(getAttributes(i - 1, j - 1, len(sequence), pssmVals))
                            noContact.append(getAttributes(i - 1, j - 1, len(sequence), pssmVals))
                            predictions.append(0)
        count += 1

    weights = []
    counter = 0

    # Calculating sum of Xi columns and multiplying each sum by n
    for i in range(200):
        sumOfCol = 0
        for pair in features:
            sumOfCol += pair[i]
        columns.append(sumOfCol)

    for i in range(201):
        weights.append(0.01)

    # Calculating the weight vectors
    for i in range(len(features)):
        weights = (calcWeightVector(weights, features, predictions, columns, counter))

    count = 0
    probability = {}
    L10correct = 0
    L5correct = 0
    L2correct = 0
    L10tot = 0
    L5tot = 0
    L2tot = 0
    accuracy = 0
    f = open("testing.txt", "w")

    # Calculating predictions for testing
    while count < numberOfTestingData:
        with open(rrPath + "/" + testingRrFiles[count]) as rrFile, \
                open(pssmPath + "/" + testingPssmFiles[count]) as pssmFile:

            # Needed to get to the first important line of the pssm file
            next(rrFile)
            next(pssmFile)
            next(pssmFile)
            next(pssmFile)

            pssmVals = []
            sequence = ""
            pairs = {}

            # Extracting residue and pssm values
            for nextLine in pssmFile:
                if nextLine != "\n":
                    pssmLine = nextLine.split()
                    pssmVals.append(list(map(int, pssmLine[2:22])))
                    sequence += pssmLine[1]
                else:
                    break

            for i in range(len(pssmVals)):
                for j in range(len(pssmVals[i])):
                    pssmVals[i][j] = sigmoid(pssmVals[i][j])

            # Extracting contact pairs
            for nextLine in rrFile:
                if nextLine != "\n":
                    rrLine = nextLine.split()
                    pairs[rrLine[0] + ", " + rrLine[1]] = float(rrLine[4])
                else:
                    break

            # Using the generated weights to classify and predict test data
            for i in range(1, len(sequence)):
                for j in range(i + 1, len(sequence) + 1):
                    pair = str(i) + ", " + str(j)
                    if abs(i - j) > 5:
                        attributes = getAttributes(i, j, len(sequence), pssmVals)
                        WiXiSum = 0
                        counter = 0
                        for i in range(1, len(attributes)):
                            WiXiSum += weights[i] * attributes[i]
                            counter += 1
                        cPrediction = 1 / (1 + math.exp(weights[0] + (WiXiSum)))
                        nCPrediction = 1 - cPrediction
                        if max(cPrediction, nCPrediction) == cPrediction:
                            probability[cPrediction] = pair

        sortedProbs = sorted(probability.keys(), reverse=True)

        # Calculating Accuracy
        for i in range(int(round(len(sequence) / 10))):
            predictedPair = probability.get(sortedProbs[i])
            if predictedPair in pairs:
                L10correct += 1
            L10tot += 1

        for i in range(int(round(len(sequence) / 5))):
            predictedPair = probability.get(sortedProbs[i])
            if predictedPair in pairs:
                L5correct += 1
            L5tot += 1

        for i in range(int(round(len(sequence) / 2))):
            predictedPair = probability.get(sortedProbs[i])
            if predictedPair in pairs:
                L2correct += 1
            L2tot += 1

        # Writing predicted pairs into a file in descending order
        f.write('-------------' + testingPssmFiles[count] + '-------------' + '\n')
        for values in sortedProbs:
            f.write('Pair: ' + probability.get(values) + ' Probability: ' + str(values) + '\n')
        count += 1
    print("L/10 Accuracy: " + str(round(L10correct / float(L10tot) * 100, 2)))
    print("L/5 Accuracy: " + str(round(L5correct / float(L5tot) * 100, 2)))
    print("L/2 Accuracy: " + str(round(L2correct / float(L2tot) * 100, 2)))


def getAttributes(i, j, length, pssm):
    nullSet = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1]
    temp = []
    for k in range(i - 2, i + 3):
        if k < 0 or k >= length:
            temp.extend(nullSet)
        else:
            temp.extend(pssm[k])
    for m in range(j - 2, j + 3):
        if m < 0 or m >= length:
            temp.extend(nullSet)
        else:
            temp.extend(pssm[m])
    for i in range(len(temp)):
        temp[i] = sigmoid(temp[i])
    return temp

def sigmoid(value):
    return math.exp(value) / (math.exp(value) + 1)

def calcWeightVector(wi, features, predictions, columns, counter):
    w0 = 0.01
    n = 0.00001
    WiXiSum = 0

    # Calculating sum of Xi rows
    for k in range(len(features[counter])):
            WiXiSum += wi[k] * features[counter][k]

    pHat = 1 / (1 + math.exp(w0 + (WiXiSum)))

    for i in range(0, 200):
        wi[i + 1] = (wi[i + 1] + (n * columns[i]) * (predictions[counter] - pHat)) - (0.8*wi[i+1])

    return wi

if __name__ == "__main__":
    main()