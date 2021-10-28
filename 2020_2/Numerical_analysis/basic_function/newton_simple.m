function [root,numIter] = newton_simple(func,dfunc,x)
% Simple version of Newton--Raphson method
iMax = 50;
tol = 1.0e6*eps;
for i = 1:iMax
    dx = -feval(func,x)/feval(dfunc,x);
    x = x + dx;
    if abs(dx) < tol
        root = x; numIter = i; return
    end
end
root = NaN;
numIter = iMax;
