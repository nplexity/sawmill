package com.nplexity.android.sawmill;

public class Log {
    public static <T> void error(T... args) {
        LogMessage message = generateMessage(Sawmill.LOG_FLAG_ERROR, args);
        Sawmill.log(message);
    }

    public static <T> void warning(T... args) {
        LogMessage message = generateMessage(Sawmill.LOG_FLAG_WARNING, args);
        Sawmill.log(message);
    }

    public static <T> void info(T... args) {
        LogMessage message = generateMessage(Sawmill.LOG_FLAG_INFO, args);
        Sawmill.log(message);
    }

    public static <T> void debug(T... args) {
        LogMessage message = generateMessage(Sawmill.LOG_FLAG_DEBUG, args);
        Sawmill.log(message);
    }

    public static <T> void verbose(T... args) {
        LogMessage message = generateMessage(Sawmill.LOG_FLAG_VERBOSE, args);
        Sawmill.log(message);
    }

    public static void trace() {
        LogMessage message = generateTracerMessage();
        Sawmill.log(message);
    }

    private static <T> LogMessage generateMessage(int flag, T... args) {
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

    private static <T> LogMessage generateTracerMessage() {
        final StackTraceElement[] elements = new Throwable().getStackTrace();
        String className = elements[2].getClassName();
        String methodName = elements[2].getMethodName();
        int lineNumber = elements[2].getLineNumber();

        String message = "[" + methodName + ":" + lineNumber + "]";

        return new LogMessage.Builder(message).flag(Sawmill.LOG_FLAG_TRACE).tag(className).build();
    }
}
