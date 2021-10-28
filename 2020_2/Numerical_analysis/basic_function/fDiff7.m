function fDiff7
xStart = 0; xStop = 2;       % Range of integration.
n = 101;                      % Number of mesh points.
freq = 1;                    % Printout frequency.
x = linspace(xStart,xStop,n)';
y = 0.5*x;                   % Starting values of y.
h = (xStop - xStart)/(n-1);
y = newtonRaphson2(@residual,y,1.0e-5);
printSol(x,y,freq)
plot(x,y,'r-o'); grid on;

     function r = residual(y)
        % Residuals of finite difference equations (left-hand
        % sides of Eqs (8.11).
        r = zeros(n,1);
        r(1) = y(1); r(n) = y(n) - 1;
        for i = 2:n-1
            r(i) = (y(i-1) - 2*y(i) + y(i+1))/h^2 ...
                 + 3*y(i)*(y(i+1) - y(i-1))/2/h;
        end
     end

%      function r = residual(y)
%         % Residuals of finite difference equations (left-hand
%         % sides of Eqs (8.11).
%         r = zeros(n,1);
%         r(1) = y(1); r(n) = y(n) - 1;
%         for i = 2:n-1
%             r(i) = y(i-1) - 2*y(i) + y(i+1)...
%                  - h*h*y2Prime(x(i),y(i),(y(i+1) - y(i-1))/(2*h));
%         end
%      end
% 
%      function F = y2Prime(x,y,yPrime)
%         % Second-order differential equation F = y".
%         F = -3*y*yPrime;
%     end
end