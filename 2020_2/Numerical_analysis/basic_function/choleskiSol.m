function x = choleskiSol(L,b)
% Solves  [L][L']{x} = {b}
% USAGE: x = choleskiSol(L,b)

n = length(b);
if size(b,2) > 1; b = b'; end   % {b} must be column vector
for k = 1:n    % Solution of [L]{y} = {b}
    b(k) = (b(k) - dot(L(k,1:k-1),b(1:k-1)'))/L(k,k);
end
for k = n:-1:1  % Solution of {L}'{x} = {y}
    b(k) = (b(k) - dot(L(k+1:n,k),b(k+1:n)))/L(k,k);
end    
x = b;
