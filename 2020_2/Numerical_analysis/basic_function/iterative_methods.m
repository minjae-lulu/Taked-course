function iterative_methods
% 2D Poisson equation
clear all; clc;
L = 1.0; M = 1.0; I = 21; J=21; dx = L/(I-1); dy = M/(J-1); h = dx;
x = [0:dx:L]; y = [0:dy:M]; % Grid generation
temp = zeros(I,J); S = -2*ones(I,J);  % Initialization
temp(1,:) = 0.0; temp(I,:) = 0.0;temp(:,1) = 0.0; temp(:,J) = 0.0;  % BC¡¯s
err = 1; eps = 1e-6; beta = 1.5; n = 0;
while err > eps
    temp_old = temp;
    for j = 2:J-1
        for i = 2:I-1
            temp(i,j) = 0.25*(temp_old(i-1,j)+temp(i+1,j)+temp_old(i,j-1)+temp(i,j+1)-h^2*S(i,j));    % Jacobi method
          % temp(i,j) = 0.25*(temp(i-1,j)+temp(i+1,j)+temp(i,j-1)+temp(i,j+1)-h^2*S(i,j));                % GS method
          % temp(i,j) = 0.25*beta*(temp(i-1,j)+temp(i+1,j)+temp(i,j-1)+temp(i,j+1)-h^2*S(i,j))...     % SOR method
          %              + (1-beta)*temp(i,j);                                                                                         
        end
    end
    n = n + 1;
    err = max(max(abs((temp(:,:)-temp_old(:,:))./temp_old(:,:)))); % Error evaluation
end
surf(x,y,temp);
