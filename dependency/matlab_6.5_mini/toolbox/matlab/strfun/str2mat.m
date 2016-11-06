function s=str2mat(varargin)
%STR2MAT Form blank padded character matrix from strings.
%   S = STR2MAT(T1,T2,T3,..) forms the matrix S containing the text
%   strings T1,T2,T3,... as rows.  Automatically pads each string with
%   blanks in order to form a valid matrix.  Each text parameter, Ti,
%   can itself be a string matrix.  This allows the creation of
%   arbitarily large string matrices.  Empty strings are significant.
%
%   STR2MAT differs from STRVCAT in that empty strings produce blank rows
%   in the output.  In STRVCAT, empty strings are ignored.
%
%   STR2MAT is grandfathered.  Use the CHAR function instead.
%
%   See also CHAR, STRVCAT.

%   Clay M. Thompson  3-20-91, 5-16-95
%   Copyright 1984-2002 The MathWorks, Inc. 
%   $Revision: 5.23 $  $Date: 2002/04/09 00:33:36 $

s = char(varargin{:});
