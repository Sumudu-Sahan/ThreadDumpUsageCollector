package org.wso2.diagnose.threaddumpusagecollector;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreadDumpCollector {
    public static ThreadDumpCollector instance;

    public static ThreadDumpCollector getInstance(){
        if(instance == null){
            instance = new ThreadDumpCollector();
        }
        return instance;
    }
    // Generate thread dump using jstack
    public void generateThreadDump(String pid, String threadDumpFileName, String threadUsageFileName) {
        try {
            // Prepare the jstack command
            String command = "jstack -l " + pid;
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();

            // Capture the output of jstack
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(threadDumpFileName))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            // Wait for the process to finish
            process.waitFor();
            generateCPUUsageReport(threadUsageFileName, threadDumpFileName);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void generateCPUUsageReport(String threadUsageFileName, String threadDumpFileName) {
        try{
            String threadDumpContent = new String(Files.readAllBytes(Paths.get(threadDumpFileName)));

            //pattern verification
            // Regular expression to extract cpu and elapsed values
            String regexVerification = "cpu=(\\d+\\.\\d+ms)\\s+elapsed=(\\d+\\.\\d+s)";
            Pattern patternVerification = Pattern.compile(regexVerification);
            Matcher matcherVerification = patternVerification.matcher(threadDumpContent);

            if(!matcherVerification.find()){
                System.out.println(Constants.USAGES_DUMPING_FAILED);
                return;
            }

            Pattern pattern = Pattern.compile(Constants.REGEX_PATTERN);
            Matcher matcher = pattern.matcher(threadDumpContent);

            // Prepare a list to hold the extracted data
            List<String> extractedThreads = new ArrayList<>();

            extractedThreads.add(Constants.STARTING_SPACE + Constants.THREAD_ID_STRING + Constants.SPLITTER + Constants.CPU_USAGE_STRING + Constants.SPLITTER + Constants.THREAD_NAME_STRING);

            // Extract and format thread details
            while (matcher.find()) {
                String threadName = matcher.group(1);
                String threadID = matcher.group(4);
                double cpu = Double.parseDouble(matcher.group(2).trim());
                double elapsed = Double.parseDouble(matcher.group(3).trim());

                // Calculate CPU percentage usage
                double cpuUsage = (cpu / (elapsed * 1000)) * 100;
                // Write to file
                extractedThreads.add(threadID + Constants.SPLITTER + Constants.PERCENTAGE_FORMAT.format(cpuUsage) + Constants.SPLITTER + threadName);
            }
            Files.write(Paths.get(threadUsageFileName), extractedThreads);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
