function example7_6 %(Runge-Kutta integration)
x = 0; y = [7.15014e6 0 0 0.937045e-3];
xStop = 1200; h = 50; freq = 2;
[xSol,ySol] = runKut4(@dEqs,x,y,xStop,h);
%[xSol,ySol] = ode45(@dEqs,[x xStop],y);
printSol(xSol,ySol,freq)
subplot(2,2,1)
plot(xSol,ySol(:,1)); title('y(1)');
subplot(2,2,2)
plot(xSol,ySol(:,2)); title('y(2)');
subplot(2,2,3)
plot(xSol,ySol(:,3)); title('y(3)');
subplot(2,2,4)
plot(xSol,ySol(:,4)); title('y(4)');

   function F = dEqs(x,y)
   F = zeros(1,4);
   F(1) = y(2);
   F(2) = y(1)*y(4)^2 - 3.9860e14/y(1)^2;
   F(3) = y(4);
   F(4) = -2*y(2)*y(4)/y(1);
   % F = F';  % For ode45
   end % function dEqs

end % function example7_6