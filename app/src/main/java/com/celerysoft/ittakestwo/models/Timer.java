package com.celerysoft.ittakestwo.models;

/**
 * Timer is use for record the playing duration.
 */
public class Timer {
    private int mTimerState;
    public static final int TIMER_STATE_UNSTART = -1;
    public static final int TIMER_STATE_PROGRESS = 0;
    public static final int TIMER_STATE_PAUSE = 1;
    public static final int TIMER_STATE_STOP = 2;

    private long mStartTime;
    private long mPauseTime;
    private long mStopTime;
    private long mDuration;
    private void setDuration(long duration) {
        mDuration = duration;
    }

    private float mDurationInSecond;
    public float getDurationInSecond () {
        mDurationInSecond = mDuration / 1000;
        return mDurationInSecond;
    }

    public Timer() {
        mTimerState = TIMER_STATE_UNSTART;
        mDuration = 0;
    }

    /**
     * reset timer
     */
    public void reset() {
        mTimerState = TIMER_STATE_UNSTART;
        mDuration = 0;
    }

    /**
     * Start timer
     */
    public void start() {
        if (mTimerState == TIMER_STATE_UNSTART || mTimerState == TIMER_STATE_PAUSE) {
            mStartTime = System.currentTimeMillis();
            mTimerState = TIMER_STATE_PROGRESS;
        }
    }

    /**
     * Pause timer
     */
    public void pause() {
        if (mTimerState == TIMER_STATE_PROGRESS) {
            mPauseTime = System.currentTimeMillis();
            mDuration += mPauseTime - mStartTime;
            mTimerState = TIMER_STATE_PAUSE;
        }
    }

    /**
     * Stop timer
     */
    public void stop() {
        if (mTimerState != TIMER_STATE_STOP) {
            mStopTime = System.currentTimeMillis();
            mDuration += mStopTime - mStartTime;
            mTimerState = TIMER_STATE_STOP;
        }

    }

    /**
     * Restart timer
     */
    public void restart() {
        reset();
        start();
    }

    private long calculateCurrentDuration() {
        long currentDuration;

        if (mTimerState == TIMER_STATE_PROGRESS) {
            currentDuration = mDuration + System.currentTimeMillis() - mStartTime;
        } else {
            currentDuration = mDuration;
        }

        return currentDuration;
    }

    /**
     * parse duration(in millis) to normal time format(minite : second.millis like 13 : 25.334)
     * @param duration duration in millis
     * @return normal time format
     */
    private String durationToTimeFormat(long duration) {
        String timeFormat;

        long minute = duration / 60000;
        String strMinute = Long.toString(minute);
        strMinute = strMinute.length() == 1 ? "0" + strMinute : strMinute;

        long second = (duration - minute * 60000) / 1000;
        String strSecond = Long.toString(second);
        strSecond = strSecond.length() == 1 ? "0" + strSecond : strSecond;

        long millis = duration - minute * 60000 - second * 1000;
        String strMillis = Long.toString(millis);
        if (strMillis.length() == 1) {
            strMillis = "00" + strMillis;
        } else if (strMillis.length() == 2) {
            strMillis = "0" + strMillis;
        }

        timeFormat = strMinute + " : " + strSecond + "." + strMillis;

        return timeFormat;
    }

    public String getDurationInTimeFormat() {
        return durationToTimeFormat(calculateCurrentDuration());
    }
}
