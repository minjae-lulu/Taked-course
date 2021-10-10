function example7_11 
[xSol,ySol] = bulStoer(@dEqs,0,[0 0],10,0.25);
plot(xSol,ySol(:,2),'k-o')
grid on
xlabel('time (s)'); ylabel('current (A)')

   function F = dEqs(x,y)
   F = zeros(1,2);
   F(1) = y(2);
   F(2) = (-y(2)-y(1)/0.45 + 9.0)/2;
   end % function dEqs

end % function example7_11