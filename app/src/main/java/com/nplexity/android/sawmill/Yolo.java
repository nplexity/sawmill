package com.nplexity.android.sawmill;

public class Yolo {
    public Yolo() {
        Sawmill.addLogger(null, Sawmill.LogLevel.DEBUG.and(Sawmill.LogFlag.TRACE));
        Log.debug("something");
    }
}
