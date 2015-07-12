package com.nplexity.android.sawmill;

import java.util.ArrayList;
import java.util.EnumSet;

public class Sawmill {

    public enum LogFlag {
        ERROR,
        WARNING,
        INFO,
        DEBUG,
        VERBOSE,
        TRACE
    }

    public enum LogLevel {
        NONE(EnumSet.noneOf(LogFlag.class)),
        ERROR(EnumSet.of(LogFlag.ERROR)),
        WARNING(EnumSet.of(LogFlag.ERROR, LogFlag.WARNING)),
        INFO(EnumSet.range(LogFlag.ERROR, LogFlag.INFO)),
        DEBUG(EnumSet.range(LogFlag.ERROR, LogFlag.DEBUG)),
        VERBOSE(EnumSet.range(LogFlag.ERROR, LogFlag.VERBOSE)),
        ALL(EnumSet.allOf(LogFlag.class));

        private final EnumSet<LogFlag> mFlags;

        LogLevel(EnumSet<LogFlag> flags) {
            mFlags = flags;
        }

        EnumSet<LogFlag> getFlags() {
            return mFlags;
        }

        public EnumSet<LogFlag> and(LogFlag flag) {
            EnumSet<LogFlag> flags = EnumSet.copyOf(mFlags);
            flags.add(flag);
            return flags;
        }
    }

    private static final ArrayList<LoggerNode> LOGGERS = new ArrayList<>();
    private static EnumSet<LogFlag> sActiveFlags = EnumSet.copyOf(LogLevel.NONE.getFlags());

    static EnumSet<LogFlag> getActiveFlags() {
        return sActiveFlags;
    }

    public static void addLogger(Logger logger) {
        addLogger(logger, LogLevel.ALL);
    }

    public static void addLogger(Logger logger, LogLevel level) {
        addLogger(logger, level.getFlags());
    }

    public static void addLogger(Logger logger, EnumSet<LogFlag> logFlags) {
        if (logger == null) {
            return;
        }

        sActiveFlags.addAll(logFlags);

        LoggerNode node = new LoggerNode(logger, logFlags);
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
            // TODO: remove the flags associated with this logger
        }

        return found;
    }

    static void log(LogMessage message) {
        for (LoggerNode node : LOGGERS) {
            if (node.getLogFlags().contains(message.getFlag())) {
                node.getLogger().logMessage(message);
            }
        }
    }
}
