function v = swapCols(v,i,j)
% Swaps columns i and j of row vector or matrix v.
% USAGE: v = swapCols(v,i,j)

temp = v(:,i);
v(:,i) = v(:,j);
v(:,j) = temp;
