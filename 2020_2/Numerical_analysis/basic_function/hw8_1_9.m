%hw8_1_9

function hw8_1_9
% Shooting method for 2nd-order boundary value problem
% in Example 8.1.

xStart = 0; xStop = 2;    % Range of integration.
h = 0.1;                  % Step size.
freq = 2;                 % Frequency of printout.
u1 = -10; u2 = 0;           % Trial values of unknown
                          % initial condition u.
x= xStart;          
u = bisect(@residual,u1,u2);
[xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
printSol(xSol,ySol,freq)
plot(xSol,ySol(:,1))

    function F = dEqs(x,y)    % First-order differential
    F = [y(2), -2*y(2)-3*y(1)*y(1)]; % equations.
    end

    function y = inCond(u)    % Initial conditions (u is
    y = [0 u];                % the unknown condition).
    end

    function r = residual(u)  % Boundary residual.    
    x = xStart;
    [xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
    r = ySol(size(ySol,1),1) + 1;
    end
end