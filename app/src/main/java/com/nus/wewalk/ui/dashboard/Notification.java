package com.nus.wewalk.ui.dashboard;

public class Notification {

    public enum Type {
        STEP_REMINDER,
        ACHIEVEMENT,
        FRIEND_ACTIVITY,
        SYSTEM_UPDATE,
        CHALLENGE_INVITATION
    }

    private Type type;
    private String title;
    private String message;
    private long timestamp;

    public Notification(Type type, String title, String message) {
        this.type = type;
        this.title = title;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters
    public Type getType() { return type; }
    public String getTitle() { return title; }
    public String getMessage() { return message; }
    public long getTimestamp() { return timestamp; }
}
