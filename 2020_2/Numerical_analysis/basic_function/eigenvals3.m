function eVals = eigenvals3(c,d,m)
% Computes the smallest m eigenvalues of A = [c\d\c].
% USAGE: eVals = eigenvals3(c,d,m).

eVals = zeros(m,1);
r = eValBrackets(c,d,m); % Bracket eigenvalues
for i=1:m
    % Solve |A - eVal*I| for eVal by Rider's method
    eVals(i) = ridder(@func,r(i),r(i+1));
end

    function f = func(eVal)
    % Returns |A - eVal*I| (last element of Sturm seq.)
    p = sturmSeq(c,d,eVal);
    f = p(length(p));
    end % function func

end % function eigenvals3
   