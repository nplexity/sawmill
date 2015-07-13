package com.nplexity.android.sawmill;

import android.util.Log;

import java.util.Calendar;

public class LogcatLogger implements Logger {
    private LogFormatter mFormatter;

    private static LogcatLogger sInstance;

    public static LogcatLogger getInstance() {
        if (sInstance == null) {
            sInstance = new LogcatLogger();
        }
        return sInstance;
    }

    @Override
    public void logMessage(LogMessage message) {
        String msg;
        if (mFormatter != null) {
            msg = mFormatter.formatLogMessage(message);
        } else {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(message.getTimestamp());
            String time = String.valueOf(calendar.get(Calendar.YEAR)) + "-" +
                          calendar.get(Calendar.MONTH) + "-" +
                          calendar.get(Calendar.DAY_OF_MONTH) + " " +
                          calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                          calendar.get(Calendar.MINUTE) + ":" +
                          calendar.get(Calendar.SECOND) + "." +
                          calendar.get(Calendar.MILLISECOND);

            String verbosity;
            switch (message.getFlag()) {
                case ERROR:
                    verbosity = "E";
                    break;
                case WARNING:
                    verbosity = "W";
                    break;
                case INFO:
                    verbosity = "I";
                    break;
                case DEBUG:
                    verbosity = "D";
                    break;
                case VERBOSE:
                    verbosity = "V";
                    break;
                case TRACE:
                    verbosity = "T";
                    break;
                default:
                    verbosity = "X";
                    break;
            }

            msg = verbosity + " " + time + " " + message.getMessage();
        }

        Log.println(message.getFlag().getPriority(), message.getTag(), msg);
    }

    @Override
    public void setLogFormatter(LogFormatter formatter) {
        mFormatter = formatter;
    }
}
