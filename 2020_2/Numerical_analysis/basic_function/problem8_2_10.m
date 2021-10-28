function problem8_2_10
% Finite difference method for the second-order,
% nonlinear boundary value problem in Problem 8-2-10
xStart = 0; xStop = 1;       % Range of integration.
n = 51;                      % Number of mesh points.
freq = 1;                    % Printout frequency.
x = linspace(xStart,xStop,n)';
y = -2/9*x + 0.5;               % Starting values of y.
h = (xStop - xStart)/(n-1);
y = newtonRaphson2(@residual,y,1.0e-5);
printSol(x,y,freq)
plot(x,y,'r-o'); grid on;

     function r = residual(y)
        % Residuals of finite difference equations (left-hand
        % sides of Eqs (8.11).
        r = zeros(n,1);
        r(1) = y(1)-1/2; 
        x = linspace(xStart,xStop,n)';
        for i = 2:n-1
            r(i) = (y(i-1) - 2*y(i) + y(i+1))/h^2 ...
                 + 2*y(i)*(2*x(i)*(y(i+1) - y(i-1))/2/h + y(i));
        end
        r(n) = (2*y(n-1) - 2*y(n) - 4/9*h)/h^2 ...
             + 2*y(n)*(2*x(n)*(-2/9) + y(n));
     end
end