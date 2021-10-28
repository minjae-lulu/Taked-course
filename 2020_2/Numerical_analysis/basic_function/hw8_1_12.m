%hw8_1_12


function hw8_1_12
% Shooting method for 2nd-order boundary value problem
% in Example 8.1.

xStart = 0; xStop = 10;    % Range of integration.
h = 1;                  % Step size.
freq = 2;                 % Frequency of printout.
u1 = 0; u2 = 2;           % Trial values of unknown
                          % initial condition u.
x = xStart;            
%u = bisect(@residual,u1,u2)
u = linInterp(@residual,u1,u2)
[xSol,ySol] = bulStoer(@dEqs,x,inCond(u),xStop,h);
printSol(xSol,ySol,freq)
plot(xSol,ySol(:,1),'r-x')

    function F = dEqs(x,y)    % First-order differential
    F = [y(2), y(1)-exp(-x)]; % equations.
    end

    function y = inCond(u)    % Initial conditions (u is
    y = [1 u];                % the unknown condition).
    end

    function r = residual(u)  % Boundary residual.    
    x = xStart;
    [xSol,ySol] = bulStoer(@dEqs,x,inCond(u),xStop,h);
    r = ySol(size(ySol,1),1);
    end
end