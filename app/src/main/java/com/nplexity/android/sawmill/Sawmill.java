package com.nplexity.android.sawmill;

import java.util.ArrayList;

public class Sawmill {
    private static final ArrayList<LoggerNode> LOGGERS = new ArrayList<>();
    private static LogLevel sHighestLogLevel = LogLevel.NONE;

    static LogLevel getHighestLogLevel() {
        return sHighestLogLevel;
    }

    public static void addLogger(Logger logger) {
        addLogger(logger, LogLevel.ALL);
    }

    public static void addLogger(Logger logger, LogLevel level) {
        addLogger(logger, level.getLevelValue());
    }

    public static void addLogger(Logger logger, int bitmask) {
        if (logger == null) {
            return;
        }

        //        if (level > sHighestLogLevel) {
        //            sHighestLogLevel = level;
        //        }

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

        return LOGGERS.remove(nodeToRemove);
    }

    public enum LogLevel {
        NONE(0),
        ERROR(LogFlag.ERROR.getFlagValue()),
        WARNING(ERROR.getLevelValue() | LogFlag.WARNING.getFlagValue()),
        INFO(WARNING.getLevelValue() | LogFlag.INFO.getFlagValue()),
        DEBUG(INFO.getLevelValue() | LogFlag.DEBUG.getFlagValue()),
        VERBOSE(DEBUG.getLevelValue() | LogFlag.VERBOSE.getFlagValue()),
        ALL(Integer.MAX_VALUE);

        private final int mLevelValue;

        LogLevel(int level) {
            mLevelValue = level;
        }

        public int getLevelValue() {
            return mLevelValue;
        }
    }

    public enum LogFlag {
        ERROR(1),
        WARNING(1 << 1),
        INFO(1 << 2),
        DEBUG(1 << 3),
        VERBOSE(1 << 4),
        TRACE(1 << 5);

        private final int mFlagValue;

        LogFlag(int flag) {
            mFlagValue = flag;
        }

        public int getFlagValue() {
            return mFlagValue;
        }
    }

    static void log(LogMessage message) {
        for (LoggerNode node : LOGGERS) {
            if ((message.getFlag().getFlagValue() & node.getLogLevel()) > 0) {
                node.getLogger().logMessage(message);
            }
        }
    }
}
