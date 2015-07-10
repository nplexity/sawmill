package com.nplexity.android.sawmill;

public class Log {
    public static <T> void error(T... args) {
        LogMessage message = generateMessage(Sawmill.LogFlag.ERROR, args);
        Sawmill.log(message);
    }

    public static <T> void warning(T... args) {
        LogMessage message = generateMessage(Sawmill.LogFlag.WARNING, args);
        Sawmill.log(message);
    }

    public static <T> void info(T... args) {
        LogMessage message = generateMessage(Sawmill.LogFlag.INFO, args);
        Sawmill.log(message);
    }

    public static <T> void debug(T... args) {
        LogMessage message = generateMessage(Sawmill.LogFlag.DEBUG, args);
        Sawmill.log(message);
    }

    public static <T> void verbose(T... args) {
        LogMessage message = generateMessage(Sawmill.LogFlag.VERBOSE, args);
        Sawmill.log(message);
    }

    public static void trace() {
        LogMessage message = generateMessage(Sawmill.LogFlag.TRACE, null);
        Sawmill.log(message);
    }

    private static <T> LogMessage generateMessage(Sawmill.LogFlag flag, T... args) {
        final StackTraceElement[] elements = new Throwable().getStackTrace();
        String className = elements[2].getClassName();
        String methodName = elements[2].getMethodName();
        int lineNumber = elements[2].getLineNumber();

        final StringBuilder sb = new StringBuilder("[" + methodName + ":" + lineNumber + "] ");
        for (T obj : args) {
            sb.append(obj);
        }

        return new LogMessage.Builder(sb.toString()).flag(flag).tag(className).build();
    }
}
