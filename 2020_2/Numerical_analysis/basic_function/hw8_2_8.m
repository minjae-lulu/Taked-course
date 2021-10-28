%hw8_2_8

xStart = 1; xStop = 2; n = 21; freq = 1;
h = (xStop-xStart)/(n-1);
x=linspace(xStart,xStop,n);

[c,d,e,b] = fDiffEqs(x,h,n);
[c,d,e] = LUdec3(c,d,e);
printSol(x,LUsol3(c,d,e,b),freq);
plot(x,LUsol3(c,d,e,b)); grid on; hold on;


function [c,d,e,b] = fDiffEqs(x,h,n)
h2 = h*h;
b = zeros(n,1);
c = 1 - h./x(2:n)/2;
d = -2 + h2./x./x;
e = 1 + h./x(1:n-1)/2;

b(n) = 0.638961;
d(1)=1; d(n)=1; c(n-1)=0; e(1)=0;

end