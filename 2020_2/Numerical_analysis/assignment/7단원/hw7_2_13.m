% hw7_2_13

F = inline('[(9/y(1)-y(1))*x]','x','y');
x = 0; xstop = 5; y = [5];
h = 0.05;

[xsol,ysol] = runKut5(F,x,y,xstop,h);
printSol(xsol,ysol,5);
grid on
plot(xsol,ysol,'r-x');