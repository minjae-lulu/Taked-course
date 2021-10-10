%hw3_2_5

xData = [0 0.5 1 1.5 2 2.5 3 3.5 4 4.5 5];
yData = [3.076 2.810 2.588 2.297 1.981 1.912 1.653 1.478 1.399 1.018 0.794];


coeff = polynFit(xData, yData,2); %straight line
sigma = stdDev(coeff,xData,yData); %standard deviation
y = coeff(2)*ones(length(xData),1) + coeff(1)*xData';

plot(xData,yData,'kx'); hold on; 
plot(xData,y,'k')
xlabel('x'); ylabel('y')