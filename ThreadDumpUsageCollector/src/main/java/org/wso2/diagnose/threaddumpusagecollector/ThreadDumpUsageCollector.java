package org.wso2.diagnose.threaddumpusagecollector;

import java.io.*;

public class ThreadDumpUsageCollector {
    private static ThreadDumpUsageCollector instance;

    private static ThreadDumpUsageCollector getInstance(){
        if(instance == null){
            instance = new ThreadDumpUsageCollector();
        }
        return instance;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println(Constants.JAR_COMMAND);
            return;
        }

        String pid = args[0];  // JVM Process ID (PID)
        String interval = args[1]; // Interval in seconds
        int numFiles;
        try{
            numFiles = Integer.parseInt(args[2]); // Number of dumps to capture
        }
        catch (Exception e){
            System.out.println(Constants.INVALID_DUMP_COUNT);
            return;
        }
        String outputDirectory = args[3]; // Output directory

        ThreadDumpUsageCollector.getInstance().initThreadDumpCapture(pid, outputDirectory, numFiles, interval);
    }

    private void initThreadDumpCapture(String jvmProcessID, String outputDirectory, int numFiles, String interval){
        File dir = new File(outputDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        int intervalSeconds = Utils.getInstance().convertToSeconds(interval);

        // Generate thread dumps
        for (int counter = 1; counter <= numFiles; counter++) {
            try {
                // Generate the thread dump
                String threadDumpFileName = String.format("%s/thread_dump_" + Utils.getInstance().getCurrentTimeStamp() + ".txt", outputDirectory, counter);
                String threadUsageFileName = String.format("%s/thread_usage_" + Utils.getInstance().getCurrentTimeStamp() + ".txt", outputDirectory, counter);
                ThreadDumpCollector.getInstance().generateThreadDump(jvmProcessID, threadDumpFileName, threadUsageFileName);

                // If this is not the last dump, sleep for the interval
                if (counter != numFiles) {
                    System.out.println(Constants.SLEEPING_FOR_TEXT + interval + " [" + counter + "]");
                    Thread.sleep(intervalSeconds * 1000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Constants.DUMPING_SUCCESS);
    }
}