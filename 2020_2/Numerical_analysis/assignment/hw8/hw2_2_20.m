%hw2_2_20

c = [8 6 3 2]';
d = [-8 -10 -11 -7 -4]';
e = [4 2 5 4]';

b = [-80 0 0 0 -30]';

[c,d,e] = LUdec3(c,d,e);
x = LUsol3(c,d,e,b);

x