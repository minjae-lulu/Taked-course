#final_2
import re

a = input("Enter all street numbers of their house: ")
tmp= a
b = re.findall("\d+",tmp)
k = len(b)
n = []

for c in range (0,k):
    d = int(b[c])
    n.append(d)

max_num = max(n)

x = []
for i in range (0,max_num+1):
    e = 0
    for j in range(0,k):
        z = abs(i - n[j])
        e = e+ z
    x.append(e)

ans = min(x)
print('The shortest total distance:',ans)

