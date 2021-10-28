function shoot2
% Shooting method for 2nd-order boundary value problem
% in Example 8.1.

xStart = 0; xStop = 5;    % Range of integration.
h = 0.2;                  % Step size.
freq = 1;                 % Frequency of printout.
u1 = -10; u2 = 10;           % Trial values of unknown
                          % initial condition u.
%u = 1;
x = xStart;            
%u = bisect(@residual,u1,u2)
u = ridder(@residual,u1,u2)
%u = newtonRaphson2(@residual,u)
[xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
printSol(xSol,ySol,freq)
plot(xSol,ySol(:,1),'r-x'); hold on;
plot(xSol,ySol(:,2),'b-x'); grid on;

    function F = dEqs(x,y)    % First-order differential
    F = [y(2), y(3), -y(3)*y(1)]; % equations.
    end

    function y = inCond(u)    % Initial conditions (u is
    y = [0 0 u];                % the unknown condition).
    end

    function r = residual(u)  % Boundary residual.    
    x = xStart;
    [xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
    r = ySol(size(ySol,1),2) -2;
    end
end