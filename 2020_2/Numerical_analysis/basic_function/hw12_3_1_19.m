%hw3_1_19

xData = [0 21.1 37.8 54.4 71.1 87.8 100];
yData = [1.79 1.13 0.696 0.519 0.338 0.321 0.296];

x = [10 30 60 90];

k = splineCurv(xData, yData);

for T = x
    y = splineEval(xData, yData, k, T);
    fprintf('%8.0f %12.4f\n',T,y);
end
