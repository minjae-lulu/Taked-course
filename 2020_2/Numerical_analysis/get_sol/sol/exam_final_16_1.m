function exam_final_16_1
clc
clear all

xStart=0; xEnd=10;
h=0.1;
n=(xEnd-xStart)/h+1;
u=[0.55 -0.65];
freq=1;

x=xStart;
u=newtonRaphson2(@residual,u);
[xSol,ySol]=runKut4(@dEqs,x,inCond(u),xEnd,h);
printSol(xSol,ySol,freq)
plot(xSol,ySol(:,1:3))
legend('F','G','H')
grid on

    function F=dEqs(x,y)
        F=zeros(1,5);
        F(1)=y(4);
        F(2)=y(5);
        F(3)=-2*y(1);
        F(4)=(y(1))^2-(y(2))^2+y(3)*y(4);
        F(5)=2*y(1)*y(2)+y(3)*y(5);
    end

    function y = inCond(u)
        y=[0 1 0 u(1) u(2)];
    end

    function r=residual(u)
        r = zeros(length(u),1);
        x=xStart;
        [xSol,ySol]=runKut4(@dEqs,x,inCond(u),xEnd,h);
        r(1)=ySol(size(ySol,1),1);
        r(2)=ySol(size(ySol,1),2);
    end
end
