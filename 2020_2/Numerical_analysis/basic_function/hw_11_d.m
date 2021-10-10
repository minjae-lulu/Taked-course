clear all; clc; 

xStart=0; xStop=1; I=21; dx = (xStop-xStart)/I-1; 
yStart=0; yStop=1; J=21; dy = (xStop-xStart)/J-1;
x = linspace(xStart, xStop, I);
y = linspace(yStart, yStop, J);

h = 1/(I-1);
w = 1.8; n=0; err=1; tol=1e-6; S = -10*ones(I,J);
T = zeros(I,J); T_old = zeros(I,J); T = zeros(I,J);

cx = -0.25*ones(I-1,1); cx(I-1) = 0;
dx = ones(I,1); 
ex = -0.25*ones(I-1,1); ex(1)= 0 ;
[cx,dx,ex] = LUdec3(cx,dx,ex);

T(1,:)=0; T(I,:) =0; T(:,J)=0;

while err > tol
    T_old = T;
    bx(1) =0; bx(I) = 0;
    for i = 2:I-1
        bx(i) = 0.25*(T(i,2) + T_old(i,2) - h^2*S(i,1));
    end
    T(:,1) = LUsol3(cx,dx,ex,bx);
    T(:,1) = w*T(:,1) + (1-w)*T_old(:,1);    
    
    for j = 2:J-1
        bx(1) = 0; bx(I) = 0;
        for i = 2:I-1
           bx(i) = 0.25*(T(i,j-1) + T_old(i,j+1) - h^2*S(i,j));
        end
        
        T(:,j) = LUsol3(cx,dx,ex,bx);
        T(:,j) = w*T(:,j) + (1-w)*T_old(:,j);
    end
    
    err = max(max(abs((T(:,:)-T_old(:,:))./T(:,:))));
    
 
    n =n+1;
end
    hold off;
    surf(x,y,T);
    xlabel('y'); ylabel('x'); zlabel('T');
    pause(0.01); 