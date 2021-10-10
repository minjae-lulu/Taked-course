% Example 6.7 (Romberg integration)
format long
func = @(x) (2*(x^2)*cos(x^2));
[Integral,numEval] = romberg(func,0,sqrt(pi))