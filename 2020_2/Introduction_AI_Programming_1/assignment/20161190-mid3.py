# midterm 3

rate = float(input("Enter the exchange rate from EUR to KRW: "))
convert = input("Enter EK to convert EUR to KRW and KE vice versa: ")
amount = float(input("Enter the amount: "))

if convert == "EK":
    result = rate * amount
    print("EUR {} is KRW {}".format (amount,result))

if convert == "KE":
    result = amount / rate
    print("KRW {} is EUR {}".format(amount,result))

