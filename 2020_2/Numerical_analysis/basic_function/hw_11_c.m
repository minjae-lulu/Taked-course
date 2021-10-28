clear all; clc; 

xStart=0; xStop=1; I=21; dx = (xStop-xStart)/I-1; 
yStart=0; yStop=1; J=21; dy = (xStop-xStart)/J-1;
x = linspace(xStart, xStop, I);
y = linspace(yStart, yStop, J);

h = 1/(I-1);
w = 1.8; n=0; err=1; tol=1e-6; S = -10*ones(I,J);
T = zeros(I,J); T_old = zeros(I,J);

T(1,:)=0; T(I,:) =0; T(:,J)=0;

while err > tol
    T_old = T;
    
        for i = 2:I-1
           T(i,1) = 0.25*w*(T(i-1,1)+T_old(i+1,1) + 2*T(i,2) -h^2*S(i,1)) + (1-w)*T(i,1);
           for j = 2:J-1
              T(i,j) = 0.25*w*(T(i-1,j)+T_old(i+1,j)+T(i,j-1)+T_old(i,j+1) -h^2*S(i,j)) + (1-w)*T(i,j);
           end
        end
   
    err = max(max(abs((T(:,:)-T_old(:,:))./T(:,:))));
    hold off;
    surf(x,y,T);
    xlabel('y'); ylabel('x'); zlabel('T');
    pause(0.01);

    n = n+1;
end

