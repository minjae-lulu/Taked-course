#midterm 5

weight = float(input("Enter the weight of a package (KG): "))

if weight <= 2:
    won = weight * 2000
    won = int(won)
    won = format(won,',')
    print("Your shipping charges for the package is {}.".format(won))

elif weight > 2 and weight <= 6:
    won = weight * 3500
    won = int(won)
    won = format(won,',')
    print("Your shipping charges for the package is {}.".format(won))

elif weight > 6 and weight <= 10:
    won = weight * 3800
    won = int(won)
    won = format(won,',')
    print("Your shipping charges for the package is {}.".format(won))

elif weight > 10:
    won = weight * 4200
    won = int(won)
    won = format(won,',')
    print("Your shipping charges for the package is {}.".format(won))