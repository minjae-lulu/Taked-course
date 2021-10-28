% hw7_2_15

F = inline('[y(2) -y(2)/x-y(1)/x/x]','x','y');
x = 1; xstop = 20; y = [0 -2];
h = 1;
[xsol,ysol] = bulStoer(F,x,y,xstop,h);
printSol(xsol,ysol,1)

hold on
grid on
plot(xsol,ysol(:,1),'r-x')
plot(xsol,ysol(:,2),'r-o')
legend('y2','y1','location','best')