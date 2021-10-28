function Problme3_e
clear all; clc;

xStart = 0.0; xStop = 1.0; I = 21; dx =(xStop-xStart)/(I-1);
yStart = 0.0; yStop = 1.0; J = 21; dy =(yStop-yStart)/(J-1);
x = linspace(xStart,xStop,I)';
y = linspace(yStart,yStop,J)';
h=dx; tol = 1e-6;

T=zeros(I,J);
T(:,1)=sin(pi*x);
T(:,J)=sin(pi*x)*exp(-pi);
T(1,:)=0; T(I,:)=0;

c=-(1/4)*ones(I,1);
d=ones(I,1);
e=-(1/4)*ones(I,1);
[c,d,e]=LUdec3(c,d,e);
b=zeros(I,1);

ini=zeros(I,1);

err = 1; n = 0;
while err > tol
    T_old = T;
    for j = 2:J-1
        for i = 2:I-1
            b(i)=1/4*(T(i,j-1)+T_old(i,j+1));
        end
        T(:,j)=conjGrad(@Iter,ini,b);
    end
    
n = n + 1;
err = max(max(abs((T(:,:)-T_old(:,:))./T_old(:,:))));
end

surf(x,y,T');
xlabel('x'),ylabel('y'),zlabel('T')

    function Av=Iter(v)
        Av=zeros(I,1);
        Av(1)=v(1);
        Av(2:I-1)=-1/4*v(1:I-2)+v(2:I-1)-1/4*v(3:I);
        Av(I)=v(I);
    end
end

