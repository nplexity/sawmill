package com.nplexity.android.sawmill;

import java.util.ArrayList;

public class Sawmill {
    public static final int LOG_FLAG_ERROR = 1;
    public static final int LOG_FLAG_WARNING = 1 << 1;
    public static final int LOG_FLAG_INFO = 1 << 2;
    public static final int LOG_FLAG_DEBUG = 1 << 3;
    public static final int LOG_FLAG_VERBOSE = 1 << 4;
    public static final int LOG_FLAG_TRACE = 1 << 5;

    public static final int LOG_LEVEL_NONE = 0;
    public static final int LOG_LEVEL_ERROR = LOG_FLAG_ERROR;
    public static final int LOG_LEVEL_WARNING = LOG_LEVEL_ERROR | LOG_FLAG_WARNING;
    public static final int LOG_LEVEL_INFO = LOG_LEVEL_WARNING | LOG_FLAG_INFO;
    public static final int LOG_LEVEL_DEBUG = LOG_LEVEL_INFO | LOG_FLAG_DEBUG;
    public static final int LOG_LEVEL_VERBOSE = LOG_LEVEL_DEBUG | LOG_FLAG_VERBOSE;
    public static final int LOG_LEVEL_ALL = Integer.MAX_VALUE;

    private static final ArrayList<LoggerNode> LOGGERS = new ArrayList<>();
    private static int sHighestLogLevel = LOG_LEVEL_NONE;

    static int getHighestLogLevel() {
        return sHighestLogLevel;
    }

    public static void addLogger(Logger logger) {
        addLogger(logger, LOG_LEVEL_ALL);
    }

    public static void addLogger(Logger logger, int bitmask) {
        if (logger == null) {
            return;
        }

        if (bitmask > sHighestLogLevel) {
            sHighestLogLevel = bitmask;
        }

        LoggerNode node = new LoggerNode(logger, bitmask);
        LOGGERS.add(node);
    }

    public static boolean removeLogger(Logger logger) {
        LoggerNode nodeToRemove = null;
        for (LoggerNode node : LOGGERS) {
            if (node.getLogger() == logger) {
                nodeToRemove = node;
                break;
            }
        }

        boolean found = LOGGERS.remove(nodeToRemove);
        if (found) {
            sHighestLogLevel = LOG_LEVEL_NONE;
            for (LoggerNode node : LOGGERS) {
                sHighestLogLevel = Math.max(node.getLogLevel(), sHighestLogLevel);
            }
        }

        return false;
    }

    static void log(LogMessage message) {
        for (LoggerNode node : LOGGERS) {
            if ((message.getFlag() & node.getLogLevel()) > 0) {
                node.getLogger().logMessage(message);
            }
        }
    }
}
