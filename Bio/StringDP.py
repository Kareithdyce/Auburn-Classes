def match(var1, var2):
    m  = [[0] * len(var1) for i in range(len(var2))]
    print(var1)
    num = 0
    for i in range(len(var1)):
        m[0][i] = num
        num-= 1
s1 = "TTC"
s2 = "ATT"
match(s1,s2)
