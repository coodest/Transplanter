package app;

import model.CppTool;
import model.MatlabTool;
import model.PythonTool;

/**
 * Created by worker on 2016/4/26.
 */
public class Transplanter {

    /**
     * python 3.5 environment
     * @param input *.py
     * @param proxyFunction call with functionName
     * @param args
     */
    public static void operatePython(String input, String proxyFunction, String args){
        PythonTool.execute(input, proxyFunction, args);
    }

    /**
     * @param input *.c/c++, dll
     * @param proxyFunction usage: "resType funcName"
     * @param args
     */
    public static void operateCpp(String input, String proxyFunction, String args){
        CppTool.execute(input, proxyFunction, args);
    }

    /**
     * both script and function M file could be executed
     * @param input *.m file
     * @param proxyFunction
     * @param args
     */
    public static void operateMatlab(String input, String proxyFunction, String args){
        MatlabTool.execute(input, proxyFunction, args);
    }
}
