package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by worker on 2016/5/3.
 */
public class CppCompiler {
    public static void compileToDLL(String input) {
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
        // 1.3 generate cppName and dllName
        String codeName = splitInput[splitInput.length - 1];
        String nonExtCodeName = "";
        if (codeName.endsWith(".cpp")) {
            nonExtCodeName = codeName.substring(0, codeName.length() - 4);
        }
        if (codeName.endsWith(".c")) {
            nonExtCodeName = codeName.substring(0, codeName.length() - 2);
        }
        String dllName = nonExtCodeName + ".dll";

        // 2. execute compileToDLL
        // 2.1 config ProcessBuilder
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.redirectErrorStream(true);
        processBuilder.directory(new File(path));
        // 2.2 environment setup
        Map<String, String> envp = processBuilder.environment();
        envp.put("Path", envp.get("Path") + ";" + System.getProperty("user.dir") + "\\dependency\\MinGW64\\bin\\");
        // 2.3 execute
        List<String> commands;
        // 2.3.1
        commands = new ArrayList<>();
        processBuilder.command(commands);
        commands.add("cmd");
        commands.add("/c");
        commands.add("g++");
        commands.add("-shared");
        commands.add("-o");
        commands.add(dllName);
        commands.add(codeName);
        execute(processBuilder);
    }

    public static void compileToEXE(String input) {
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
        // 1.3 generate cppName
        String codeName = splitInput[splitInput.length - 1];
        String nonExtCodeName = "";
        if (codeName.endsWith(".cpp")) {
            nonExtCodeName = codeName.substring(0, codeName.length() - 4);
        }
        if (codeName.endsWith(".c")) {
            nonExtCodeName = codeName.substring(0, codeName.length() - 2);
        }

        // 2. execute compileToDLL
        // 2.1 config ProcessBuilder
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.redirectErrorStream(true);
        processBuilder.directory(new File(path));
        // 2.2 environment setup
        Map<String, String> envp = processBuilder.environment();
        envp.put("Path", envp.get("Path") + ";" + System.getProperty("user.dir") + "\\dependency\\MinGW64\\bin\\");
        // 2.3 execute
        List<String> commands;
        // 2.3.1
        commands = new ArrayList<>();
        processBuilder.command(commands);
        commands.add("cmd");
        commands.add("/c");
        commands.add("g++");
        commands.add("-o");
        commands.add(nonExtCodeName);
        commands.add(codeName);
        execute(processBuilder);
    }

    private static void execute(ProcessBuilder processBuilder) {
        try {
            Process process = processBuilder.start();
            process.waitFor();

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
