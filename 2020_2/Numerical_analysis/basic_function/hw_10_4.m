function HW10_2_1

xStart = 0.0; xStop = 1.0; 
dx = 0.1;
J = (xStop-xStart)/dx+1;
x = linspace(xStart,xStop,J)';

yStart = 0.0; yStop = 1.0; 
dy = 0.1;
y = linspace(yStart,yStop,J)'; 

time = 0.0; tStop = 1.0;
dt = 0.025; 
alpha = 0.1; r = alpha*dt/(dx^2);
S=1.0;

f = zeros(J,J,2);   % initial condition f(:,1) - old; f(:,2) - new

    while (time < tStop)
        f(1,:) = 0;
        f(:,1) = 0;
        f(:,J) = 0;
        f(J,2:J-1,2) = (1-4*r)*f(J,2:J-1,1) + r*( 2*f(J-1,2:J-1,1) + f(J,3:J,1) + f(J,1:J-2) ) + S*dt;
        
        for i = 2:J-1
            for j =  2:J-1
               f(i,j,2)=(1-4*r)*f(i,j,1) + r*( f(i+1,j,1) + f(i-1,j,1) + f(i,j+1,1) + f(i,j-1,1) ) + S*dt;
            end
        end
        

        time = time + dt;
        f(:,:,1) = f(:,:,2);
    end
surf(y,x,f(:,:,2));
xlabel("y"); ylabel("x"); zlabel("f");

end