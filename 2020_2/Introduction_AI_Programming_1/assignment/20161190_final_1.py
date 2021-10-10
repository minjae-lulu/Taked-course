#final_01

num1 = int(input("Enter the 1st number: "))
num2 = int(input("Enter the 2nd number: "))

n = num2
k = 0
print(num2, end=' ')
while True:
    if n == num1:
        k = k + 1
        print(end='\n')
        print("Length =",k)
        break

    else:
        k = k + 1
        if n%2 == 1:
            n = 5*n+3
            print(n, end=' ')
        else:
            n = n//2
            print(n, end=' ')

    if k > 99:
        print(end='\n')
        print("Too many length! Stop")
        break

