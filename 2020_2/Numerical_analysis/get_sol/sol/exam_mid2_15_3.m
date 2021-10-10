function exam_mid2_15_3

xStart=0; xStop=1; h=0.01; freq=1;
u=[0 0.5];
Da=0.05; beta=1; gamma=20; lamda=0.5; theta=1; B=6;

x=xStart;
u=newtonRaphson2(@residual,u);
[xSol,ySol]=runKut4(@dEqs,x,inCond(u),xStop,h);
printSol(xSol,ySol,freq)
plot(xSol,ySol)

    function F=dEqs(x,y)
        F=zeros(1,2);
        F(1)=Da*(1-y(1))*exp((gamma*y(2))/(gamma+y(2)));
        F(2)=B*Da*(1-y(1))*exp((gamma*y(2))/(gamma+y(2)))...
            -beta*(y(2)-theta);
    end

    function y=inCond(u)
        y=[u(1) u(2)];
    end

    function r=residual(u)
        r=zeros(length(u),1);
        x=xStart;
        [xSol,ySol]=runKut4(@dEqs,x,inCond(u),xStop,h);
        r(1)=ySol(1,1)-(1-lamda)*ySol(size(ySol,1),1);
        r(2)=ySol(1,2)-(1-lamda)*ySol(size(ySol,1),2);
    end
end
