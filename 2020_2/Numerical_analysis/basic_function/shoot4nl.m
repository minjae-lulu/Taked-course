function shoot4nl
% Shooting method for nonlinear 4th-order boundary 
% value problem in Example 8.5.
 xStart = 0; xStop = 1;    % Range of integration.
h = 0.01;                  % Step size.
freq = 1;                 % Frequency of printout.
u = [-1 1];               % Trial values of u(1) 
                          % and u(2).
x = xStart;
u = newtonRaphson2(@residual,u);
[xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
printSol(xSol,ySol,freq)
plot(xSol,ySol(:,1))

     function F = dEqs(x,y)    % Differential equations.
        F = zeros(1,4);
        F(1) = y(2); F(2) = y(3); F(3) = y(4);
        if x < 10.0e-4; F(4) = -12*y(2)*y(1)^2;
        else           F(4) = -4*(y(1)^3)/x;
        end
    end
     function y = inCond(u)    % Initial conditions; u(1)
        y = [0 0 u(1) u(2)];      % and u(2) are unknowns.
    end
     function r = residual(u)  % Boundary residuals.
        r = zeros(length(u),1);
        x = xStart;
        [xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
        lastRow = size(ySol,1);
        r(1) = ySol(lastRow,3);
        r(2) = ySol(lastRow,4) - 1;
    end
end