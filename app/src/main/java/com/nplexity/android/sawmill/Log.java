package com.nplexity.android.sawmill;

public class Log {
    public static <T> void error(T... args) {
        if (Sawmill.getActiveFlags().contains(Sawmill.LogFlag.ERROR)) {
            LogMessage message = generateMessage(Sawmill.LogFlag.ERROR, args);
            Sawmill.log(message);
        }
    }

    public static <T> void warning(T... args) {
        if (Sawmill.getActiveFlags().contains(Sawmill.LogFlag.WARNING)) {
            LogMessage message = generateMessage(Sawmill.LogFlag.WARNING, args);
            Sawmill.log(message);
        }
    }

    public static <T> void info(T... args) {
        if (Sawmill.getActiveFlags().contains(Sawmill.LogFlag.INFO)) {
            LogMessage message = generateMessage(Sawmill.LogFlag.INFO, args);
            Sawmill.log(message);
        }
    }

    public static <T> void debug(T... args) {
        if (Sawmill.getActiveFlags().contains(Sawmill.LogFlag.DEBUG)) {
            LogMessage message = generateMessage(Sawmill.LogFlag.DEBUG, args);
            Sawmill.log(message);
        }
    }

    public static <T> void verbose(T... args) {
        if (Sawmill.getActiveFlags().contains(Sawmill.LogFlag.VERBOSE)) {
            LogMessage message = generateMessage(Sawmill.LogFlag.VERBOSE, args);
            Sawmill.log(message);
        }
    }

    public static void trace() {
        if (Sawmill.getActiveFlags().contains(Sawmill.LogFlag.TRACE)) {
            LogMessage message = generateTracerMessage();
            Sawmill.log(message);
        }
    }

    private static <T> LogMessage generateMessage(Sawmill.LogFlag flag, T... args) {
        final StackTraceElement[] elements = new Throwable().getStackTrace();
        String className = elements[2].getClassName();
        String methodName = elements[2].getMethodName();
        int lineNumber = elements[2].getLineNumber();

        final StringBuilder sb = new StringBuilder();
        for (T obj : args) {
            sb.append(obj);
        }

        return new LogMessage.Builder(sb.toString()).flag(flag).tag(className).className(className)
                                                    .methodName(methodName).lineNumber(lineNumber)
                                                    .build();
    }

    private static <T> LogMessage generateTracerMessage() {
        final StackTraceElement[] elements = new Throwable().getStackTrace();
        String className = elements[2].getClassName();
        String methodName = elements[2].getMethodName();
        int lineNumber = elements[2].getLineNumber();

        String message = "[" + methodName + ":" + lineNumber + "]";

        return new LogMessage.Builder(message).flag(Sawmill.LogFlag.TRACE).tag(className).build();
    }
}
