package com.nplexity.android.sawmill;

public class LoggerNode {
    private final Logger mLogger;
    private int mLogLevel;

    public LoggerNode(Logger logger, int level) {
        mLogger = logger;
        mLogLevel = level;
    }

    public Logger getLogger() {
        return mLogger;
    }

    public int getLogLevel() {
        return mLogLevel;
    }

    public void setLogLevel(int logLevel) {
        mLogLevel = logLevel;
    }
}
