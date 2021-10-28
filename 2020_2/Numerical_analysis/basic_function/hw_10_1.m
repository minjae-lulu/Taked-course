function hw_10_1
% 1-D heat equation using the FTCS method
xStart = 0.0; xStop = 1.0; 
dx = 0.1; J = (xStop-xStart)/dx + 1;
x = linspace(xStart,xStop,J)'; 
dt = 0.05; time = 0.0; tStop = 1.0;
alpha = 0.1; r = alpha*dt/dx^2; S = 1.0;

f = zeros(J,2);   % initial condition f(:,1) - old; f(:,2) - new
plot(x,f(:,2)); %hold on; 
xlabel('x'), ylabel('f'), grid on
title '1D heat equation with the FTCS method'

while (time < tStop)
    f(1,2) = (1-2*r)*f(1,1) + 2*r*f(2,1)+S*dt; f(J,:) = 0.0; % Boundary conditions
    f(2:J-1,2) = r*f(1:J-2,1) + (1-2*r)*f(2:J-1,1) + r*f(3:J,1) + S*dt;
    %plot(x,f(:,2)); pause(0.1);
    time = time + dt;
    f(:,1) = f(:,2);
end
plot(x,f(:,2));
end

 