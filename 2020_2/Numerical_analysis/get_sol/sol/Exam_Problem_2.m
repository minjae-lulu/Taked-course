%Problem_2
function Problem_2

clear all
clc

xStart=-5; xEnd=5;
tStart=0; tEnd=3;
dt=0.001;

n=101;
h=(xEnd-xStart)/5;

x=linspace(xStart,xEnd,n);

q=1.2; ax=0.36; ay=1.0; Ta=8; Lex=1.0; Ley=0.3; D=4.0*10^5;

T=zeros(n,1);
T=0.2+5.0*exp(-8*x.^2);
X=zeros(n,1);
for i=1:n
    if i<n/2
        X(i)=-x(i)/5;
    else
        X(i)=0;
    end
end

Y=zeros(n,1);
for i=1:n
    if i<n/2
        Y(i)=0;
    else
        Y(i)=x(i)/5;
    end
end


y=zeros(3*n,1);
y(1:n)=T;
y(n+1:2*n)=X;
y(2*n+1:3*n)=Y;



[tSol,ySol]=runKut4(@dEqs,tStart,y,tEnd,dt);
plot(x,ySol(1:n)); hold on; grid on
plot(x,ySol(n+1:2*n))
plot(x,ySol(2*n+1:3*n))
legend('T','X','Y')

    function F=dEqs(t,y)
        F=zeros(1,3*n);
        F(1)=0.2; F(n)=0.2;
        F(n+1)=1; F(2*n)=0;
        F(2*n+1)=0; F(3*n)=1;
        for i=2:n-1
            F(i)=x(i)*(y(i+1)-y(i-1))/(2*h)+(y(i+1)-2*y(i)+y(i-1))/(h*h)...
                +q*D*y(i+n)*y(i+2*n)*exp(-Ta/y(i));
        end
        
        for i = 2:n-1
            F(i+n)=x(i)*(y(i+n+1)-y(i+n-1))/(2*h)+1/Lex*(y(i+n+1)-2*y(i+n)+y(i+n-1))/(h*h)...
                -ax*D*y(i+n)*y(i+2*n)*exp(-Ta/y(i));
        end
        
        for i = 2:n-1
            F(i+2*n)=x(i)*(y(i+2*n+1)-y(i+2*n-1))/(2*h)+1/Ley*(y(i+2*n+1)-2*y(i+2*n)+y(i+2*n-1))/(h*h)...
                -ay*D*y(i+n)*y(i+2*n)*exp(-Ta/y(i));
            
        end
    end
end
        
        
        
        
        
        
        
