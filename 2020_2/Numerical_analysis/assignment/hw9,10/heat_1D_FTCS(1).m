function heat_1D_FTCS
% 1-D heat equation using the FTCS method
xStart = 0.0; xStop = 1.0; 
J = 21; dx = (xStop - xStart)/(J-1); 
x = linspace(xStart,xStop,J)'; 
dt = 0.05; time = 0.0; tStop = 5.0;
alpha = 0.025; r = alpha*dt/dx^2;

f = zeros(J,2);   % initial condition f(:,1) - old; f(:,2) - new
plot(x,f); hold on; 
xlabel('x'), ylabel('f'), grid on
title '1D heat equation with the FTCS method'

while (time < tStop)
    f(1,:) = 1.0; f(J,:) = 0.0; % Boundary conditions
    f(2:J-1,2) = r*f(1:J-2,1) + (1-2*r)*f(2:J-1,1) + r*f(3:J,1);
    plot(x,f(:,2)); pause(0.1);
    time = time + dt;
    f(:,1) = f(:,2);
end

 