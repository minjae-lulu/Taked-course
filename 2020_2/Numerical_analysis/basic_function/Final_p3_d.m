function Problme3_d
clear all; clc;

xStart = 0.0; xStop = 1.0; I = 21; dx =(xStop-xStart)/(I-1);
yStart = 0.0; yStop = 1.0; J = 21; dy =(yStop-yStart)/(J-1);
x = linspace(xStart,xStop,I)';
y = linspace(yStart,yStop,J)';
h=dx; tol = 1e-6; beta = 1.5;

T=zeros(I,J);
T(:,1)=sin(pi*x);
T(:,J)=sin(pi*x)*exp(-pi);
T(1,:)=0; T(I,:)=0;

c=-(1/4)*ones(I-1,1); c(I-1)=0;
d=ones(I,1);
e=-(1/4)*ones(I-1,1); e(1)=0;
b=zeros(I,1);
[c,d,e]=LUdec3(c,d,e);

err = 1; 
while err > tol
    T_old = T;
    for j = 2:J-1
        for i = 2:I-1
            b(i)=1/4*(T(i,j-1)+T_old(i,j+1));
        end
        T(:,j)=LUsol3(c,d,e,b);
        T(:,j)=beta*T(:,j)+(1-beta)*T_old(:,j);
    end
err = max(max(abs((T(:,:)-T_old(:,:))./T_old(:,:))));
end

surf(x,y,T');
xlabel('x'),ylabel('y'),zlabel('T')
end