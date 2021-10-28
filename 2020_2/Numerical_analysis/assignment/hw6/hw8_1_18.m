%hw8_1_18


function hw8_1_18
% Shooting method for 4th-order boundary value
% problem in Example 8.4.
xStart = 0; xStop = 4;    % Range of integration.
h = 0.5;                  % Step size.
freq = 1;                 % Frequency of printout.
u = [-0.3 0.3];                % Trial values of u(1) 
                          % and u(2).
x = xStart;
u = newtonRaphson2(@residual,u);
[xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
printSol(xSol,ySol,freq)
plot(xSol,ySol(:,1))

    function F = dEqs(x,y)    % Differential equations
    F = [y(2), y(3), y(4), -2*y(1)*y(3)];
    end

    function y = inCond(u)    % Initial conditions; u(1)
    y = [0 0 u(1) u(2)];      % and u(2) are unknowns.
    end

    function r = residual(u)  % Boundary residuals.
    r = zeros(length(u),1);
    x = xStart;
    [xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
    lastRow = size(ySol,1);
    r(1)= ySol(lastRow,1);
    r(2) = ySol(lastRow,2)-1;
    end
end