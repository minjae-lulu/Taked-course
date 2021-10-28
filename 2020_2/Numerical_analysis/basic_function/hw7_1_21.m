% hw7_1_21

x = 0; xstop = 0.05; h = 0.00025; y = [0 0];
[xsol,ysol] = runKut4(@p7_1_21,x,y,xstop,h);
printSol(xsol,ysol,0);

grid on
hold on
plot(xsol,ysol(:,1),'r-'); 
plot(xsol,ysol(:,2),'r--'); 
xlabel('Time (s)'); ylabel('Current (A)')
legend('i1','i2','location','best')


function F = p7_1_21(x,y)
R = 1; L = 0.2e-3; C = 3.5e-3;
E = 240*sin(120*pi*x);
dE = 240*120*pi*cos(120*pi*x);
F = zeros(1,2);
F(1) = (-3*R*y(1) - 2*R*y(2) + E)/L;
F(2) = (-2*F(1) -y(2)/R/C + dE/R)/3;
end