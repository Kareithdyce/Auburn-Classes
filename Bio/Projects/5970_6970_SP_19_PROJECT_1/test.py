import math as m
n = 100
num = 0
for i in range(n+1):
    pos1 = m.factorial(i+n)/(m.factorial(n) * m.factorial(i))
    pos2 = m.factorial(n)/(m.factorial(n-i) * m.factorial(i))
    num+= (pos1* pos2)
print(num)