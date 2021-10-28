function my_hw10_1
% 1-D heat equation using the FTCS method
xStart = 0.0; xStop = 1.0; 
dx = 0.1; J = (xStop - xStart)/dx +1; 
x = linspace(xStart,xStop,J)'; 
dt = 0.05; time = 0.0; tStop = 1.0;
alpha = 0.1; r = alpha*dt/dx^2; S = 1.0;

T = zeros(J,2);   % initial condition f(:,1) - old; f(:,2) - new
plot(x,T(:,2)); %hold on; 
xlabel('x'), ylabel('f'), grid on
title '1D heat equation with the FTCS method'

while (time < tStop)
    T(J,:) = 0.0; % Boundary conditions
    T(1,2) = 2*r*T(2,1) + (1-2*r)*T(1,1) + S*dt;
    T(2:J-1,2) = r*T(1:J-2,1) + (1-2*r)*T(2:J-1,1) + r*T(3:J,1) + S*dt;
    plot(x,T(:,2)); %pause(0.01);
    time = time + dt;
    T(:,1) = T(:,2);
end
end
 