%hw8_1_19


function hw8_1_19
% Shooting method for 4th-order boundary value
% problem in Example 8.4.
xStart = 0; xStop = 10;    % Range of integration.
h = 1;                  % Step size.
freq = 1;                 % Frequency of printout.
u = [1000 600];                % Trial values of u(1) 
                          % and u(2).
x = xStart;
u = newtonRaphson2(@residual,u);
[xSol,ySol] = bulStoer(@dEqs,x,inCond(u),xStop,h);
printSol(xSol,ySol,freq)
plot(xSol,ySol(:,1))

    function F = dEqs(x,y)    % Differential equations
    m = 20; c = 3.2e-4; g = 9.80665;
    v = sqrt(y(2)*y(2) + y(4)*y(4));
       
    F = [y(2), -c/m*v*y(2), y(4), -c/m*v*y(4)-g];
    end

    function y = inCond(u)    % Initial conditions; u(1)
    y = [0 u(1) 0 u(2)];      % and u(2) are unknowns.
    end

    function r = residual(u)  % Boundary residuals.
    r = zeros(length(u),1);
    x = xStart;
    [xSol,ySol] = bulStoer(@dEqs,x,inCond(u),xStop,h);
    lastRow = size(ySol,1);
    r(1 )= ySol(lastRow,1)-8000;
    r(2) = ySol(lastRow,3);
    end
end