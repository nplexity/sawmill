package com.nplexity.android.sawmill;

import java.util.EnumSet;

class LoggerNode {
    private final Logger mLogger;
    private EnumSet<Sawmill.LogFlag> mLogFlags;

    LoggerNode(Logger logger, EnumSet<Sawmill.LogFlag> logFlags) {
        mLogger = logger;
        mLogFlags = logFlags;
    }

    Logger getLogger() {
        return mLogger;
    }

    EnumSet<Sawmill.LogFlag> getLogFlags() {
        return mLogFlags;
    }

    void setLogFlags(EnumSet<Sawmill.LogFlag> logFlags) {
        mLogFlags = logFlags;
    }
}
