package org.wso2.diagnose.threaddumpusagecollector;

import java.text.DecimalFormat;

public class Constants {
    public static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("###0.00");
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd_HH-mm-ss";

    public static final char UNIT_SECONDS = 's';
    public static final char UNIT_MINUTES = 'm';
    public static final char UNIT_HOURS = 'h';
    public static final char UNIT_DAYS = 'd';

    public static final String THREAD_ID_STRING = "Thread ID";
    public static final String THREAD_NAME_STRING = "Thread Name";
    public static final String CPU_USAGE_STRING = "CPU Usage(%)";
    public static final String STARTING_SPACE = "   ";
    public static final String SPLITTER = "          ";

    public static final String SLEEPING_FOR_TEXT = "Sleeping for ";
    public static final String JAR_COMMAND = "Usage: java -jar ThreadDumpUsageCollector-1.0-jar-with-dependencies.jar <JVM_PID> <interval_seconds> <num_dumps> <output_directory>";
    public static final String INVALID_DUMP_COUNT = "Invalid value for the <num_dumps>";

    public static final String DUMPING_SUCCESS = "Successfully dumped all the files!!!";
    public static final String USAGES_DUMPING_FAILED = "Cannot find the CPU and elapse times in the dumps. Hence, aborting the usage generation.";

    public static final String OS_PROPERTY = "os.name";
    public static final String OS_PROPERTY_WINDOWS = "win";
    public static final String OS_PROPERTY_MAC = "mac";

    public static final String REGEX_PATTERN = "\"([^\"]+)\" #\\d+.*?cpu=(\\d+\\.\\d+)ms elapsed=(\\d+\\.\\d+)s tid=(0x[0-9a-fA-F]+).*";
}
