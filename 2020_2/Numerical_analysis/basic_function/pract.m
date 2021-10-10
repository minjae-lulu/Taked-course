%Problem3
function Problem_3

clear all
clc

xStart=-1; xEnd=1;
yStart=-1; yEnd=1;
t_length=1.5;
I=41; J=41;
dt=0.05;
A=1.0;
sigma=0.15;
u=0.5;
v=0.5;

dx=(xEnd-xStart)/I;
dy=(yEnd-yStart)/I;
tk=t_length/dt;

x=linspace(xStart,xEnd,I);
y=linspace(yStart,yEnd,J);


phi_sol=zeros(I,J,tk);

for i=1:I
    for j=1:J
        phi_sol(i,j,1)=A*exp(-((x(i)+0.5)^2+(y(j)+0.5)^2)/(2*sigma^2));
    end
end
phi_sol(1,:,:)=0;
phi_sol(I,:,:)=0;
phi_sol(:,1,:)=0;
phi_sol(:,J,:)=0;


c1=-u*dt/(4*dx)*ones(I-1,1);
c1(I-1)=0;
d1=ones(I,1);
e1=u*dt/(4*dx)*ones(I-1,1);
e1(1)=0;
[c1,d1,e1]=LUdec3(c1,d1,e1);

c2=-v*dt/(4*dy)*ones(J-1,1);
c2(J-1)=0;
d2=ones(J,1);
e2=v*dt/(4*dy)*ones(J-1,1);
e2(1)=0;
[c2,d2,e2]=LUdec3(c2,d2,e2);

bx=zeros(I,1);
by=zeros(J,1);

phi_star=zeros(I,J);


for k=2:tk
    for j=2:J-1
        for i=2:I-1
            bx(i)=phi_sol(i,j,k-1)-v*dt/2*((phi_sol(i,j+1,k-1)-phi_sol(i,j-1,k-1))/(2*dy));
        end
        phi_star(:,j)=LUsol3(c1,d1,e1,bx);
    end
        
    for i=2:I-1
        for j=2:J-1
            by(j)=phi_star(i,j)-u*dt/2*((phi_star(i+1,j)-phi_star(i-1,j))/(2*dx));
        end
        phi_sol(i,:,k)=LUsol3(c2,d2,e2,by);
    end
end

surf(x,y,phi_sol(:,:,tk))
hold on
title('Temperature'),xlabel('x'),ylabel('y'),zlabel('T'),colorbar



