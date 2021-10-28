function Problem_1
clear all; clc;

xStart = -1; xStop = 1; I = 41; dx =(xStop-xStart)/(I-1);
yStart = -1; yStop = 1; J = 41; dy =(yStop-yStart)/(J-1);
x = linspace(xStart,xStop,I)';
y = linspace(yStart,yStop,J)';
time = 0; tStop = 1.5; dt=0.05;
u=0.5; v=0.5; A=1.0; sigma=0.15;

time_k=(tStop-time)/dt;

f_sol=zeros(I,J,time_k);

for i=1:I
    for j=1:J
        f_sol(i,j,1)=A*exp(-((x(i)+0.5)^2+(y(j)+0.5)^2)/(2*sigma^2));
    end
end

f_sol(1,:,:)=0; f_sol(I,:,:)=0; f_sol(:,1,:)=0; f_sol(:,J,:)=0;

rx = u*dt/(4*dx); ry = v*dt/(4*dy);

c_x = -rx*ones(I-1,1); c_x(I-1)=0;
d_x = ones(I,1);
e_x = rx*ones(I-1,1); e_x(1)=0;
c_y = -ry*ones(J-1,1); c_y(J-1)=0;
d_y = ones(J,1);
e_y = ry*ones(J-1,1); e_y(1)=0;

[c_x,d_x,e_x]=LUdec3(c_x,d_x,e_x);
[c_y,d_y,e_y]=LUdec3(c_y,d_y,e_y);

b_x=zeros(I,1);
b_y=zeros(J,1);

f_star=zeros(I,J);

for k=2:time_k
    for j=2:J-1
        for i=2:I-1
            b_x(i)=f_sol(i,j,k-1)-ry*((f_sol(i,j+1,k-1)-f_sol(i,j-1,k-1)));
        end
        f_star(:,j)=LUsol3(c_x,d_x,e_x,b_x);
    end
        
    for i=2:I-1
        for j=2:J-1
            b_y(j)=f_star(i,j)-rx*((f_star(i+1,j)-f_star(i-1,j)));
        end
        f_sol(i,:,k)=LUsol3(c_y,d_y,e_y,b_y);
    end
end

surf(x,y,f_sol(:,:,time_k)); hold on;
xlabel('x'),ylabel('y'),zlabel('T')
end

