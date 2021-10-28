function y = trig(func,x)
switch func
    case 'sin'
    y = sin(x);
    case 'cos'
    y = cos(x);
    case 'tan'
    y = tan(x);
    otherwise
    error('No such function defined')
end