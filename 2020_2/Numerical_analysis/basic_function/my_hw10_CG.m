function my_hw10_CG
L = 1; M = 1;  dx = 0.05; dy = 0.05; h = dx;
I = L/dx + 1; J = L/dy + 1; eps = 1e-06;
x = linspace(0,L,I); y = linspace(0,M,J); 
S = zeros(I,J); S(2:I-1,2:J-1)=-10; b = zeros(I*J,1); 
T = zeros(I,J);                        % Initial guess
 
temp = reshape(T,[I*J,1]);             % Convert 2D matrix into a column vector
b = h^2.*reshape(S,[I*J,1]);           
[temp,n] = conjGrad(@func,temp,b,eps); % Solve using CG method 
%[temp,flag,relres,n] = gmres(@func,b,20);

surf(x,y,reshape(temp,[I,J])); title('CG method'); display(n);
 
  function Av = func(v)                % Av function for CG method
     Av = v;
     for j = 2:J-1
        for i = 2:I-1
            Av((j-1)*I+i) = v((j-1)*I+i-1) + v((j-1)*I+i+1)... 
                          + v((j-2)*I+i) + v(j*I+i) - 4*v((j-1)*I+i);
        end
     end
  end 

end
