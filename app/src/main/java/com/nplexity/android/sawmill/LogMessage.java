package com.nplexity.android.sawmill;

public class LogMessage {
    private final String mMessage;
    private final String mTag;
    private final Sawmill.LogFlag mFlag;
    private final long mTimestamp = System.currentTimeMillis();
    private final String mClassName;
    private final String mMethodName;
    private final int mLineNumber;

    private LogMessage(Builder builder) {
        mMessage = builder.message;
        mTag = builder.tag;
        mFlag = builder.flag;
        mClassName = builder.className;
        mMethodName = builder.methodName;
        mLineNumber = builder.lineNumber;
    }

    public static class Builder {
        private String message = "";
        private String tag = "";
        private Sawmill.LogFlag flag = Sawmill.LogFlag.VERBOSE;
        private String className;
        private String methodName;
        private int lineNumber;

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

        public Builder className(String className) {
            this.className = className;
            return this;
        }

        public Builder methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public Builder lineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
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

    public String getClassName() {
        return mClassName;
    }

    public String getMethodName() {
        return mMethodName;
    }

    public int getLineNumber() {
        return mLineNumber;
    }
}
