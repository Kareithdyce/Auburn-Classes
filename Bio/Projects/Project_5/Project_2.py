# This import is for reading multiple files in a folder
import os

def main(folderPath, trainingData, testingData, saPath):
    # Gets their fasta and sa file paths
    fastaPath = folderPath

    files = read_files(fastaPath, saPath)

    numberOfTrainingData = int(.75 * len(files[0]))
    numberOfTestingData = len(files[0]) - numberOfTrainingData



    # This gets the training and testing files and they're placed in their respective index
    allfiles = get_data(files, numberOfTrainingData, numberOfTestingData, trainingData, testingData)
    trainingFiles = [allfiles[0],allfiles[1]]
    testingFiles = [allfiles[2],allfiles[3]]

    trainingFeatureMatrix = get_Feature_Matrix(numberOfTrainingData, trainingFiles, fastaPath, saPath)
    #testingFeatureMatrix = get_Feature_Matrix(len(trainingData) + len(testingData), testingFiles, fastaPath, saPath)


    # This is the beginning of finding the attribute with the highest gain
    highestGain = -999999999999999999999
    highestGainAttribute = ''
    availableAttributesOut = {
        1: "Proline",
        2: "Hydrophobic",
        3: "Polar",
        4: "Positive",
        5: "Negative",
        6: "Charged",
        7: "Small",
        8: "Tiny",
        9: "Aromatic",
        10: "Aliphatic"
    }
    numberOfData = 0
    yesAttribute = 0
    noAttribute = 0
    numberOfExposedYes = 0
    numberOfBuriedYes = 0
    numberOfExposedNo = 0
    numberOfBuriedNo = 0
    colNumber = -1
    tableForNo = []
    tableForYes = []

    totalBuried = 0
    totalExposed = 0
    totalBuriedtemp = 0
    totalExposedtemp = 0

    for col in range(1, 10):
        tempTableYes = []
        tempTableNo = []
        for row in range(len(trainingFeatureMatrix)):
            numberOfData += 1
            if trainingFeatureMatrix[row][col] == '1':
                yesAttribute += 1
                if trainingFeatureMatrix[row][11] == '1':
                    numberOfExposedYes += 1
                    totalExposed += 1
                elif trainingFeatureMatrix[row][11] == '0':
                    numberOfBuriedYes += 1
                    totalBuried += 1
                tempTableYes.append(trainingFeatureMatrix[row])

            elif trainingFeatureMatrix[row][col] == '0':
                noAttribute += 1
                if trainingFeatureMatrix[row][11] == '1':
                    numberOfExposedNo += 1
                    totalExposed += 1
                elif trainingFeatureMatrix[row][11] == '0':
                    numberOfBuriedNo += 1
                    totalBuried += 1
                tempTableNo.append(trainingFeatureMatrix[row])
        import math

        yesExposedRatio = float(numberOfExposedYes)/yesAttribute
        yesBuriedRatio = float(numberOfBuriedYes)/yesAttribute
        entropyYes = ((-yesExposedRatio * math.log(yesExposedRatio, 2)) - (yesBuriedRatio * (math.log(yesBuriedRatio, 2))))

        noExposedRatio = float(numberOfExposedNo)/noAttribute
        noBuriedRatio = float(numberOfBuriedNo)/noAttribute
        entropyNo = ((-noExposedRatio * math.log(noExposedRatio, 2)) - (noBuriedRatio * math.log(noBuriedRatio, 2)))
        entropyPreSplit = ((float(-totalExposed)/numberOfData) * math.log(float(totalExposed)/numberOfData, 2)) -\
                          ((float(totalBuried) / numberOfData) * math.log(float(totalBuried)/numberOfData, 2))
        gain = entropyPreSplit - ((float(yesAttribute)/numberOfData) * entropyYes) - ((float(noAttribute)/numberOfData)
                                                                                      * entropyNo)
        if gain > highestGain:
            highestGain = gain
            colNumber = col
            highestGainAttribute = availableAttributesOut[colNumber]
            tableForNo = tempTableNo
            tableForYes = tempTableYes
            totalBuriedtemp = totalBuried
            totalExposedtemp = totalExposed

        numberOfData = 0
        yesAttribute = 0
        noAttribute = 0
        numberOfExposedYes = 0
        numberOfBuriedYes = 0
        numberOfExposedNo = 0
        numberOfBuriedNo = 0
        totalBuried = 0
        totalExposed = 0



    # The attribute with the highest gain out of the 10 will be named as "root"
    del availableAttributesOut[colNumber]
    root = Attributes(highestGainAttribute)
    root.availableAttributes = availableAttributesOut
    root.total = len(tableForYes) + len(tableForNo)
    root.numberOfYes = len(tableForYes)
    root.numberOfNo = len(tableForNo)
    root.yesTable = tableForYes
    root.noTable = tableForNo
    root.totalBuried = totalBuriedtemp
    root.totalExposed = totalExposedtemp

    root.yesChild = build_tree(root, root.yesTable, root.availableAttributes)
    root.noChild = build_tree(root, root.noTable, root.availableAttributes)

    trueneg = 0
    truepos = 0
    falsepos = 0
    falseneg = 0


    # Uses the test data and walks it through the learned ID3 Decision Tree
    # to predict the labels
    # for x in range(len(testingFeatureMatrix)):
    #     predicted = get_results(testingFeatureMatrix[x], root)
    #     if testingFeatureMatrix[x][11] == '1' and predicted == '1':
    #         truepos += 1
    #     elif testingFeatureMatrix[x][11] == '0' and predicted == '1':
    #         falsepos += 1
    #     elif testingFeatureMatrix[x][11] == '0' and predicted == '0':
    #         trueneg += 1
    #     elif testingFeatureMatrix[x][11] == '1' and predicted == '0':
    #         falseneg += 1
    # precision = float(truepos)/(truepos + falsepos)
    # recall = float(truepos)/(truepos + falseneg)
    # accuracy = (truepos + trueneg) / float(truepos + trueneg + falsepos + falseneg)
    # f1Score = 2*((precision * recall) / float(precision + recall))
    # mcc = float((truepos * trueneg) - (falsepos * falseneg))/ math.sqrt((truepos + falsepos) * (truepos + falseneg) *
    #                                                                (trueneg + falsepos) * (trueneg + falseneg))
    # print
    # print ("Number of true positives: ", truepos)
    # print ("Number of true negatives: ", trueneg)
    # print ("Number of false positive: ", falsepos)
    # print ("Number of false negatives: ", falseneg)
    # print
    # print ("Precision: ", round(precision, 5))
    # print ("Recall: ", round(recall, 5))
    # print ("Accuracy: ", round(accuracy, 5))
    # print ("F1 Score:", round(f1Score, 5))
    # print ("MCC:", round(mcc, 5))

    return testingFiles


# This is the attributes class for the 10 different attributes we have for the amino acids
class Attributes:
    def __init__(self, attribute, noChild = None, yesChild = None):
        self.attribute = attribute
        self.gain = 0
        self.entropy = 0
        self.total = 0
        self.numberOfYes = 0 #total number of "yes, this attribute is exposed: 1"
        self.numberOfNo = 0 #total number of "no, this attribute is buried: 0"
        self.noChild = noChild
        self.yesChild = yesChild
        self.availableAttributes = None
        self.yesTable = None
        self.noTable = None
        self.totalExposed = 0
        self.totalBuried = 0
        self.isLeaf = False
        self.value = ""

#This function takes in 2 folder names, and find the directories for those folders and open them
def read_files(fastaPath, saPath):
    fastaFiles = []
    script_dir = os.path.dirname(os.path.realpath('__file__'))
    for i in os.listdir(script_dir + '/' + fastaPath):
        if i.endswith('.fasta'):
            fastaFiles.append(i)

    saFiles = []
    for i in os.listdir(script_dir + '/' + saPath):
        if i.endswith('.sa'):
            saFiles.append(i)

    if len(fastaFiles) != len(saFiles):
        print("The number of .fasta files doesn't match the number of .sa files")
        quit()

    output = [fastaFiles, saFiles]
    return output


# This function builds the ID3 learned decision tree from the root node
def build_tree(node, table, availableAttributes):
    length = availableAttributes

    if (not length) or node.totalBuried == 0 or node.totalExposed == 0 or len(table) == 0:
        root = Attributes(node.attribute)
        root.isLeaf = True
        if node.totalBuried >= node.totalExposed:
            root.value = '0'
        else:
            root.value = '1'
        return root
    else:
        import math
        entropyPreSplit = ((float(-node.totalExposed)/node.total) * math.log(float(node.totalExposed)/node.total, 2)) -\
                          ((float(node.totalBuried) / node.total) * math.log(float(node.totalBuried)/node.total, 2))
        numberOfData = 0
        yesAttribute = 0
        noAttribute = 0

        colNumber = -1
        tableForNo = []
        tableForYes = []

        numberOfExposedYes = 0
        numberOfBuriedYes = 0
        numberOfExposedNo = 0
        numberOfBuriedNo = 0

        totalBuried = 0
        totalExposed = 0
        totalBuriedtemp = 0
        totalExposedtemp = 0

        keyIndex = length.keys()
        highestGain = -99999

        for col in keyIndex:
            tempTableYes = []
            tempTableNo = []
            for row in range(len(table)):
                numberOfData += 1
                if table[row][col] == '1':
                    yesAttribute += 1
                    if table[row][11] == '1':
                        numberOfExposedYes += 1
                        totalExposed += 1
                    elif table[row][11] == '0':
                        numberOfBuriedYes += 1
                        totalBuried += 1
                    tempTableYes.append(table[row])

                elif table[row][col] == '0':
                    noAttribute += 1
                    if table[row][11] == '1':
                        numberOfExposedNo += 1
                        totalExposed += 1
                    elif table[row][11] == '0':
                        numberOfBuriedNo += 1
                        totalBuried += 1
                    tempTableNo.append(table[row])
            import math
            entropyYes = 0
            if yesAttribute > 0:
                yesExposedRatio = float(numberOfExposedYes) / yesAttribute
                yesBuriedRatio = float(numberOfBuriedYes) / yesAttribute
                entropyYes = ((-yesExposedRatio * math.log(yesExposedRatio, 2)) - (
                            yesBuriedRatio * (math.log(yesBuriedRatio, 2))))

            entropyNo = 0
            if noAttribute > 0:
                noExposedRatio = float(numberOfExposedNo) / noAttribute
                noBuriedRatio = float(numberOfBuriedNo) / noAttribute
                entropyNo = ((-noExposedRatio * math.log(noExposedRatio, 2)) - (noBuriedRatio * math.log(noBuriedRatio, 2)))
                entropyPreSplit = ((float(-totalExposed) / numberOfData) * math.log(float(totalExposed) / numberOfData, 2)) -\
                                  ((float(totalBuried) / numberOfData) * math.log(float(totalBuried) / numberOfData, 2))

            gain = entropyPreSplit - ((float(yesAttribute) / numberOfData) * entropyYes) - (
                        (float(noAttribute) / numberOfData)
                        * entropyNo)
            if gain > highestGain:
                highestGain = gain
                colNumber = col
                highestGainAttribute = length[colNumber]
                tableForNo = tempTableNo
                tableForYes = tempTableYes
                totalBuriedtemp = totalBuried
                totalExposedtemp = totalExposed

            numberOfData = 0
            yesAttribute = 0
            noAttribute = 0
            numberOfExposedYes = 0
            numberOfBuriedYes = 0
            numberOfExposedNo = 0
            numberOfBuriedNo = 0
            totalBuried = 0
            totalExposed = 0


        my_dict = {}
        if colNumber != -1:
            for x in length.keys():
                if x != colNumber:
                    my_dict[x] = length[x]

        root = Attributes(highestGainAttribute)
        root.availableAttributes = my_dict
        root.total = len(tableForYes) + len(tableForNo)
        root.numberOfYes = len(tableForYes)
        root.numberOfNo = len(tableForNo)
        root.yesTable = tableForYes
        root.noTable = tableForNo

        root.totalExposed = totalExposedtemp
        root.totalBuried = totalBuriedtemp

        root.yesChild = build_tree(root, root.yesTable, root.availableAttributes)
        root.noChild = build_tree(root, root.noTable, root.availableAttributes)

        return root

def get_Output(trainingData, testingData):
    training = []
    x = []
    testing = []
    y = []
    import random
    for i in range(len(trainingData)):
        training.append('{0:.4f}'.format(random.uniform(0.45, 0.55)))
        training.append('{0:.4f}'.format(random.uniform(0.45, 0.55)))
        x.append(training)
        training = []
    for i in range(len(testingData)):
        testing.append('{0:.4f}'.format(random.uniform(0.45, 0.55)))
        testing.append('{0:.4f}'.format(random.uniform(0.45, 0.55)))
        y.append(testing)
        testing = []
    output = [x, y]
    return output


# This function separates the training data from the testing data
def get_data(files, numberOfTrainingData, numberOfTestingData, trainingDataFasta, testingDataFasta):
    trainingFastaFiles = []
    trainingSaFiles = []
    testingFastaFiles = []
    testingSaFiles = []
    count = 0
    while count < numberOfTrainingData:
        trainingFastaFiles.append(files[0][count])
        trainingSaFiles.append(files[1][count])
        count += 1

    testingFilesIndex = 0
    count = 0
    while testingFilesIndex < (len(trainingDataFasta) + len(testingDataFasta)):
        if testingFilesIndex < len(trainingDataFasta):
            testingFastaFiles.append(trainingDataFasta[testingFilesIndex][0])
            testingFastaFiles.append(trainingDataFasta[testingFilesIndex][1])

            a = trainingDataFasta[testingFilesIndex][0]
            a = str(a)
            a = a.replace(".fasta", ".sa")
            b = trainingDataFasta[testingFilesIndex][1]
            b = str(b)
            b = b.replace(".fasta", ".sa")
            testingSaFiles.append(a)
            testingSaFiles.append(b)
        else:
            testingFastaFiles.append(testingDataFasta[count][0])
            testingFastaFiles.append(testingDataFasta[count][1])

            a = testingDataFasta[count][0]
            a = str(a)
            a.replace(".fasta", ".sa")
            b = testingDataFasta[count][1]
            b = str(b)
            b.replace(".fasta", ".sa")
            testingSaFiles.append(a)
            testingSaFiles.append(b)
            count += 1

        testingFilesIndex += 1

    output = [trainingFastaFiles, trainingSaFiles, testingFastaFiles, testingSaFiles]
    return output


# This function walks through the decision tree and find what the leaf node's value will be
def get_results(x, node):
    attribute = node.attribute
    availableAttributesOut = {
        "Proline": 1,
        "Hydrophobic": 2,
        "Polar": 3,
        "Positive": 4,
        "Negative": 5,
        "Charged": 6,
        "Small": 7,
        "Tiny": 8,
        "Aromatic": 9,
        "Aliphatic": 10
    }

    col = availableAttributesOut[attribute]
    predicted = ""
    if node.isLeaf:
        return node.value
    elif x[col] == '0':
        predicted = get_results(x, node.noChild)
    else:
        predicted = get_results(x, node.yesChild)
    return predicted



# This function gets the feature matrix of both sa files and fasta files
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
        with open(fastaPath + "/" + allfiles[0][count]) as fastaFile, open(saPath + "/" + allfiles[1][count]) as saFile:
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
    return featureMatrix

if __name__ == "__main__": main()