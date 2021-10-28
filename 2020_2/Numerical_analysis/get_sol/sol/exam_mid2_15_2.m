function exam_mid2_15_2

xStart=0; xStop=pi; h=0.005*pi; y=[1 0];

function F=dEqs(x,y)
    F=zeros(1,2);
    F(1)=y(2);
    F(2)=-y(1);
end

[xSol,ySol]=runKut4(@dEqs,xStart,y,xStop,h);
plot(xSol,ySol)
legend('y1','y2')
grid on

end