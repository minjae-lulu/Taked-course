f = open('arbor_day.txt', 'rt')

d = {}

for line in f:

    (key,val)=(line.strip().split('\t'))
    d[key] = val

name = str(input("Enter a country: "))

if name in d.keys():
    print("The arbor day of", key, 'is', val)

if name not in d:
    print("I don't know")

f.close()