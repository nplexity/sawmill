package com.nplexity.android.sawmill;

public class Log {
    public static <T> void error(String tag, T... args) {
        LogMessage message = generateMessage(Sawmill.LogFlag.ERROR, tag, args);
        Sawmill.log(message);
    }

    public static <T> void warning(String tag, T... args) {
        LogMessage message = generateMessage(Sawmill.LogFlag.WARNING, tag, args);
        Sawmill.log(message);
    }

    public static <T> void info(String tag, T... args) {
        LogMessage message = generateMessage(Sawmill.LogFlag.INFO, tag, args);
        Sawmill.log(message);
    }

    public static <T> void debug(String tag, T... args) {
        LogMessage message = generateMessage(Sawmill.LogFlag.DEBUG, tag, args);
        Sawmill.log(message);
    }

    public static <T> void verbose(String tag, T... args) {
        LogMessage message = generateMessage(Sawmill.LogFlag.VERBOSE, tag, args);
        Sawmill.log(message);
    }

    // TODO: trace

    private static <T> LogMessage generateMessage(Sawmill.LogFlag flag, String tag, T... args) {
        final StringBuilder sb = new StringBuilder();
        for (T obj : args) {
            sb.append(obj);
        }

        return new LogMessage.Builder(sb.toString()).flag(flag).tag(tag).build();
    }
}
