package com.nplexity.android.sawmill;

public interface Logger {
    void logMessage(LogMessage message);
    void setLogFormatter(LogFormatter formatter);
}
