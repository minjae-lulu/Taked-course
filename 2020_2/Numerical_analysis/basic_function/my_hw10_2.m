function my_hw10_2
% 1-D heat equation using the BTCS method
xStart = 0.0; xStop = 1.0; 
dx = 0.1; J = (xStop - xStart)/dx +1; 
x = linspace(xStart,xStop,J)'; 
dt = 0.05; time = 0.0; tStop = 1.0;
alpha = 0.1; r = alpha*dt/dx^2; S = 1.0;

f = zeros(J,1);   % initial condition f(:,1) - old; f(:,2) - new
plot(x,f); %hold on; 
xlabel('x'), ylabel('f'), grid on
title '1D heat equation with the BTCS method'

c = -r*ones(J-1,1); c(J-1) = 0.0;
d = (1+2*r)*ones(J,1); d(J) = 1.0;
e = -r*ones(J-1,1); e(1) = -2*r; %f(i-1) = f(i+1) bc
b = zeros(J,1);
[c,d,e] = LUdec3(c,d,e);

while (time < tStop)
    b(1) = f(1)+S*dt; b(J) = 0.0; % Boundary conditions
    b(2:J-1) = f(2:J-1)+S*dt;
    f = LUsol3(c,d,e,b);
    plot(x,f); %pause(0.01);
    time = time + dt;
end

 