clear all
clc

A=importdata('data.txt');
I_sol=0;
for i=1:size(A,2)-1
    I=1/2*(A(2,i)+A(2,i+1))*(A(1,i+1)-A(1,i));
    I_sol=I_sol+I;
end

I_sol