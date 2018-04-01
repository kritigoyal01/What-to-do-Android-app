package com.example.kritig.myapp;

/**
 * Created by Kriti G on 31-Mar-18.
 */

public class Message {

    private String message;
    private long last_display_time;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public long getLast_display_time() {
        return last_display_time;
    }

    public void setLast_display_time(long last_display_time) {
        this.last_display_time = last_display_time;
    }




}
