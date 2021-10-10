clear all; clc; 

xStart=0; xStop=1; I=21; dx = (xStop-xStart)/I-1; 
yStart=0; yStop=1; J=21; dy = (xStop-xStart)/J-1;
x = linspace(xStart, xStop, I);
y = linspace(yStart, yStop, J);

h = 1/(I-1);
w = 1.8; n=0; err=1; tol=1e-6; S = -10*ones(I,J);
T = zeros(I,J); T_old = zeros(I,J); T_w = zeros(I,J);

cx = -0.25*ones(I-1,1); cx(I-1) = 0;
dx = ones(I,1); 
ex = -0.25*ones(I-1,1); ex(1)= 0 ;
[cx,dx,ex] = LUdec3(cx,dx,ex);

T(1,:)=0; T(I,:) =0; T(:,J)=0;


A = zeros(I,J);
for i = 1:I
   A(i,i) = 1; 
end
for i = 1:I-1
   A(i,i+1) = -0.25;
   A(i+1,i) = -0.25;
end
A(I,I-1) = 0; A(1,2) = 0;
b = zeros(I,1);

while err> tol    
    T_old = T;
    
    b(1) =0; b(I) = 0;
    for i = 2:I-1
        b(i) = 0.25*(T(i,2) + T_old(i,2) - h^2*S(i,1));
    end
    T_w(:,1) = gmres(A,b);
    T(:,1) = w*T_w(:,1) + (1-w)*T_old(:,1);    
    
    for j = 2:J-1
        b(1) = 0; b(I) = 0;
        for i = 2:I-1
           b(i) = 0.25*(T(i,j-1) + T_old(i,j+1) - h^2*S(i,j));
        end
        
        T_w(:,j) = gmres(A,b);
        T(:,j) = w*T_w(:,j) + (1-w)*T_old(:,j);
    end
    
    err = min(min(abs((T(:,:)-T_old(:,:))./T(:,:))));
  
    n = n+1;
end
    hold off;
    surf(x,y,T);
    xlabel('y'); ylabel('x'); zlabel('T');
    pause(0.01); 