%hw2_2_9

n=10;
c = ones(n-1,1)*-1;
d = ones(n)*4;
e = ones(n-1,1)*-1;
b = ones(n,1)*5; b(1)=9;
[c,d,e] = LUdec3(c,d,e);
x = LUsol3(c,d,e,b);

x