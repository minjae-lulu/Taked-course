def binary_search(a_list, item):

    first = 0
    last = len(a_list) - 1
    found = False
    while first <= last and not found:
        midpoint = (first + last) // 2
        if a_list[midpoint] == item:
            found = True
        else:
            if item < a_list[midpoint]:
                last = midpoint - 1
            else:
                first = midpoint + 1
    return found

def bubble_sort(a_list):
    for pass_num in range(len(a_list)-1, 0, -1):
        for i in range(pass_num):
            if a_list[i] > a_list[i+1]:
                temp = a_list[i]
                a_list[i] = a_list[i+1]
                a_list[i+1] = temp
    return a_list


def selection_sort(a_list):
    for fill_slot in range(0, len(a_list)-1):
        pos_of_min = fill_slot
        for location in range(fill_slot, len(a_list)):
            if a_list[location] < a_list[pos_of_min]:
                pos_of_min = location
            temp = a_list[fill_slot]
            a_list[fill_slot] = a_list[pos_of_min]
            a_list[pos_of_min] = temp
        return a_list

def insertion_sort(a_list):
    for index in range(1, len(a_list)):
        current_value = a_list[index]
        position = index
        while position > 0 and a_list[position-1] > current_value:
            a_list[position] = a_list[position-1]
            position -=1
        a_list[position] = current_value
    return a_list

count = int(input("Enter the number of numbers: "))
num_list = []
for i in range(0, count):
    number = int(input("Enter the number: "))
    num_list.append(number)
print("The number list is", num_list)

z =0
while z <1:
    type = input("Enter the types of sort (bubble, selection, insertion): ")

    if type == "bubble":
        num_list = bubble_sort(num_list)
        break
    elif type == "selection":
        num_list = selection_sort(num_list)
        break
    elif type == "insertion":
        num_list = insertion_sort(num_list)
        break
    else:
        print("Wrong sort type, again!")
        continue


print("The sorted number list is", num_list)
target = int(input("Enter the target number to find: "))
result = binary_search(num_list, target)
if result == True:
    print("We found!")
else:
    print("We cannot found!")