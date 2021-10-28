% hw7_2_6

F = inline('[y(2) -230*y(2)-225*y(1)]','x','y');
x = 0; xstop = 0.2; y = [0.01 0];
h = 0.008;
[xsol,ysol] = runKut5(F,x,y,xstop,h);
printSol(xsol,ysol,0)

grid on
plot(xsol,ysol(:,2),'r--x')
xlabel('Time'); ylabel('dy/dt');