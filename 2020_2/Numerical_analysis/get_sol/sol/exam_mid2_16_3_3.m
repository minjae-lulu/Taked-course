function exam_mid2_16_3_3
clear all
clc

dt=0.1;
n=50/0.1;
tStart=0;tEnd=50;
t = linspace(tStart,tEnd,n+1);
y=[1100 120];
alpha=1; beta=0.01; gamma=1; eta=0.001;
root=zeros(n+1,2);
root(1,:)=y;
a=1;


    function f=func(x)
        f=zeros(2,1);
        f(1)=x(1)-y(1)+dt*alpha*x(1)+dt*beta*x(1)*x(2);
        f(2)=x(2)-y(2)-dt*gamma*x(2)+dt*eta*x(1)*x(2);
    end

for i=1:0.1:50   
    root_temp=newtonRaphson2(@func,y);
    root(a+1,:)=root_temp;
    y=root(a+1,:);
    a=a+1;
end
root
plot(t,root)
end