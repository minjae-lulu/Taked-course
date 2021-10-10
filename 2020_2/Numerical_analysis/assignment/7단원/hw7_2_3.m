% hw7_2_3

F = inline('[x-10*y(1)]','x','y');
for h = [0.1 0.25 0.5];
x = 0; xstop = 5; y(1) = 10;
[xsol,ysol] = runKut4(F,x,y,xStop,h);
fprintf('\nh = %8.4f\n',h)
printSol(xSol,ySol,0)
end