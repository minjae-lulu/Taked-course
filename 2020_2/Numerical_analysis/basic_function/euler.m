function [xSol,ySol] = euler(dEqs,x,y,xStop,h)
% Euler's method for solving initial value problems. 
% USAGE: [X,Y] = euler(dEqs,x,y,xStop,h).
% INPUT:
% dEqs =  handle of function that specifies the
%         1st-order differential equations
%         F(x,y) = [y'(1),y'(2),...,y'(n)].
% x,y   = initial values; y must be a row vector.
% xStop = terminal value of x.
% h     = increment of x used in integration (h > 0).
% OUTPUT:
% xSol  = x-values at which solution is computed.
% ySol  = y-values of solution.
 if size(y,1) > 1; y = y'; end % y must be row vector
xSol = zeros(2,1); ySol = zeros(2,length(y));
xSol(1) = x; ySol(1,:) = y;
k = 1;
while x < xStop
    h = min(h,xStop - x);
    y = y + h*feval(dEqs,x,y);
    x = x + h; k = k + 1;
    xSol(k) = x; ySol(k,:) = y;
end