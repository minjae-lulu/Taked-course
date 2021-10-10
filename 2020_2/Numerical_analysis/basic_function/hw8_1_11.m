%hw8_1_11

function hw8_1_11

xStart = 0.01; xStop = 2; % Range of integration.
h = 0.1;                  % Step size.
freq = 1;                 % Frequency of printout.
u1 = -150; u2 = 0;        % Trial values of unknown
                          % initial condition u.
x = xStart;            

u = linInterp(@residual,u1,u2);
[xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
plot(xSol,ySol(:,1),'r-x'); 


    function F = dEqs(x,y) % First-order differential
    F = [y(2), -y(2)/x-y(1)]; % equations.
    end

    function y = inCond(u) % Initial conditions (u is
    y = [1 u]; % the unknown condition).
    end

	function r = residual(u) % Boundary residual.
    x = xStart;
    [xSol,ySol] = runKut5(@dEqs,x,inCond(u),xStop,h);
    r = ySol(size(ySol,1),2);
    end
    
end