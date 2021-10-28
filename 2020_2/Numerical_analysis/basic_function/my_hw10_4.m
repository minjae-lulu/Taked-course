function my_hw10_4
clear; clc;
xStart = 0; xStop = 1; dx = 0.1; I = (xStop-xStart)/dx+1;
yStart = 0; yStop = 1; dy = 0.1; J = (yStop-yStart)/dy+1;
x = linspace(xStart,xStop,I);
y = linspace(yStart,yStop,J);

S = 1;  time=0; tStop=1; h = 0.1; dt = 0.025;
alpha = 0.1; r = alpha*dt/(dx^2);

T = zeros(I,J,2); %f(:,1) - old; f(:,2) - new

while (time < tStop)
    T(:,:,1)=T(:,:,2);
    T(1,:,2) = 0; T(:,1,2) = 0; T(:,J,2) = 0;
    
    for j = 2:J-1
        T(I,j,2) = (1-4*r)*T(I,j,1) +r*(2*T(I-1,j,1)+T(I,j+1,1)+T(I,j-1,1)) + dt*S;    
    end
    for i = 2:I-1
        for j = 2:J-1
            T(i,j,2) = (1-4*r)*T(i,j,1) +r*(T(i+1,j,1)+T(i-1,j,1)+T(i,j+1,1)+T(i,j-1,1)) + dt*S;            
        end
    end
    time = time + dt;
    
    surf(y,x,T(:,:,2));
    xlabel('y'), ylabel('x'), zlabel('T'), grid on
    pause(0.01);
    
end
end
