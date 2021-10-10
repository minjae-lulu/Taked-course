% hw7_2_4

F = inline('[x-10*y(1)]','x','y')
x = 0; xstop = 10; y(1) = 10;
[xsol,ysol] = runKut5(F,x,y,xstop,h);
printSol(xsol,ysol,0)
