function ADI_method
clear; clc;
% 2-D heat equation using the ADI method
xStart = 0.0; xStop = 1.0; I = 21; dx =(xStop-xStart)/(I-1);
yStart = 0.0; yStop = 1.0; J = 21; dy =(yStop-yStart)/(J-1);
x = linspace(xStart,xStop,I)';
y = linspace(yStart,yStop,J)';
dt = 0.05; time = 0.0; tStop = 3.0;
alpha = 0.1; S = 1.0*ones(I,J);
rx = alpha*dt/dx^2/2; ry= alpha*dt/dy^2/2;

% Define cx, dx, ex for LU decomposition
cx = -rx*ones(I-1,1); cx(I-1) = 0;  
dx = (1+2*rx)*ones(I,1); dx(1) = 1; dx(I) = 1; 
ex = -rx*ones(I-1,1); ex(1) = 0;
bx = zeros(I,1); 
[cx,dx,ex] = LUdec3(cx,dx,ex);

% Define cy, dy, ey for LU decomposition
cy = -ry*ones(J-1,1); cy(J-1) = 0;
dy = (1+2*ry)*ones(J,1); dy(1) = 1; dy(J) = 1;
ey = -ry*ones(J-1,1); ey(1) = 0;
by = zeros(J,1);
[cy,dy,ey] = LUdec3(cy,dy,ey);

f = zeros(I,J,2); % initialization of solution

while (time < tStop)
    
    for j = 2:J-1
        bx(1) = 0.0; bx(I) = 0.0;
        bx(2:I-1) = ry*f(2:I-1,j-1,1)+(1-2*ry)*f(2:I-1,j,1) ...
                  + ry*f(2:I-1,j+1,1)+dt*S(2:I-1,j)/2;
        f(:,j,2) = LUsol3(cx,dx,ex,bx);
    end
    
    for i = 2:I-1
        by(1) = 0.0; by(J) = 0.0;
        by(2:J-1) = rx*f(i-1,2:J-1,2)+(1-2*rx)*f(i,2:J-1,2) ...
                  + rx*f(i+1,2:J-1,2)+dt*S(i,2:J-1)/2;
        f(i,:,1) = LUsol3(cy,dy,ey,by);
    end
    
    time = time + dt;
    surf(x,y,f(:,:,1)); zlim([0.0 0.8]); pause(0.1);
end

xlabel('y'); ylabel('x'); zlabel('f');
title '2-D heat equation with ADI method';

figure(1)
surf(y,x,f(:,:,1));
xlabel('y'), ylabel('x'), zlabel('f'), grid on
title '2-D heat equation with ADI method'

figure(2)
plot(x,f(:,1:11,1), 'r+-');hold on;
plot(x,f(:,11:21,1), 'ko-')
xlabel('x'), ylabel('f'), grid on
title '2-D heat equation with ADI method'

end
