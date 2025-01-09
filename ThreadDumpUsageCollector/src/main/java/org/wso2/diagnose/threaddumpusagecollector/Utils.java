package org.wso2.diagnose.threaddumpusagecollector;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private static Utils instance;

    public static Utils getInstance(){
        if(instance == null){
            instance = new Utils();
        }
        return instance;
    }
    /**
     * Check if the OS is Windows.
     *
     * @return true if the OS is Windows
     */
    public boolean isWindows() {
        return System.getProperty(Constants.OS_PROPERTY).toLowerCase().contains(Constants.OS_PROPERTY_WINDOWS);
    }

    /**
     * Check if the OS is macOS.
     *
     * @return true if the OS is macOS
     */
    public boolean isMac() {
        return System.getProperty(Constants.OS_PROPERTY).toLowerCase().contains(Constants.OS_PROPERTY_MAC);
    }

    // Convert interval to seconds
    public int convertToSeconds(String intervalInput) {
        try{
            char unit = intervalInput.charAt(intervalInput.length() - 1);
            int value = Integer.parseInt(intervalInput.substring(0, intervalInput.length() - 1));

            switch (unit) {
                case Constants.UNIT_MINUTES:
                    return value * 60;
                case Constants.UNIT_HOURS:
                    return value * 3600;
                case Constants.UNIT_DAYS:
                    return value * 86400;
                case Constants.UNIT_SECONDS:
                default:
                    return value;
            }
        }
        catch (Exception e){
            return Integer.parseInt(intervalInput.trim());
        }
    }

    // Get the current timestamp formatted as YYYY-MM-DD_HH-MM-SS
    public String getCurrentTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIMESTAMP_FORMAT);
        return sdf.format(new Date());
    }
}
