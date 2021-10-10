# midterm 7

def gcd(a, b):
    if a>0 and b>0:
        while b != 0:
            t = b
            b = a % b
            a = t
        return a


    else:
        return "Not negative!"


a = int(input("Enter the first integer number: "))
b = int(input("Enter the second integer number: "))
print(gcd(a, b))