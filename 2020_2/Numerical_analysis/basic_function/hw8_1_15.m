%hw8_1_15

function hw8_1_15
% Shooting method for 3rd-order boundary value
% problem in Example 8.3.
xStart = -1; xStop = 1;   % Range of integration.
h = 0.1;                % Step size.
u1 = 0; u2 = 10;          % Trial values of unknown
                         % initial condition u.
x = xStart;
u = ridder(@residual,u1,u2);
[xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
printSol(xSol,ySol,3);
plot(xSol,ySol(:,1),'k-o')
xlabel('x'); ylabel('y')
grid on

    function F = dEqs(x,y) % 1st-order differential eqs.
    F = [y(2), y(3), -2*y(3)-sin(y(1))];
    end

    function y = inCond(u) % Initial conditions.
    y = [0 -1 u];
    end

    function r = residual(u) % Boundary residual.
    x = xStart;
    [xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
    r = ySol(size(ySol,1),2) - 1;
    end
end