package com.do_this.model;

/**
 * Created by Kriti G on 31-Mar-18.
 */

public class Count {
    private int count;
    private long lastIncrementedDate;

    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
        setLastIncrementedDate(getCount() != 0 ? System.currentTimeMillis() : 0);
    }

    private void setLastIncrementedDate(long lastIncrementedDate) {
        this.lastIncrementedDate = lastIncrementedDate;
    }

    public void incrementCount() {
        setCount(getCount() + 1);
    }

    public void resetIfADayOld() {
        if (this.lastIncrementedDate != 0 && System.currentTimeMillis() - this.lastIncrementedDate - 86400000 > 0) {
            setCount(0);
        }
    }
}
