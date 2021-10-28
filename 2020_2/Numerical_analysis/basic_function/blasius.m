function blasius
xStart = 0; xStop = 10;    % Range of integration.
h = 0.01;                  % Step size.
freq = 2;                 % Frequency of printout.
u1 = 0; u2 = 0.5;           % Trial values of unknown
                          % initial condition u.
x = xStart;            
u = ridder(@residual,u1,u2)
[xSol,ySol] = runKut4(@dEqs,x,inCond(u),xStop,h);
printSol(xSol,ySol,freq)
plot(xSol,ySol(:,2),'k-')
grid on

    function F = dEqs(x,y)    % First-order differential
    F = [y(2), y(3), -0.5*y(1)*y(3)]; % equations.
    end

    function y = inCond(u)    % Initial conditions (u is
    y = [0 0 u];                % the unknown condition).
    end

    function r = residual(u)  % Boundary residual.    
    x = xStart;
    [xSol,ySol] = runKut4(@dEqs,x,inCond(u),xStop,h);
    lastRow = size(ySol,1);
    lastColumn = size(ySol,2)
    r = ySol(lastRow,2) - 1;
    end
end