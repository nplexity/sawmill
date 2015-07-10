package com.nplexity.android.sawmill;

import java.util.ArrayList;

public class Sawmill {
    private static final ArrayList<LoggerNode> LOGGERS = new ArrayList<>();

    public static void addLogger(Logger logger) {
        addLogger(logger, LogLevel.ALL);
    }

    public static void addLogger(Logger logger, LogLevel level) {
        if (logger == null) {
            return;
        }

        LoggerNode node = new LoggerNode(logger, level.getLevelValue());
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

    void log(LogMessage message) {
        for (LoggerNode node : LOGGERS) {
            node.getLogger().logMessage(message);
        }
    }
}
