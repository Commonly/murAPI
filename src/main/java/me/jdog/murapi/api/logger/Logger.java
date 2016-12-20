package me.jdog.murapi.api.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Muricans on 11/20/16.
 */
public class Logger {
    private static Logger instance = new Logger();
    private Logger() {
    }
    public static Logger getInstance() {
        return instance;
    }

    private static void info(String text) {
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[" + format.format(time) + " INFO]: " + text);
    }

    private static void severe(String text) {
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[" + format.format(time) + " SEVERE]: " + text);
    }

    private static void error(String text) {
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[" + format.format(time) + " ERROR]: " + text);
    }

    private static void warn(String text) {
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[" + format.format(time) + " WARNING]: " + text);
    }

    /**
     *
     * @param logType Level of log type.
     * @param text The text to log.
     */
    public static void log(LogType logType, String text) {
        if(logType == LogType.INFO)
            info(text);
        if(logType == LogType.SEVERE)
            severe(text);
        if(logType == LogType.ERROR)
            error(text);
        if(logType == LogType.WARN)
            warn(text);
        if(logType == null) {
            warn("Cannot be null!");
        }
    }
}
