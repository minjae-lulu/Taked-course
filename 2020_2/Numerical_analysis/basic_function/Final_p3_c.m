function Problem3_c
clear all; clc;

xStart = 0.0; xStop = 1.0; I = 21; dx =(xStop-xStart)/(I-1);
yStart = 0.0; yStop = 1.0; J = 21; dy =(yStop-yStart)/(J-1);
x = linspace(xStart,xStop,I)';
y = linspace(yStart,yStop,J)';
h=dx; 
tol = 1e-6; beta = 1.5;

T=zeros(I,J);
T(:,1)=sin(pi*x);
T(:,J)=sin(pi*x)*exp(-pi);
T(1,:)=0; T(I,:)=0;

err = 1; n = 0;
while err > tol
    T_old = T;
    for j = 2:J-1
        for i = 2:I-1
            T(i,j) = 0.25*beta*(T(i-1,j)+T(i+1,j)+T(i,j-1)+T(i,j+1))+(1-beta)*T(i,j);
        end
    end
n = n + 1;
err = max(max(abs((T(:,:)-T_old(:,:))./T_old(:,:))));
end

surf(x,y,T');
xlabel('x'),ylabel('y'),zlabel('T')
end