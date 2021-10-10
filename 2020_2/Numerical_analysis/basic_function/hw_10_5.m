function HW10_2_2
clear; clc;
% 2-D heat equation using the ADI method
xStart = 0.0; xStop = 1.0; I = 11; dx =(xStop-xStart)/(I-1);
yStart = 0.0; yStop = 1.0; J = 11; dy =(yStop-yStart)/(J-1);
x = linspace(xStart,xStop,I)';
y = linspace(yStart,yStop,J)';
dt = 0.025; time = 0.0; tStop = 1.0;
alpha = 0.1; S = 1.0*ones(I,J);
rx = alpha*dt/dx^2/2; ry= alpha*dt/dy^2/2;

cx = -rx*ones(I-1,1); cx(I-1) = -2*rx;  
dx = (1+2*rx)*ones(I,1); dx(1) = 1; 
ex = -rx*ones(I-1,1); ex(1) = 0;
cy = -ry*ones(J-1,1); cy(J-1) = 0;
dy = (1+2*ry)*ones(J,1); dy(1) = 1; dy(J) = 1;
ey = -ry*ones(J-1,1); ey(1) = 0;

bx = zeros(I,1); 
by = zeros(J,1);

[cy,dy,ey] = LUdec3(cy,dy,ey);
[cx,dx,ex] = LUdec3(cx,dx,ex);

T = zeros(I,J,2); % initialization of solution

while (time < tStop)
       
    for i = 2:I-1
        by(1) = 0.0; by(J) = 0.0;
        by(2:J-1) = rx*T(i-1,2:J-1,2)+(1-2*rx)*T(i,2:J-1,2) ...
                  + rx*T(i+1,2:J-1,2)+dt*S(i,2:J-1)/2;
        T(i,:,1) = LUsol3(cy,dy,ey,by);
    end
    T(1,:,1)=0;
    
    by(1) = 0.0; by(J) = 0.0;
    by(2:J-1) = 2*rx*T(I-1,2:J-1,2)+(1-2*rx)*T(I,2:J-1,2) +dt*S(I,2:J-1)/2;
    T(I,:,1) = LUsol3(cy,dy,ey,by);    
    
    for j = 2:J-1
        bx(1) = 0.0; 
        bx(2:I) = ry*T(2:I,j-1,1)+(1-2*ry)*T(2:I,j,1) ...
                  + ry*T(2:I,j+1,1)+dt*S(2:I,j)/2;
        T(:,j,2) = LUsol3(cx,dx,ex,bx);
    end
    
    T(:,1,2)=0;
    T(:,J,2)=0;
    
    
    time = time + dt;
    
    surf(y,x,T(:,:,1));
    xlabel('y'), ylabel('x'), zlabel('f'), grid on
    title '2-D heat equation with ADI method'
end

end