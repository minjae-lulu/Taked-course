function kkkk1234
xStart = 0.5; xStop = 1; n = 11;
h = (xStop - xStart)/(n-1);
x = zeros(n,1); y = zeros(n,2);
x(1) = xStart;

for i = 2:n
    x(i) = x(i-1) + h;
    y(i,2) = 200*(1 - log(x(i))/log(0.5)); 
end

[c,d,e,b] = fdEqs(x,h,n);
[c,d,e] = LUdec3(c,d,e);
y(:,1) = LUsol3(c,d,e,b); 
printSol(x,y,1)
plot(x,y); grid on; hold on;

function[c,d,e,b] = fdEqs(x,h,n)
h2 = h*h;
c = zeros(n-1,1);
d = ones(n,1)*(-2);
e = zeros(n-1,1);

for i = 1:n-1
c(i) = 1 - h/2/x(i+1);
e(i) = 1 + h/2/x(i);
end

e(1) = 0; c(n-1) = 0;
d(1) = 1; d(n) = 1;
b = zeros(n,1); b(n) = 200;
end
end