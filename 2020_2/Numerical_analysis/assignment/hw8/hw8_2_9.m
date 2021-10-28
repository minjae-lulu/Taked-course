%hw8_2_9

function hw8_2_9
xStart = 0; xStop = pi; n = 21; freq = 1;
x = linspace(xStart,xStop,n);
y = x.*x/pi^2; 
h = (xStop - xStart)/(n-1);
y = newtonRaphson2(@residual,y,1.0e-5);
printSol(x,y,freq)
plot(x,y); grid on

function r = residual(y)

    r = zeros(n,1);
    r(1) = -2*y(1) + 2*y(2)- h*h*y(1)*y(1)*sin(y(1));
    r(n) = y(n) - 1;
    for i = 2:n-1
    r(i) = y(i-1) - 2*y(i) + y(i+1) - h*h*y(i)*y(i)*sin(y(i));
    end
end

end
