function example7_5 %(4th order Runge-Kutta method)
dEqs = @(x,y)(3*y - 4*exp(-x)); 
x = 0; xStop = 10;
y = 1; h = 0.1;
[xSol,ySol] = runKut4(dEqs,x,y,xStop,h);
printSol(xSol,ySol,20);
end % function example7_5