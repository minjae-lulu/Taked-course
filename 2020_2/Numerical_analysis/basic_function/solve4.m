function [x,numIter] = solve4(func,x,epsilon)
if nargin == 2; epsilon = 1.0e-6; end
for numIter = 1:30
    dx = feval(func,x);     % feval is a MATLAB function for
    x = x + dx;             % evaluating a passed function
if abs(dx) < epsilon; return; end
end
error('Too many iterations')