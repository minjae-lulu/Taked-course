%hw6_1_10


% sinx = t^2  =>  cosx*dx = 2t*dt  =>  using 1 = (sinx)^2 + (cosx)^2  
% express => sqrt((1 - (sinx)^2))*dx = 2t*dt => dx/sqrt(sinx) = 2t/sqrt(1-t^4)*dt


f = inline('2*x/sqrt(1-x^4)','x');
a=0; b = 2^-0.25;
k = romberg(f,a,b);
