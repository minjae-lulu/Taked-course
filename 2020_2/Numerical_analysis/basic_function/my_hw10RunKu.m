function my_hw10RunKu

xStart = 0; xStop = 1; dx = 0.05; I = (xStop-xStart)/dx+1;
yStart = 0; yStop = 1; dy = 0.05; J = (yStop-yStart)/dy+1;
x = linspace(xStart,xStop,I)';
y = linspace(yStart,yStop,J)';

h = dx; time = 0.0; tStop = 1.0; dt = 0.005; a =0.1; 

S = 1.0*ones(I,J);
S = reshape(S,[I*J,1]);
T = zeros(I,J);
f = reshape(T,[I*J,1]);

[xSol,ySol] = runKut4(@dEqs,time,f,tStop,dt);

T = reshape(ySol(size(ySol,1),:),[I,J]);
surf(x,y,T');

function F = dEqs(t,f)
    F = zeros(1,I*J);
    
    for i = 2:I-1
        
        for j = 2:J-1
            F((j-1)*I+i) = a/(h^2)*(  f((j-1)*I+i-1) + f((j-1)*I+i+1) +f((j)*I+i) +f((j-2)*I+i) -4*f((j-1)*I+i) ) +S((j-1)*I+i);
        end
        
        for j =1
            F((j-1)*I+i) = a/(h^2)*(  f(i-1) + f(i+1) +f(I+i) +f(I+i) -4*f(i) ) +S(i);
        end
        
    end

end
end