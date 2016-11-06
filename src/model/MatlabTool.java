package model;

import util.CppCompiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by worker on 2016/4/29.
 */
public class MatlabTool {
    public static void execute(String input, String proxyFunction, String args){
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
            if (module.endsWith(".m")) {
                module = module.substring(0, module.length() - 2);
            }

            // 2. execute
            // 2.1 ProcessBuilder init
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.redirectErrorStream(true);
            processBuilder.directory(new File(path));
            // 2.2 environment setup
            Map<String, String> envp = processBuilder.environment();
            envp.put("Path", envp.get("Path") + ";" + System.getProperty("user.dir") + "\\dependency\\matlab_6.5_mini\\bin\\win32");
            // 2.3 commands rigid
            List<String> commands = new ArrayList<>();
            commands.add("cmd");
            commands.add("/c");
            commands.add("matlab");
            commands.add("-nojvm");
            commands.add("-nodesktop");
            commands.add("-r");
            if(proxyFunction != null){
                if (args == null) {
                    commands.add(proxyFunction);
                } else {
                    commands.add(proxyFunction + "("+args+")");
                }
            }
            else{
                commands.add(module);
            }
            processBuilder.command(commands);
            // 2.4 process init
            Process process = processBuilder.start();
            process.waitFor();

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
