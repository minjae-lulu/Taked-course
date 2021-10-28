#final_4

num1 = int(input("Enter the first number: "))
num2 = int(input("Enter the second number: "))

count = 0
c8 = False
n1 = str(num1); n2 = str(num2)

for i in range(1, len(n1)+1):
    if (int(n1[-i]) + int(n2[-i]) > 9) or (int(n1[-i]) + int(n2[-i]) > 8 and c8 == True):
        count = count + 1
        c8 = True
    else:
        c8 = False

if count == 0:
    print("No carry operation")
else:
    print("# of Carry:",count)

