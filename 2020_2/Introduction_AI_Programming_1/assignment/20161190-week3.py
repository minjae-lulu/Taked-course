def repayment_amount(principal_amount,interest_per_year,time_period):
    if interest_type == 'S':
        repayment_amount = principal_amount * (1 + (interest_per_year * time_period)/100)
        return repayment_amount

    elif interest_type == 'C':

        repayment_amount = principal_amount * (((1 + interest_per_year/100) ** time_period))
        return repayment_amount

    else:
        return 3

principal_amount = int(input("Enter the principal amount: "))
interest_per_year = float(input("Enter the rate of interest per year (%): "))
time_period = int(input("Enter the loan time period (year): "))
interest_type = str(input("Enter the interest type (S or C): "))

repayment_amount = float(repayment_amount(principal_amount,interest_per_year,time_period))

if interest_type == 'S' :
    print("The repayment value is", repayment_amount)

elif interest_type == 'C':
    print("The repayment value is", repayment_amount)

else:
    print("Wrong type.")
