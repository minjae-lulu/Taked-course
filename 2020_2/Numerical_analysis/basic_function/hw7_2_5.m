% hw7_2_5
F = inline('[y(2) -230*y(2)-225*y(1)]','x','y');
x = 0; xstop = 0.2; y = [0.01 0];
h = 0.008;
[xsol,ysol] = runKut4(F,x,y,xstop,h);
printSol(xsol,ysol,0)
plot(xsol,ysol(:,2),'r--x')
grid on
xlabel('Time'); ylabel('dy/dt');