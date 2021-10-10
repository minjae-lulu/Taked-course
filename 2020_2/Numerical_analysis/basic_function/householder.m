function [c,d,P] = householder(A)
% Housholder reduction of A to tridiagonal form A -> [c\d\c].
% USAGE: [c,d] = householder(A)  computes c and d only.
%      [c,d,P] = householder(A)  also computes the transformation
%                                matrix P.

% Householder reduction
n = size(A,1);
for k = 1:n-2
    u = A(k+1:n,k);
    uMag = sqrt(dot(u,u));
    if u(1) < 0; uMag = -uMag; end
    u(1) = u(1) + uMag;
    A(k+1:n,k) = u;            % Save u in lower part of A
    H = dot(u,u)/2;
    v = A(k+1:n,k+1:n)*u/H;
    g = dot(u,v)/(2*H);
    v = v - g*u;
    A(k+1:n,k+1:n) = A(k+1:n,k+1:n) - v*u' - u*v';
    A(k,k+1) = -uMag;
end
d = diag(A); c = diag(A,1);
if nargout == 3; P = transMatrix; end
   
    % Computation of the transformation matrix
    function P = transMatrix
    P = eye(n);
    for k = 1:n-2
        u = A(k+1:n,k);
        H = dot(u,u)/2;
        v = P(1:n,k+1:n)*u/H;
        P(1:n,k+1:n) = P(1:n,k+1:n) - v*u';
    end
    end
end