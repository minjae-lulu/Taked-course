#final_3
import re

while True:
    com = input("Enter command (m, b, or e): ")
    if com == 'm':
        x = 0; y = 0; z = 0
        target = input("Enter the move commands: ")

        x_minus1 = len(re.findall("[1]", target))
        x_minus3 = len(re.findall("[3]", target))
        x_minus5 = len(re.findall("[5]", target))
        x_minus7 = len(re.findall("[7]", target))
        x_minus9 = len(re.findall("[9]", target))
        x_plus0 = len(re.findall("[0]", target))
        x_plus2 = len(re.findall("[2]", target))
        x_plus4 = len(re.findall("[4]", target))
        x_plus6 = len(re.findall("[6]", target))
        x_plus8 = len(re.findall("[8]", target))

        x = ((x_plus0+x_plus2+x_plus4+x_plus6+x_plus8)-(x_minus1+x_minus3+x_minus5+x_minus7+x_minus9))

        y_minus = len(re.findall("[a-z]", target))
        y_plus = len(re.findall("[A-Z]", target))
        y = ((y_plus)-(y_minus))

        z_minus1 = len(re.findall("[!]", target))
        z_minus3 = len(re.findall("[#]", target))
        z_minus5 = len(re.findall("[%]", target))
        z_minus7 = len(re.findall("[&]", target))
        z_minus9 = len(re.findall("[(]", target))

        z_plus2 = len(re.findall("[@]", target))
        z_plus4 = len(re.findall("[$]", target))
        z_plus6 = len(re.findall("[str(^)]", target))
        z_plus8 = len(re.findall("[*]", target))
        z_plus0 = len(re.findall("[)]", target))

        z = ((z_plus2+z_plus4+z_plus6+z_plus8+z_plus0)-(z_minus1+z_minus3+z_minus5+z_minus7+z_minus9))

        print("({0},{1},{2})".format(x,y,z))


    elif com == 'b':
        target2 = input("You say a word: ")

        re_word = re.findall("[A-Z][a-z]+",target2)
        other = re.findall("\W+",target2)

        #print(''.join(reversed(re_word)))
        print("Robot say a word:",target2[::-1])


    elif com == 'e':
        print("Good bye!")
        break

    else:
        print("Wrong robot commands!")