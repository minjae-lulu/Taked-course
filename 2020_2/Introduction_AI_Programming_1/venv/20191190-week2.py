year = int(input ("Enter the year: "))
month = int(input ("Enter the month: "))
day = int(input ("Enter the day: "))
type = str(input("Enter the type of the date: "))


if type == 'B':
    print(str(year) + "-" + str(month)+"-" + str(day))

elif type == 'L':
    print(str(day) + "-" + str(month)+"-" + str(year))

elif type == 'M':
    print(str(month) + "-" + str(day)+"-" + str(year))

else :
    print("Wrong date format.")
