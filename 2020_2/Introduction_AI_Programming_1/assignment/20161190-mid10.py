#mid 10

k = int(input("Enter the limit value: "))
c = 0
for ni in range(2,k+1):
    t = 0
    if ni % 2 == 0:
        n = ni * 3 + 1

        while True:
            if n == ni:
                c = c+1
                break

            elif t > 1000:
                break

            elif n % 2 == 0:
                n = n/2
                t = t+1
                continue
            elif n % 2 == 1:
                n = n* 3+1
                t = t+1
                continue
    else:
        pass
print(c)