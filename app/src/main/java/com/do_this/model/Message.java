package com.do_this.model;

/**
 * Created by Kriti G on 31-Mar-18.
 */

public class Message {
    private String message;
    private long lastDisplayTime;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public long getLastDisplayTime() {
        return lastDisplayTime;
    }

    public void setLastDisplayTime(long lastDisplayTime) {
        this.lastDisplayTime = lastDisplayTime;
    }

    public boolean isADayOld() {
        return getLastDisplayTime() == 0 || System.currentTimeMillis() - getLastDisplayTime() - 86400000 > 0;
    }
}
