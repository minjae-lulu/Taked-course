%experi
function k
xStart = 0; xStop = 10; h = 0.1; freq = 1;
u = [0 0];

x = xStart;
u = newtonRaphson2(@residual,u);
[xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
printSol(xSol,ySol,freq)
plot(xSol,ySol(:,1))

    function F = dEqs(x,y)    % Differential equations
    m = 20; c = 3.2e-4; g = 9.80665;
    v = sqrt(y(2)*y(2) + y(4)*y(4));
       
    F = [y(2), -c/m*v*y(2), y(4), -c/m*v*y(4)-g];
    end

    function y = inCond(u)
        y = [0 u(1) 0 u(2)];
    end

    function r = residual(u)
        r = zeros(2,1);
        x = xStart;
        [xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
        r(1) = ySol(size(ySol,1),1) -8000;
        r(2) = ySol(size(ySol,1),3);
    end
end