
records = dict()

while True:
    letter = input("Select the instruction (a: add, s: sort, d: done): ")

    if letter == "a":
        n = input("Enter the name: ")
        pn = input("Enter the phone: ")
        if n not in records:
            records[n] = dict()
        records[n] = pn

    elif letter == "s":

        b = records.keys()
        c = list(b)
        c.sort()
        for key in c:

            print(key, records[key])


    elif letter == "d":
        print("Program is done.")
        break

    else:
        print("Wrong insturction.... a or s or d!")