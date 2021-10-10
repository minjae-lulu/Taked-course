import math

def add (A,B):
    print('%.2f' % float(A+B))

def sub(A, B):
    print('%.2f' % float(A - B))

def mul(A, B):
    print('%.2f' % float(A * B))

def div(A, B):
    if B == 0:
        print('%.2f' % float(B))
    else:
        print('%.2f' % float(A/B))

def pow(A,B):
    print('%.2f' % float(A**B))

def sqrt(A):
    print('%.2f' % float(A**0.5))


def sin(A):
    A = math.radians(A)
    print('%.2f' % float(math.sin(A)))

def cos(A):
    A = math.radians(A)
    print('%.2f' % float(math.cos(A)))

def tan(A):
    A = math.radians(A)
    print('%.2f' % float(math.tan(A)))
