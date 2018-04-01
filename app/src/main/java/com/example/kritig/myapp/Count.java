package com.example.kritig.myapp;

/**
 * Created by Kriti G on 31-Mar-18.
 */

public class Count {

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void updateCount() {
        if (getCount() == 0) {
            setLast_incremented_date(System.currentTimeMillis());
            count++;
        } else {
            count = 0;
        }
    }

    public long getLast_incremented_date() {
        return last_incremented_date;
    }

    public void setLast_incremented_date(long last_incremented_date) {
        this.last_incremented_date = last_incremented_date;
    }

    private int count;
    private long last_incremented_date;
}
