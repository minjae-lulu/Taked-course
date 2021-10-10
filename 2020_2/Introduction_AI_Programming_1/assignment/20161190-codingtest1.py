
# com = input("Enter command (i, d, e): ")

z = 0
while z <1:
    com = input("Enter command (i, d, e): ")
    if com == "i":
        start = int(input("Enter a start times table (2~34): "))
        end = int(input("Enter an end times table (2~34): "))
        continue

    elif com == "d":
        yn = input("Group printing? (y or n): ")
        if yn == "n":
            for f in range(start,end+1):
                for s in range(1,10):
                    print(f,"*",s,"=",f*s)
                    continue

        elif yn == "y":

            for f in range(start,end+1):
                for s in range(1,10):
                    print("{} * {} = {}".format (f,s,f*s), end="\t")
                    continue


    elif com == "e":
        print("Program is done")
        break

    else:
        print("Wrong commands. i, d, or, e!")

