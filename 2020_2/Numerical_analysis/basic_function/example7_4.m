function example7_4 %(4th order Runge-Kutta method)
 
x = 0; xStop = 2;
y = [0 1]; h = 0.2;
[xSol,ySol] = runKut4(@dEqs,x,y,xStop,h);
yExact = 100.*xSol - 5.*xSol.^2 + 990.*(exp(-0.1.*xSol) - 1);
plot(xSol,ySol(:,1),'ko')
hold on
plot(xSol,yExact,'k-')
xlabel('x');ylabel('y')
legend('Numerical','Exact','Location','Best')

   function F = dEqs(x,y)
   F = zeros(1,2);
   F(1) = y(2);
   F(2) = -0.1*y(2) - x;
   end % function dEqs

end % function example7_4
