function xSol = nonlin_eq
%xSol = newtonRaphson2(@func, [1 1 1]);
xSol = fsolve(@func, [1 1 1]);
    function y = func(x)
    y = [sin(x(1)) + x(2)^2 + log(x(3)) - 7;...
    3*x(1) + 2^x(2) - x(3)^3 + 1; x(1) + x(2) + x(3) - 5];
    end
end