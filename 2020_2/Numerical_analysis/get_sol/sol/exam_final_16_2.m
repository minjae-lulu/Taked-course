function exam_final_16_3

clc
clear all

xStart=-5; xEnd=5;
n=101;
freq=1;
x=linspace(xStart,xEnd,n);
y=zeros(3*n,1);
for i=1:n
    y(i)=0.2+2.0*exp(-8*x(i)^2);
end

for i=1:n
    if i<1/2*(n+1)
        y(i+n)=-x(i)/5;
    else
        y(i+n)=0;
    end
end

for i=1:n
    if i<=1/2*(n+1)
        y(i+2*n)=0;
    else
        y(i+2*n)=x(i)/5;
    end
end

q=1.2; a_x=0.36; a_y=1.0; T_a=8; Le_x=1.0; Le_y=0.3; D=4.0*10^5;


h=(xEnd-xStart)/n;
y=newtonRaphson2(@residual,y);
plot(x,y(1:n)); hold on; grid on
plot(x,y(n+1:2*n))
plot(x,y(2*n+1:3*n))
legend('T','X','Y')

    function r=residual(y)
        r=zeros(3*n,1);
        r(1)=y(1)-0.2; r(n)=y(n)-0.2;
        r(n+1)=y(n+1)-1; r(2*n)=y(2*n)-0;
        r(2*n+1)=y(2*n+1)-0;r(3*n)=y(3*n)-1;
        
        for i = 2:n-1
            r(i)=(y(i+1)-2*y(i)+y(i-1))/(h*h)...
                +x(i)*(y(i+1)-y(i-1))/(2*h)+q*D*y(i+n)*y(i+2*n)*exp(-T_a/y(i));
        end
        
        for i = 2:n-1
            r(i+n)=1/Le_x*(y(i+n+1)-2*y(i+n)+y(i+n-1))/(h*h)...
                +x(i)*(y(i+n+1)-y(i+n-1))/(2*h)-a_x*D*y(i+n)*y(i+2*n)*exp(-T_a/y(i));
        end
        
        for i = 2:n-1
            r(i+2*n)=(y(i+2*n+1)-2*y(i+2*n)+y(i+2*n-1))/(h*h)/Le_y...
                +x(i)*(y(i+2*n+1)-y(i+2*n-1))/(2*h)-a_y*D*y(i+n)*y(i+2*n)*exp(-T_a/y(i));
        end
    end

end











