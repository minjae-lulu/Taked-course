%Problem1
function problem_1

clear all
clc

xStart=0; xEnd=1;
n=101; phi=0.6; gamma=40; beta=0.2;
x=linspace(xStart,xEnd,n);

h=(xEnd-xStart)/n;
h2=h*h;
y1=1.0*ones(n,1);
y2=0.6*ones(n,1);
y3=zeros(n,1);

y1=newtonRaphson2(@residual1,y1,1.0e-8);
y2=newtonRaphson2(@residual2,y2,1.0e-8);
y3=newtonRaphson2(@residual3,y3,1.0e-8);

plot(x,y1); hold on; grid on;
plot(x,y2)
plot(x,y3)
legend('y=1.0','y=0.6','y=0')

    function r=residual1(y1)
    r=zeros(n,1);
    r(1)=6*(y1(2)-y1(1))/h2-phi^2*y1(1)*exp((gamma*beta*(1-y1(1)))/(1+beta*(1-y1(1))));
    for i=2:n-1
        r(i)=3*(y1(i+1)-2*y1(i)+y1(i-1))/h2...
            -phi^2*y1(i)*exp((gamma*beta*(1-y1(i)))/1+beta*(1-y1(i)));
    end
    r(n)=y1(n)-1;
    end

    function r=residual2(y2)
    r=zeros(n,1);
    r(1)=6*(y2(2)-y2(1))/h2-phi^2*y2(1)*exp((gamma*beta*(1-y2(1)))/(1+beta*(1-y2(1))));
    for i=2:n-1
        r(i)=3*(y2(i+1)-2*y2(i)+y2(i-1))/h2...
            -phi^2*y2(i)*exp((gamma*beta*(1-y2(i)))/1+beta*(1-y2(i)));
    end
    r(n)=y2(n)-1;
    end
    function r=residual3(y3)
    r=zeros(n,1);
    r(1)=6*(y3(2)-y3(1))/h2-phi^2*y3(1)*exp((gamma*beta*(1-y3(1)))/(1+beta*(1-y3(1))));
    for i=2:n-1
        r(i)=3*(y3(i+1)-2*y3(i)+y3(i-1))/h2...
            -phi^2*y3(i)*exp((gamma*beta*(1-y3(i)))/1+beta*(1-y3(i)));
    end
    r(n)=y3(n)-1;
    end
end


