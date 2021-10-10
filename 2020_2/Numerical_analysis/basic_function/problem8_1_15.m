function problem8_1_15
% Shooting method for 2nd-order boundary value problem
% in Example 8.1.

xStart = -1; xStop = 1;    % Range of integration.
h = 0.1;                  % Step size.
freq = 2;                 % Frequency of printout.
u1 = -100; u2 = 100;           % Trial values of unknown
                          % initial condition u.
x = xStart;            
u = ridder(@residual,u1,u2)
[xSol,ySol] = runKut4(@dEqs,x,inCond(u),xStop,h);
printSol(xSol,ySol,freq)
plot(xSol,ySol(:,1))

    function F = dEqs(x,y)    % First-order differential
    F = [y(2), y(3), -2*y(3)-sin(y(1))]; % equations.
    end

    function y = inCond(u)    % Initial conditions (u is
    y = [0 -1 u];                % the unknown condition).
    end

    function r = residual(u)  % Boundary residual.    
    x = xStart;
    [xSol,ySol] = runKut4(@dEqs,x,inCond(u),xStop,h);
    lastRow = size(ySol,1);
    r = ySol(lastRow,2) - 1;
    end
end