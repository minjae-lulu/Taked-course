# midterm 6

def quadratic(a, b, c):

    d = b**2 -4*a*c
    if d > 0:
        x1 = (-b + d**0.5)/(2*a)
        x2 = (-b - d**0.5)/(2*a)
        print(x1,'and',x2)

    elif d == 0:
        x = -b/(2*a)
        print(x)

    else:
        print("No root")

a = float(input("Enter a: "))
b = float(input("Enter b: "))
c = float(input("Enter c: "))
quadratic(a, b, c)