%DIAG Diagonal matrices and diagonals of a matrix.
%   DIAG(V,K) when V is a vector with N components is a square matrix
%   of order N+ABS(K) with the elements of V on the K-th diagonal. K = 0
%   is the main diagonal, K > 0 is above the main diagonal and K < 0
%   is below the main diagonal. 
%
%   DIAG(V) is the same as DIAG(V,0) and puts V on the main diagonal.
%
%   DIAG(X,K) when X is a matrix is a column vector formed from
%   the elements of the K-th diagonal of X.
%
%   DIAG(X) is the main diagonal of X. DIAG(DIAG(X)) is a diagonal matrix.
%
%   Example
%      m = 5;
%      diag(-m:m) + diag(ones(2*m,1),1) + diag(ones(2*m,1),-1)
%   produces a tridiagonal matrix of order 2*m+1.
%
%   See also SPDIAGS, TRIU, TRIL.

%   Copyright 1984-2002 The MathWorks, Inc. 
%   $Revision: 5.8 $  $Date: 2002/04/08 20:21:04 $
%   Built-in function.
