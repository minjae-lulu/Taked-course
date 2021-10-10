function my_hw10_AFM
clear; clc;
% 2-D heat equation using the ADI method
xStart = 0.0; xStop = 1.0; I = 21; dx =(xStop-xStart)/(I-1);
yStart = 0.0; yStop = 1.0; J = 21; dy =(yStop-yStart)/(J-1);
x = linspace(xStart,xStop,I)';
y = linspace(yStart,yStop,J)';

dt = 0.05; time = 0.0; tStop = 1.0;
alpha = 0.1; S = 1.0*ones(I,J);
rx = alpha*dt/dx^2/2; ry= alpha*dt/dy^2/2;


cx = -rx*ones(I-1,1); 
cx(I-1) = 0;  
dx = (1+2*rx)*ones(I,1); 
dx(1) = 1; 
dx(I)=1; 
ex = -rx*ones(I-1,1); 
ex(1) = 0;
bx = zeros(I,1); 
[cx,dx,ex] = LUdec3(cx,dx,ex);


cy = -ry*ones(J-1,1); 
cy(J-1) = 0;
dy = (1+2*ry)*ones(J,1); 
dy(J) = 1;
ey = -ry*ones(J-1,1); 
ey(1) = -2*ry;
by = zeros(J,1);
[cy,dy,ey] = LUdec3(cy,dy,ey);

f = zeros(I,J,2); % initialization of solution
fs= zeros(I,J);
R = zeros(I,J);
g = zeros(I,J);

while (time < tStop)

    for i = 1:I
       g(i,1)=(1-2*ry)*f(i,1,2)+2*ry*f(i,2,2);
       for j = 2:J-1
           g(i,j)=ry*f(i,j-1,2)+(1-2*ry)*f(i,j,2)+ry*f(i,j+1,2);
       end
    end

    for i= 2:I-1
        for j=1:J
            R(i,j)=rx*g(i-1,j)+(1-2*rx)*g(i,j)+rx*g(i+1,j)+S(i,j)*dt;
        
        end

    end
    
    
    for j = 1:J-1
        bx(1)=0;
        bx(2:I-1) = R(2:I-1,j);
        bx(I)=0;
        fs(:,j) = LUsol3(cx,dx,ex,bx);
    end
    
    for i = 2:I-1
        by(1:J-1) = fs(i,1:J-1);
        by(J)=0;
       
        f(i,:,1) = LUsol3(cy,dy,ey,by);
    end

    f(:,:,2)=f(:,:,1);
    time = time + dt;
surf(y,x,f(:,:,1));  
xlabel('y'), ylabel('x'), zlabel('f');
pause(0.01);

end

surf(y,x,f(:,:,1));
xlabel('y'), ylabel('x'), zlabel('f'), grid on
title '2-D heat equation with ADI method'

end