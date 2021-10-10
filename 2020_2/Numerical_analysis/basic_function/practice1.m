%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%% HW#7 Poisson equation %%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

L = 1.0; M = 1.0; h = 0.05; I = L/h +1; J = L/h +1; 
x = [0:h:L]; y = [0:h:M];           % Grid generation
T = zeros(I,J); S = -10*ones(I,J);  % Initialization
T(I,:) = 0.0; T(:,1) = 0.0; T(1,:) = 0.0;  % BC’s
err = 1; eps = 1e-6; omega = 1.8; n = 0;

while err > eps
    n
T_old = T;
T_temp = zeros(I,J);
c = -0.25*ones(I-1,1); c(I-1) = -0.5;
d = ones(I,1);
e = -0.25*ones(I-1,1); e(1) = 0;
[c,d,e] = LUdec3(c,d,e);
b = zeros(I,1);

for j = 2:J-1
    for i = 2:I-1
        b(i) = 0.25*(T(i,j+1)+T(i,j-1)-S(i,j)*h^2);
        b(I) = 0.25*(T(I,j-1)+T(I,j+1)-S(I,j)*h^2);
    end
    T_temp(:,j) = LUsol3(c,d,e,b);
    T(:,j) = omega*T_temp(:,j)+(1-omega)*T(:,j);
end   
n = n + 1;
err = max(max(abs((T(:,:)-T_old(:,:))./T_old(:,:)))); % Error evaluation
hold off; surf(x,y,T); ylabel('x'); xlabel('y'); zlim([0 1.5]); pause(0.01);
end
% surf(x,y,T);

