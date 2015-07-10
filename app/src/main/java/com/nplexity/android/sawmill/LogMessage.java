package com.nplexity.android.sawmill;

public class LogMessage {
    private final String mTag;
    private final String mMessage;
    private final Sawmill.LogFlag mFlag;
    private final long mTimestamp = System.currentTimeMillis();

    private LogMessage(Builder builder) {
        mTag = builder.tag;
        mMessage = builder.message;
        mFlag = builder.flag;
    }

    public static class Builder {
        private String tag = "";
        private String message = "";
        private Sawmill.LogFlag flag = Sawmill.LogFlag.VERBOSE;

        public Builder(String message) {
            this.message = message;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder flag(Sawmill.LogFlag flag) {
            this.flag = flag;
            return this;
        }

        public LogMessage build() {
            return new LogMessage(this);
        }
    }

    public String getTag() {
        return mTag;
    }

    public String getMessage() {
        return mMessage;
    }

    public Sawmill.LogFlag getFlag() {
        return mFlag;
    }

    public long getTimestamp() {
        return mTimestamp;
    }
}
