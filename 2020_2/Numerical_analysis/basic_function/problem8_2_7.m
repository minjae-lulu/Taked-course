function problem8_2_7
% Finite difference method for the second-order,
% linear boundary value problem in Example 8.6.

xStart = 0; xStop = 1;   % Range of integration.
n = 101;                     % Number of mesh points.
freq = 1;                   % Printout frequency.

h = (xStop - xStart)/(n-1);
x = linspace(xStart,xStop,n)';
 
d = ones(n,1)*(-2 + h^2); d(1) = 1; d(n) = 1;
c = ones(n-1,1)*(1-h); c(n-1) = 0;
e = ones(n-1,1)*(1+h); e(1) = 0;
b = zeros(n,1); b(n) = 1;

[c,d,e] = LUdec3(c,d,e);
ySol = LUsol3(c,d,e,b);
printSol(x,ySol,freq)
plot(x,ySol)