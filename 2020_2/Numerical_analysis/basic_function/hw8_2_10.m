%hw8_2_10

function hw8_2_10

xStart = 0; xStop = 1; n = 21; freq = 1;
x = linspace(xStart,xStop,n);
y = -2/9*x + 0.5; 
h = (xStop - xStart)/(n-1);
y = newtonRaphson2(@residual,y);
printSol(x,y,freq)
plot(x,y); hold on

function r = residual(y)
    r = zeros(n,1);
    r(1) = y(1) - 0.5;
    r(n) = 2*y(n-1) - 2*y(n) - 4/9*h + h*h*2*y(n)* (2*x(n)*(-2/9) +y(n));
    for i = 2:n-1
    r(i) = y(i-1) - 2*y(i) + y(i+1) + h*h*2*y(i)* (2*x(i)*((y(i+1)-y(i-1))/h/2)+y(i));
    end
end

end
