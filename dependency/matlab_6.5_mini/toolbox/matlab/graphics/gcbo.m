function [object, fig] = gcbo
%GCBO Get handle to current callback object.
%   OBJECT = GCBO returns the handle of the object whose callback is
%   currently executing.  This handle is obtained from the root property
%   'CallbackObject'.
%   
%   [OBJECT, FIGURE] = GCBO returns the handle of the object whose callback
%   is currently executing, and the figure containing that object.
%
%   During a callback, GCBO can be used to obtain the handle of the object
%   whose callback is executing, and the figure which contains that object.
%   If one callback is interrupted by another, the root CallbackObject is
%   updated to contain the handle of the object whose callback is
%   interrupting.  When the execution of the interrupting callback has
%   completed, and the execution of the original callback resumes, the root
%   CallbackObject is restored to contain the handle of the original object.
%
%   The root CallbackObject property is read-only, so its value is
%   guaranteed to be valid at any time during a callback.  The root
%   CurrentFigure property, and the figure CurrentAxes and CurrentObject
%   properties (returned by GCF, GCA, and GCO respectively) are
%   user-settable, so they may change during the execution of a callback,
%   especially if that callback is interrupted by another callback.  As a
%   result, those functions should not be considered interchangeable with
%   GCBO, because they are not reliable indicators of which object's
%   callback is executing.
%
%   When no callbacks are executing, GCBO returns [].  If the current object
%   gets deleted during callback execution, GCBO returns [].
%
%   See also GCO, GCF, GCA, GCBF.

%   Copyright 1984-2002 The MathWorks, Inc. 
%   $Revision: 1.17 $  $Date: 2002/04/08 22:41:29 $

object = get(0, 'CallbackObject');

% If object is not a handle, it was likely deleted, return empty
% to prevent subsequent GETs from failing.
if ~ishandle(object)
    object = [];
end

% return the figure containing the object, if requested:
if nargout == 2,
    if isempty(object),
        fig = [];
    elseif isequal(object,0),
        fig = [];
    else,
        fig = object;
        try
            while ~isempty(fig) & ~isequal(get(fig,'parent'),0),
                fig = get(fig,'parent');
            end
        catch
            fig = [];
        end
    end
end
