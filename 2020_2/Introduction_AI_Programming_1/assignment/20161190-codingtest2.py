start = int(input("Enter the start number: "))
end = int(input("Enter the end number: "))

L = []
for i in range (start,end+1):
    for j in range (start,end+1):
        p = i*j
        if str(p) == str(p)[::-1]:
            L.append(p)

L = list(set(L))
L.sort()

n = len(L)
if n == 0:
    print("No palindrome!")

else:
    for z in L:
        print(z)
