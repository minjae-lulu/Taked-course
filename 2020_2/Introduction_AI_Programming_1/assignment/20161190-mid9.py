#mid 9

sales = int(input("Enter the monthly sales: "))
pay = int(input("Enter the advanced pay: "))

if sales < 10000:
    rate = 0.1

elif sales >= 10000 and sales <= 14999:
    rate = 0.12

elif sales >= 15000 and sales <= 17999:
    rate = 0.14

elif sales >= 18000 and sales <= 21999:
    rate = 0.16

elif sales >= 18000 and sales <= 21999:
    rate = 0.16

elif sales >= 22000:
    rate = 0.18

result = sales * rate - pay
print("The pay is ${}".format(int(result)))

if result <0:
    print("The salesperson must reimburse the company.")