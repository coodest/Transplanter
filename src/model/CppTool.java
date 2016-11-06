package model;

import util.CppCompiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by worker on 2016/4/29.
 */
public class CppTool {
    public static void execute(String input, String proxyFunction, String args) {
        try {
            // 1. do preparation
            // 1.1 check input
            while (input.contains("\\")) {
                input = input.replace("\\", "/");
            }
            // 1.2 build path
            String[] splitInput = input.split("/");
            String path = "";
            for (int i = 0; i < splitInput.length - 1; i++) {
                path += splitInput[i];
                if (i < splitInput.length - 2) {
                    path += "/";
                }
            }
            // 1.3 check module
            String module = splitInput[splitInput.length - 1];
            if (module.endsWith(".cpp")) {
                CppCompiler.compileToDLL(input);
                module = module.substring(0, module.length() - 4);
                module += ".dll";
            }
            if (module.endsWith(".c")) {
                CppCompiler.compileToDLL(input);
                module = module.substring(0, module.length() - 2);
                module += ".dll";
            }
            // 1.4 args trim
            String argTypes = "";
            if (args == null) {
                args = "";
            } else {
                args = args.trim();
                String[] splitArgs = args.split(",");
                for (int i = 0; i < splitArgs.length; i++) {
                    if (splitArgs[i].startsWith("\"")) {
                        argTypes += " string";
                    } else if (splitArgs[i].contains(".")) {
                        argTypes += " float";
                    } else if (Pattern.compile("^[0-9]*$").matcher(splitArgs[i]).find()) {
                        argTypes += " int";
                    }
                    if (i != splitArgs.length - 1) {
                        argTypes += ",";
                    }
                }
            }
            // 1.5 function return type set
            String resType = proxyFunction.split(" ")[0];
            String proxyFunctionName = proxyFunction.split(" ")[1];
            // 1.6 make runner files
            File runnerCpp = new File(path + "\\CppRunner.cpp");
            if(!runnerCpp.exists()){
                FileWriter runnerCppWriter = new FileWriter(runnerCpp);
                String runnerCppContent =
                        "#include <iostream>\n" +
                                "#include <string>\n" +
                                "#include <Windows.h>\n" +
                                "using namespace std;\n" +
                                "int main(int argc, char* argv[]) {\n" +
                                "    typedef " + resType + " (*Func) (" + argTypes + ");\n" +
                                "    HINSTANCE dll = LoadLibrary(\""+module+"\");\n" +
                                "    Func proxy = (Func) GetProcAddress(dll, \""+proxyFunctionName+"\");\n";
                if(resType.compareTo("void") == 0){
                    runnerCppContent += "    proxy(" + args + ");\n";
                }
                else{
                    runnerCppContent += "    " + resType + " any = proxy("+args+");\n" + "    cout<<any<<endl;\n";
                }
                runnerCppContent += "    FreeLibrary(dll);\n" + "    return 0;\n" + "}";
                runnerCppWriter.write(runnerCppContent);
                runnerCppWriter.flush();
                runnerCppWriter.close();
            }
            File runnerExe = new File(path + "\\CppRunner.exe");
            if(!runnerExe.exists()){
                CppCompiler.compileToEXE(path + "\\CppRunner.cpp");
            }

            // 2. execute
            // 2.1 ProcessBuilder init
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.redirectErrorStream(true);
            processBuilder.directory(new File(path));
            // 2.2 commands rigid
            List<String> commands = new ArrayList<>();
            commands.add("cmd");
            commands.add("/c");
            commands.add("CppRunner");
            processBuilder.command(commands);
            // 2.3 process init
            Process process = processBuilder.start();
            process.waitFor();
            // 2.4 del runner files
            runnerCpp.delete();
            runnerExe.delete();

            // 3. output result
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            process.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
