% Example 6.11 (Gauss-Legendre quadrature)
func = @(x) ((sin(x)/x)^2);
a = 0; b = pi; Iexact = 1.41815;
for n = 2:12
    I = gaussQuad(func,a,b,n);
    if abs(I - Iexact) < 0.00001
        I
        n
        break
    end
end