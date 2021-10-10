# midterm 4

year = int(input("Enter a year: "))

if year % 4 == 0:
    if year % 100 == 0:
        if year % 400 == 0:
            print("Leap year")

        else:
            print("Common year")

    else :
        print("Leap year")

else :
    print("Common year")

