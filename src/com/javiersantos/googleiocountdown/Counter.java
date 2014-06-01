package com.javiersantos.googleiocountdown;

import android.text.format.Time;

public class Counter {
	
	private long intervalMillis;

    public Counter(int second, int minute, int hour, int monthDay, int month, int year) {

        Time futureTime = new Time();

        // Set date to future time
        futureTime.set(second, minute, hour, monthDay, month, year);
        futureTime.normalize(true);
        long futureMillis = futureTime.toMillis(true);

        Time timeNow = new Time();

        // Set date to current time
        timeNow.setToNow();
        timeNow.normalize(true);
        long nowMillis = timeNow.toMillis(true);

        // Subtract current milliseconds time from future milliseconds time to retrieve interval
        intervalMillis = futureMillis - nowMillis;
    }

    public long getIntervalMillis() {
        return intervalMillis;
    }

}