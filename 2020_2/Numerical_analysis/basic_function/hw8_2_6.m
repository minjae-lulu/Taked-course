%hw8_2_6

xStart = 1; xStop =2; n = 21; freq = 1;

h = (xStop - xStart)/(n-1);
x = linspace(xStart,xStop,n)';

[c,d,e,b] = fDiffEqs(x,h,n);
[c,d,e] = LUdec3(c,d,e);
printSol(x,LUsol3(c,d,e,b),freq)
plot(x,LUsol3(c,d,e,b)); grid on;

function [c,d,e,b] = fDiffEqs(x,h,n)
b = zeros(n,1);
c = ones(n-1,1);
d = -(h*h).*x -2;
e = ones(n-1,1);

b(1) = 1.5; b(n) = 3;
c(n-1)=0;    d(1)=1; d(n)=1;    e(1)=0;


end



