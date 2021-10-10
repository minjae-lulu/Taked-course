% hw7_1_18b



F = inline('[y(2) y(1)*cos(2*x)]','x','y');
x = 0; xStop = 20; h = 0.5;
y = [0 1]; %initial value

yOld = 0;

while 1
    [xSol, ySol] = runKut4(F,x,y,xStop,h);
    yNew = ySol(size(ySol,1));
    if abs(yNew - yOld) < 1.0e-4; break
    else; h = h/2; yOld = yNew; end
end
h
plot(xSol,ySol(:,1),'r--'); grid on
xlabel('x'); ylabel('y')
