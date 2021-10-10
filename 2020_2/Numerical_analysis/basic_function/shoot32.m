function shoot32
% Shooting method for 2nd-order boundary value problem
% in Example 8.1.

xStart = 5; xStop = 0;    % Range of integration.
h = -0.001;                  % Step size.
freq = 2;                 % Frequency of printout.
u1 = 1; u2 = 2;           % Trial values of unknown
                          % initial condition u.
x = xStart;            
u = linInterp(@residual,u1,u2)
[xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
printSol(xSol,ySol,freq)
plot(xSol,ySol(:,1),'k-o')
grid on

    function F = dEqs(x,y)    % First-order differential
    F = [y(2), y(3), 2*y(3)+6*x*y(1)]; % equations.
    end

    function y = inCond(u)    % Initial conditions (u is
    y = [0 0 u];                % the unknown condition).
    end

    function r = residual(u)  % Boundary residual.    
    x = xStart;
    [xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
    %lastRow = size(ySol,1);
    r = ySol(size(ySol,1),1) - 2;
    end
end