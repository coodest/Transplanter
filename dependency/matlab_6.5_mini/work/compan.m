function A = compan (c)
%COMPAN Companion matrix.
%   COMPAN(P) is a companion matrix of the polynomial
%   with coefficients P.

%   Copyright 1984-2002 The MathWorks, Inc. 
%   $Revision: 5.7 $  $Date: 2002/04/08 20:21:04 $

if min(size(c)) > 1
    error('Input argument must be a vector.')
end
n = length(c);
if n <= 1
   A = [];
elseif n == 2
   A = -c(2)/c(1);
else
   c = c(:).';     % make sure it's a row vector
   A = diag(ones(1,n-2),-1);
   A(1,:) = -c(2:n) ./ c(1);
end
