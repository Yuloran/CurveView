package com.yuloran.demo.model.bean;

/**
 * Author & Date: Yuloran, 2017/9/10 13:57
 * Function:
 */

public class HourWeather implements Comparable<HourWeather> {
    private int mTemperature;
    private int mHour;

    public HourWeather() {
    }

    public HourWeather(int temperature, int hour) {
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
    public int compareTo(HourWeather o) {
        return mTemperature - o.getTemperature();
    }

    @Override
    public String toString() {
        return "HourWeather{" + "mTemperature=" + mTemperature + ", mHour=" + mHour + '}';
    }
}
