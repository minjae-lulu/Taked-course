function [xSol,ySol] = runKut5(dEqs,x,y,xStop,h,tol)
% 5th-order Runge-Kutta integration.
% USAGE: [xSol,ySol] = runKut5(dEqs,x,y,xStop,h,tol)
% INPUT:
% dEqs  = handle of function that specifyies the
%         1st-order differential equations
%         F(x,y) = [dy1/dx dy2/dx dy3/dx ...].
% x,y   = initial values; y must be row vector.
% xStop = terminal value of x.
% h     = trial value of increment of x.
% tol  = per-step error tolerance (default = 1.0e-6).
% OUTPUT:
% xSol = x-values at which solution is computed.
% ySol = values of y corresponding to the x-values.

if size(y,1) > 1 ; y = y'; end  % y must be row vector
if nargin < 6; tol = 1.0e-6; end
n = length(y);

% Dormand-Prince coefficients
A2 = 0.2; A3 = 0.3; A4 = 0.8; A5 = 8/9; A6 = 1.0;
A7 = 1.0;

C1 = 35/384; C3 = 500/1113; C4 = 125/192; 
C5 = -2187/6784; C6 = 11/84;

D1 = 5179/57600; D3 = 7571/16695; D4 = 393/640;
D5 = -92097/339200; D6 = 187/2100; D7 = 1/40;

B21 = 0.2;
B31 = 0.075; B32 = 0.225;
B41 = 44/45; B42 = -56/15; B43 = 32/9;
B51 = 19372/6561; B52 = -25360/2187; B53 = 64448/6561;
B54 = -212/729;
B61 = 9017/3168; B62 =-355/33; B63 = 46732/5247;
B64 = 49/176; B65 = -5103/18656;
B71 = 35/384; B73 = 500/1113; B74 = 125/192;
B75 = -2187/6784; B76 = 11/84;

% Initialize solution
xSol = zeros(2,1); ySol = zeros(2,n);
xSol(1) = x; ySol(1,:) = y;
stopper = 0; counter = 1;
K1 = h*feval(dEqs,x,y);

for m = 2:5000
    K2 = h*feval(dEqs,x + A2*h,y + B21*K1);
    K3 = h*feval(dEqs,x + A3*h,y + B31*K1 + B32*K2);
    K4 = h*feval(dEqs,x + A4*h,y + B41*K1 + B42*K2... 
            + B43*K3);
    K5 = h*feval(dEqs,x + A5*h,y + B51*K1 + B52*K2...
            + B53*K3 + B54*K4);
    K6 = h*feval(dEqs,x + A6*h,y + B61*K1 + B62*K2...
            + B63*K3 + B64*K4 + B65*K5);
    K7 = h*feval(dEqs,x + A7*h,y + B71*K1 + B73*K3...
            + B74*K4 + B75*K5 + B76*K6);

    dy = C1*K1 + C3*K3 + C4*K4 + C5*K5 + C6*K6;
    E = (C1 - D1)*K1 + (C3 - D3)*K3 + (C4 - D4)*K4...
           + (C5 - D5)*K5 + (C6 - D6)*K6 - D7*K7;   
    e = sqrt(sum(E.*E)/n);
    hNext = 0.9*h*(tol/e)^0.2;
    
    % Accept integration step if e is within tolerance
    if  e <= tol
        y = y + dy; x = x + h;
        counter = counter + 1;
        xSol(counter) = x; ySol(counter,:) = y;
        if stopper == 1; break; end  % End of x-range
        if abs(hNext) > 10.0*abs(h); hNext = 10.0*h; end

      % Check if next step is the last one; if so, adjust h
        if (h > 0.0) == ((x + hNext) >= xStop)
            hNext = xStop - x;
            stopper = 1;
        end
        K1 = K7*hNext/h;
    else
        if abs(hNext) < 0.1*abs(h); hNext=0.1*h ;end   
        K1 = K1*hNext/h;
    end     
    h = hNext;
end    
