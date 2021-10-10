function exam_final_16_4

clear all
clc

xStart=0; xEnd=1;
yStart=0; yEnd=1;
h=0.05;
dt=0.05;
tEnd=1;
alpha=0.1;

nx=(xEnd-xStart)/h+1;
ny=(yEnd-yStart)/h+1;
x=linspace(xStart,xEnd,nx);
y=linspace(yStart,yEnd,ny);
nt=tEnd/dt+1;
r=alpha*dt/(2*h^2);
S=0.2*ones(nx,1);

T_sol=zeros(ny,nx,nt);
T_sol(:,:,1)=0;
T_sol(1,:,:)=0;
T_sol(ny,:,:)=0;
T_sol(:,1,:)=1;

cx=-r*ones(nx-1,1); dx=(1+2*r)*ones(nx,1); ex=-r*ones(nx-1,1);
cx(nx-1)=-2*r;dx(1)=1;ex(1)=0;
[cx,dx,ex]=LUdec3(cx,dx,ex);

cy=-r*ones(ny-1,1); dy=(1+2*r)*ones(ny,1); ey=-r*ones(ny-1,1);
cy(ny-1)=0;dy(1)=1;dy(ny)=1;ey(1)=0;
[cy,dy,ey]=LUdec3(cy,dy,ey);

bx=zeros(nx,1);
bx(1)=1;
by=zeros(ny,1);
by(1)=0;
by(ny)=0;

for k=2:nt
    for j=2:ny-1
        for i=2:nx
            bx(i)=r*T_sol(j+1,i,k-1)+(1-2*r)*T_sol(j,i,k-1)+r*T_sol(j-1,i,k-1)+dt/2*S(i);
        end
        
        T_temp(j,:)=LUsol3(cx,dx,ex,bx);
        
    end

    for i=2:nx-1
        for j=2:ny-1
            by(j)=r*T_temp(j,i+1)+(1-2*r)*T_temp(j,i)+r*T_temp(j,i-1)+dt/2*S(j);
        end
        
        T_sol(:,i,k)=LUsol3(cy,dy,ey,by);
    end
end
surf(x,y,T_sol(:,:,nt))
end

