%FINDSTR Find one string within another.
%   K = FINDSTR(S1,S2) returns the starting indices of any occurrences
%   of the shorter of the two strings in the longer.
%
%   FINDSTR is symmetric in its two arguments; that is, either
%   S1 or S2 may be the shorter pattern to be searched for in the longer
%   string.  If you do not want this behavior, use STRFIND instead.
%
%   Examples
%       s = 'How much wood would a woodchuck chuck?';
%       findstr(s,'a')    returns  21
%       findstr('a',s)    returns  21
%       findstr(s,'wood') returns  [10 23]
%       findstr(s,'Wood') returns  []
%       findstr(s,' ')    returns  [4 9 14 20 22 32]
%
%   See also STRFIND, STRCMP, STRNCMP, STRMATCH.

%   Copyright 1984-2002 The MathWorks, Inc. 
%   $Revision: 5.18 $  $Date: 2002/04/09 00:33:34 $
%   Built-in function.

