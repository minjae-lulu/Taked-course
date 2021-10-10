function [x,numIter] = solve3(x,epsilon)
if nargin == 1; epsilon = 1.0e-6; end
for numIter = 1:30
    dx = myfunc(x);
    x = x + dx;
    if abs(dx) < epsilon; return; end
end
error('Too many iterations')

function y = myfunc(x)
y = -(sin(x) - 0.5*x)/(cos(x) - 0.5);