def main():
    sequences = get_sequences()
    #sequences = ["TAGTCACG", "AGACTGTC"]
    blosum = blosum_table()

    print("")
    table = nw_create_table(sequences);
    alignment = nw_backtrack(table, sequences, blosum)

    # Printing Global score and alignment
    print("Needleman Wunsch:")
    print("Score:", alignment[0])

    count = 0
    while count < len(alignment[1]):
        if len(alignment[1]) - count > 80:
            print(alignment[1][count:count + 80])
            print(alignment[2][count:count + 80])
            print(alignment[3][count:count + 80])
            print("")
        else:
            print(alignment[1][count:count + len(alignment[1]) - count])
            print(alignment[2][count:count + len(alignment[1]) - count])
            print(alignment[3][count:count + len(alignment[1]) - count])
        count += 80

    table = sw_create_table(sequences)
    alignment = sw_backtrack(table, sequences, blosum)

    # Printing Local score and alignment
    print("")
    print("Smith Waterman:")
    print("Score:", alignment[0])
    count = 0
    while count < len(alignment[1]):
        if len(alignment[1]) - count > 80:
            print(alignment[1][count:count + 80])
            print(alignment[2][count:count + 80])
            print(alignment[3][count:count + 80])
            print("")
        else:
            print (alignment[1][count:count + len(alignment[1]) - count])
            print (alignment[2][count:count + len(alignment[1]) - count])
            print (alignment[3][count:count + len(alignment[1]) - count])
        count += 80

# Scans two fasta files for alignment
def get_sequences():
    fasta1 = ""
    #fileName = "1k4rA_dengue_virus.fasta"
    fileName = "1dzl_HPV.fasta"
    #fileName = "2plv1_polio_virus.fasta"
    with open(fileName) as fp: # Change file name here
        for line in fp:
            if line[0] == ">":
                continue
            else:
                fasta1 += line

    fasta2 = ""
    #fileName2 = "5ire_zika_virus.fasta"    
    fileName2 = "3mge_HIV.fasta"
    #fileName2 = "4rhv1_rhino_virus.fasta"
    with open(fileName2) as fp: # Change file name here
        for line in fp:
            if line[0] == ">":
                continue
            else:
                fasta2 += line

    sequences = [fasta1.replace('\n',''), fasta2.replace('\n','')]
    return sequences

# Recreating blosum62 matrix
def blosum_table():
    value = ""
    with open('blosum62.txt') as fp:
        for line in fp:
            value += line

    value = value.split()
    row = "ARNDCQEGHILKMFPSTWYVBZX-"
    col = row
    iter = 0

    blosum_table = {}
    for i in range(0, len(row)):
        for j in range(0, len(col)):
            blosum_table[row[i]+col[j]] = int(value[iter])
            iter += 1
    return blosum_table


# Creates and fills out DP table NW
def nw_create_table(sequence):
    rows = len(sequence[0]) + 1
    cols = len(sequence[1]) + 1

    # Creating 2D list of zeros
    table = []
    for row in range(rows):
        table += [[0] * cols]

    # Base case
    for i in range(0, rows):
        table[i][0] = -i
    for i in range(0, cols):
        table[0][i] = -i

    # Filling out the table
    for i in range(1, rows):
        for j in range(1, cols):
            if sequence[0][i-1] != sequence[1][j-1]:
                new_value = max(table[i-1][j], table[i][j-1], table[i-1][j-1]) - 1
                table[i][j] = new_value
            else:
                table[i][j] = table[i-1][j-1] + 1
    return table

# Backtracking for optimal alignment NW
def nw_backtrack(table, sequence, blosum):
    rows = len(sequence[0])
    cols = len(sequence[1])

    score = 0
    s1 = ""
    connect = ""
    s2 = ""

    # Backtracking through optimal path
    while rows != 0 or cols != 0:
        if table[rows-1][cols-1] >= max(table[rows-1][cols], table[rows][cols-1]):
            s1 += sequence[0][rows - 1]
            s2 += sequence[1][cols - 1]
            score += blosum[sequence[0][rows-1] + sequence[1][cols-1]]
            rows -= 1
            cols -= 1
            if sequence[0][rows] == sequence[1][cols]:
                connect += "|"
            else:
                if blosum[sequence[0][rows] + sequence[1][cols]] > 0:
                    connect += "|"
                else:
                    connect += "*"
        elif cols == 0 or table[rows-1][cols] >= table[rows][cols-1]:
            s1 += sequence[0][rows-1]
            connect += " "
            s2 += "-"
            score += blosum[sequence[0][rows - 1] + "-"]
            rows -= 1
        else:
            s1 += "-"
            connect += " "
            s2 += sequence[1][cols-1]
            score += blosum["-" + sequence[1][cols - 1]]
            cols -= 1

    # Reversing the strings
    s1 = s1[::-1]
    connect = connect[::-1]
    s2 = s2[::-1]

    alignment = [score, s1, connect, s2]
    return alignment

# Creates and fills out DP table SW
def sw_create_table(sequence):
    rows = len(sequence[0]) + 1
    cols = len(sequence[1]) + 1

    # Creating 2D list of zeros
    table = []
    for row in range(rows):
        table += [[0] * cols]

    # Filling out the table
    for i in range(1, rows):
        for j in range(1, cols):
            if sequence[0][i - 1] != sequence[1][j - 1]:
                new_value = max(table[i - 1][j], table[i][j - 1], table[i - 1][j - 1]) - 1
                if new_value < 0:
                    new_value = 0
                table[i][j] = new_value
            else:
                table[i][j] = table[i - 1][j - 1] + 2
    return table

# Backtracking for optimal alignment SW
def sw_backtrack(table, sequence, blosum):
    rows = 0
    cols = 0

    # Getting index of max value in table
    largest_val = max(map(max, table))
    for i in range(0, len(sequence[0]) + 1):
        for j in range(0, len(sequence[1]) + 1):
            if table[i][j] != largest_val:
                continue
            else:
                rows = i
                cols = j
                break

    score = 0
    s1 = ""
    connect = ""
    s2 = ""

    # Backtracking through optimal path
    while table[rows][cols] != 0:
        if table[rows - 1][cols - 1] >= max(table[rows - 1][cols], table[rows][cols - 1]) or \
                table[rows - 1][cols - 1] == 0 or sequence[0][rows - 1] == sequence[1][cols - 1]:
            s1 += sequence[0][rows - 1]
            s2 += sequence[1][cols - 1]
            score += blosum[sequence[0][rows-1] + sequence[1][cols-1]]
            rows -= 1
            cols -= 1
            if sequence[0][rows] == sequence[1][cols]:
                connect += "|"
            else:
                if blosum[sequence[0][rows] + sequence[1][cols]] > 0:
                    connect += "|"
                else:
                    connect += "*"
        elif cols == 0 or table[rows - 1][cols] >= table[rows][cols - 1]:
            s1 += sequence[0][rows - 1]
            connect += " "
            s2 += "-"
            score += blosum[sequence[0][rows - 1] + "-"]
            rows -= 1
        else:
            s1 += "-"
            connect += " "
            s2 += sequence[1][cols - 1]
            score += blosum["-" + sequence[1][cols - 1]]
            cols -= 1

    # Reversing the strings
    s1 = s1[::-1]
    connect = connect[::-1]
    s2 = s2[::-1]

    alignment = [score, s1, connect, s2]
    return alignment

if __name__ == "__main__":
    main()