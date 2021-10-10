%Problem4_d
function Problme_4_d

clear all
clc

xStart=0; xEnd=1;
yStart=0; yEnd=1;
I=21; J=21;
dx=(xStart-xEnd)/(I-1); dy=(yStart-yEnd)/(J-1);
h=dx;

x=linspace(xStart,xEnd,I);
y=linspace(yStart,yEnd,J);

T_sol=zeros(I,J);
T_sol(:,1)=sin(pi*x);
T_sol(:,J)=sin(pi*x)*exp(-pi);
T_sol(1,:)=0;
T_sol(I,:)=0;

c=-(1/4)*ones(I-1,1);
c(I-1)=0;
d=ones(I,1);
e=-(1/4)*ones(I-1,1);
e(1)=0;
[c,d,e]=LUdec3(c,d,e);
b=zeros(I,1);

err = 1; eps = 1e-6; beta = 1.5;
while err > eps
    T_sol_old = T_sol;
    for j = 2:J-1
        for i = 2:I-1
            b(i)=1/4*(T_sol(i,j-1)+T_sol_old(i,j+1));
        end
        T_sol(:,j)=LUsol3(c,d,e,b);
        T_sol(:,j)=beta*T_sol(:,j)+(1-beta)*T_sol_old(:,j);
    end
err = max(max(abs((T_sol(:,:)-T_sol_old(:,:))./T_sol_old(:,:)))); % Error evaluation
end

surf(x,y,T_sol');
title('Laplace solution'),xlabel('x'),ylabel('y'),zlabel('T'),colorbar
end