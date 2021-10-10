% one-dimensional advection by the various schemes
clear;clc;
n=21; length=2.0; h=length/(n-1); dt=0.05; U=1.0; nstep=1.0/dt; 
x=zeros(n,1); f=zeros(n,1); y=zeros(n,1); ex=zeros(n,1); time=0.0;
for i=1:n
    f(i)=0.5*sin(2*pi*h*(i-1)); 
    x(i)=h*(i-1); 
end        % initial conditions

 for m=1:nstep
	for i=1:n
        ex(i)= 0.5*sin(2*pi*(h*(i-1)-U*time)); 
    end     % exact solution
	hold off; plot(x,f,'linewidt',2); axis([0 2 -2 2]);    % plot solution
	hold on; plot(x,ex,'k','linewidt',2);           % plot exaction solution
 	xlabel('x'); ylabel('f(x,t)');
	xlabel('x'); ylabel('f(x,t)'); 
    pause(0.05); % if you want to watch only final result, 
                 % then change this command to comment    
    y=f;
    % Refer to next three schemes
    
    % FTCS
% 	for i=2:n-1
% 		f(i)=y(i)-0.5*(U*dt/h)*(y(i+1)-y(i-1)); % advect by centered differences
%     end
% 	f(n)=y(n)-0.5 *(U*dt/h)*(y(2)-y(n-1));   % do endpoints for periodic b.c.
% 	f(1)=f(n);
% 
%     Upwind scheme
%     for i=2:n-1
% 		f(i)=y(i)-(U*dt/h)*(y(i)-y(i-1)); % advect by upwind
%     end
% 	f(n)=y(n)-(U*dt/h)*(y(n)-y(n-1));   % do endpoints for periodic b.c.
% 	f(1)=f(n);

    % Generalized Upwind Scheme
% 	for i=2:n-1
% 		f(i)=y(i)-0.5*(U*dt/h)*(y(i+1)-y(i-1))...
%             +abs(U)*dt/2/h*(y(i+1)-2*y(i)+y(i-1)); % advect by generalized upwind
%     end
% 	f(n)=y(n)-0.5 *(U*dt/h)*(y(2)-y(n-1))...
%         +abs(U)*dt/2/h*(y(2)-2*y(n)+y(n-1));       % do endpoints for periodic b.c.
% 	f(1)=f(n);

    %------------ Write down your code ----------------
    
    % (Lax-friedrichs) method
    for i=2:n-1
		f(i)= 0.5* (y(i+1)+y(i-1))-0.5*(U*dt/h)*(y(i+1)-y(i-1));
    end
	f(n)= 0.5*(y(2)+y(n-1))-0.5 *(U*dt/h)*(y(2)-y(n-1));     % do endpoints for periodic b.c.
	f(1)=f(n);
    
    %    Leap Frog method
%     ybefore=y;            
    
%     if m==1
%         for i=2:n-1
%             f(i)=y(i)-(U*dt/h)*(y(i)-y(i-1));
%         end
%         f(n)=y(n)-(U*dt/h)*(y(n)-y(n-1));
%         f(1)=f(n);
%     else
%     
%         for i=2:n-1
%             f(i)=ybefore(i)-(U*dt/h)*(y(i+1)-y(i-1));
%         end
%         f(n)=ybefore(n)-(U*dt/h)*(y(2)-y(n-1));
%         f(1)=f(n);
%     
%     end
    

    %    Lax-Wendroff's Method
%     for i=2:n-1
% 		f(i)=y(i)-0.5*(U*dt/h)*(y(i+1)-y(i-1)) + 0.5*(U*dt/h)^2*(y(i+1)-2*y(i)+y(i-1));
%     end
% 	f(n)=y(n)-0.5 *(U*dt/h)*(y(2)-y(n-1)) + 0.5*(U*dt/h)^2*(y(2)-2*y(n)+y(n-1));   
% 	f(1)=f(n);


    %    MacComack Method
% 
%     for i=2:n-1
%         a = y(i) -(U*dt/h)*(y(i+1)-y(i));
%         b = y(i-1) - (U*dt/h)*(y(i)-y(i-1));
% 		f(i)= 0.5*(y(i)+a - (U*dt/h)*(a-b));
%     end
%     c = y(n) -(U*dt/h)*(y(2)-y(n));
%     d = y(n-1) - (U*dt/h)*(y(n)-y(n-1));
% 	f(n)= 0.5*( y(n) + c -(U*dt/h)*(c-d) );   
% 	f(1)=f(n);
    
    
    % -------------------------------------------------
    time=time+dt; %% Do not modify
end
