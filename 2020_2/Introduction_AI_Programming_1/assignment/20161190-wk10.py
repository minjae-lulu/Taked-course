import re

while True:
    target = input("Enter a degree: ")
    pattern1 = "\d+C"
    pattern2 = "\d+F"

    regexp1 = re.compile(pattern1,re.IGNORECASE)
    regexp2 = re.compile(pattern2,re.IGNORECASE)

    result1 = regexp1.search(target)
    result2 = regexp2.search(target)

    if result1:
        temperature = float(re.findall("[-+]?[.]?[\d]+[\.]?\d*",target)[0])
        result_temp = temperature * 9 /5 +32
        print("%.2fF" % (result_temp))
        break

    elif result2:
        temperature = float(re.findall("[-+]?[.]?[\d]+[\.]?\d*",target)[0])
        result_temp = 5 * (temperature - 32) / 9
        print("%.2fC" % (result_temp))
        break
    else:
        continue