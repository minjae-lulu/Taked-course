function [x,numIter] = solve2(x,epsilon)
if nargin == 1           % Specify default value if
    epsilon = 1.0e-6;    % second input argument is
end                      % omitted in function call
for numIter = 1:100
    dx = -(sin(x) - 0.5*x)/(cos(x) - 0.5);
    x = x + dx;
    if abs(dx) < epsilon % Converged; return to
        return           % calling program
    end
end
error('Too many iterations')