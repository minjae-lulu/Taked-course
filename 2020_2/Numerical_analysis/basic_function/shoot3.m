function shoot3
% Shooting method for 3rd-order boundary value
% problem in Example 8.3.
xStart = 0; xStop = 5;   % Range of integration.
h = 0.01;                % Step size.
u1 = -10; u2 = 10;          % Trial values of unknown
                         % initial condition u.
x = xStart;
u = linInterp(@residual,u1,u2);
[xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
printSol
plot(xSol,ySol(:,1),'k-o')
xlabel('x'); ylabel('y')
grid on

    function F = dEqs(x,y) % 1st-order differential eqs.
    F = [y(2), y(3), -y(3)/x+y(2)/x/x+0.1*y(2)^3];
    end

    function y = inCond(u) % Initial conditions.
    y = [0 0 0];
    end

    function r = residual(u) % Boundary residual.
    x = xStart;
    [xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
    r = ySol(size(ySol,1),1) -1;
    end
end