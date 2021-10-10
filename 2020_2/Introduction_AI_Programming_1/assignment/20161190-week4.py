
max_num = int(input("Enter the max num (1~max_num): "))
list1 = list(range(1,max_num+1))

print("The list is",list1)

left_shift = int(input("Enter the number of left-shift: "))

for i in range(0,left_shift):
    list1.append(list1[0])
    list1 = list1[1:]
    print(list1)