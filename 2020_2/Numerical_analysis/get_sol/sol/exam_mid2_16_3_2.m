function mid_16_2_3

tStart=0; tEnd=50;
h=0.1;
y=[1100 120];
alpha=1; beta=0.01; gamma=1; eta=0.001;


[tSol,ySol]=runKut4(@dEqs,tStart,y,tEnd,h);
printSol(tSol,ySol,1)
plot(tSol,ySol)
legend('y1','y2')

function F=dEqs(x,y)
    F=zeros(1,2);
    F(1)=alpha*y(1)-beta*y(1)*y(2);
    F(2)=-gamma*y(2)+eta*y(1)*y(2);
end

end