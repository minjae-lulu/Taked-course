function s2 = strip(s1)
s2 = '';                       % Create an empty string
for i = 1:length(s1)
    if s1(i) == ' '
        continue
    else
        s2 = strcat(s2,s1(i)); % Concatenation
    end
end