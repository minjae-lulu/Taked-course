function test

x = 0; xStop = 10;
y = 1; h = 0.5;
[xSol,ySol] = runKut4(@dEqs,x,y,xStop,h);
yExact = exp(-5.*xSol);
plot(xSol,ySol(:,1),'r-o')
hold on
plot(xSol,yExact,'k-')
xlabel('x');ylabel('y')
legend('Numerical','Exact','Location','Best')

   function F = dEqs(x,y)
   F = zeros(1,1);
   F(1) = -5*y(1);
   end % function dEqs

end % function example7_2
