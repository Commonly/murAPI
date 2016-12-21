/*
 * Copyright (c) 2016
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
     * @param logType Level of log type.
     * @param text    The text to log.
     */
    public static void log(LogType logType, String text) {
        if (logType == LogType.INFO)
            info(text);
        if (logType == LogType.SEVERE)
            severe(text);
        if (logType == LogType.ERROR)
            error(text);
        if (logType == LogType.WARN)
            warn(text);
        if (logType == null) {
            warn("Cannot be null!");
        }
    }
}
