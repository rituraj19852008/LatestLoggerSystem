package com.cs.mappers;

public class LogEvent {
    private String id;
    private long duration;
    private boolean alert;
    private String type;
    private String host;


    public String getId() {
        return this.id;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getType() {
        return this.type;
    }

    public String getHost() {
        return this.host;
    }

    public boolean isAlert() {
        return this.alert;
    }

    public static class Builder {
        private final String id;
        private final long duration;
        private final boolean alert;
        private String type;
        private String host;

        public Builder(String id, long duration, boolean alert) {
            this.id = id;
            this.duration = duration;
            this.alert = alert;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withHost(String host) {
            this.host = host;
            return this;
        }

        public LogEvent build() {
            LogEvent logEvent = new LogEvent();
            logEvent.alert = this.alert;
            logEvent.duration = this.duration;
            logEvent.type = this.type;
            logEvent.host = this.host;
            logEvent.id = this.id;
            return logEvent;
        }
    }
}
