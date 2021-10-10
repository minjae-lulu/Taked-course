F = inline('[y(2) -sin(y(1))]' , 'x', 'y');
x = 0; 
y = [1 0];
xstop = 2.2*pi ;
h = 0.25;

[xsol, ysol] = runKut4 (F,x,y,xstop,h);
printSol(xsol,ysol,1)