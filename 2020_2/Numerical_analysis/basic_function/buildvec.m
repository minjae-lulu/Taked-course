function x = buildvec
for i = 1:1000
    elem = input('==> '); % Prompts for input of element
    if isempty(elem)      % Check for empty element
        break
    end
    x(i) = elem;
end