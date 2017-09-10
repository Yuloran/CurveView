package com.yuloran.curveview.model.bean;

/**
 * Author & Date: Yuloran, 2017/9/10 13:57
 * Function:
 */

public class HourTemperature implements Comparable<HourTemperature> {
    private int mTemperature;
    private int mHour;

    public HourTemperature() {
    }

    public HourTemperature(int temperature, int hour) {
        this.mTemperature = temperature;
        this.mHour = hour;
    }

    public int getTemperature() {
        return mTemperature;
    }

    public void setTemperature(int temperature) {
        this.mTemperature = temperature;
    }

    public int getHour() {
        return mHour;
    }

    public void setHour(int hour) {
        this.mHour = hour;
    }

    @Override
    public int compareTo(HourTemperature o) {
        return mTemperature - o.getTemperature();
    }

    @Override
    public String toString() {
        return "HourTemperature{" +
                "mTemperature=" + mTemperature +
                ", mHour=" + mHour +
                '}';
    }
}
