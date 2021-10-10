clear all; clc

% (1) Constant
omega = 1.8; tol = 1e-6; I = 21; J = 21;
h = 1/(I-1); Iter = 0; err = 1;
fnew = zeros(I,J); f = zeros(I,J); ftemp = zeros(I,J);
S = -10*ones(I,J);
x = [0:h:1]; y = [0:h:1];

% (2) LUdec
c = (-1/4)*ones(I-1,1); c(I-1) = 0;
d = ones(I,1);
e = (-1/4)*ones(I-1,1); e(1) = 0;
[c,d,e] = LUdec3(c,d,e);
b = zeros(I,1);

% (3) Loop
while err > tol
    f = fnew;
    
    % For y = 0
    b(1) = 0;
    for i = 2:I-1
        b(i) = (1/4)*fnew(i,2) + (1/4)*f(i,2) - (h*h/4)*S(i,1);
    end
    b(I) = 0;
    ftemp(:,1) = LUsol3(c,d,e,b);
    fnew(:,1) = omega*ftemp(:,1) + (1-omega)*f(:,1);
    
    % For y = ~1
    for j = 2:J-1
        b(1) = 0;
        for i = 2:I-1
            b(i) = (1/4)*fnew(i,j-1) + (1/4)*f(i,j+1) - (h*h/4)*S(i,j);
        end
        b(I) = 0;
        
        ftemp(:,j) = LUsol3(c,d,e,b);
        fnew(:,j) = omega*ftemp(:,j) + (1-omega)*f(:,j);
    end
    fnew(:,J) = 0;
        
    err = max(max(abs((fnew(:,:)-f(:,:))./f(:,:)))); % Error
    hold off; 
    surf(x,y,fnew);
    xlabel('y'); ylabel('x'); zlabel('T')
    pause(0.01);
    
    Iter = Iter+1
end
