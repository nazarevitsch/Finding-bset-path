package com.bida.testtask.domain;

import java.io.Serializable;
import java.sql.Time;

public class DeliveryBoyConfig implements Serializable {

    private Time startWork;
    private Time endWork;
    private Time timeSpendingAtOrderPoint;
    private int speedKmPerHour;
    private double startLatitude;
    private double startLongitude;

    public DeliveryBoyConfig(){
    }

    public Time getStartWork() {
        return startWork;
    }

    public void setStartWork(Time startWork) {
        this.startWork = startWork;
    }

    public Time getEndWork() {
        return endWork;
    }

    public void setEndWork(Time endWork) {
        this.endWork = endWork;
    }

    public Time getTimeSpendingAtOrderPoint() {
        return timeSpendingAtOrderPoint;
    }

    public void setTimeSpendingAtOrderPoint(Time timeSpendingAtOrderPoint) {
        this.timeSpendingAtOrderPoint = timeSpendingAtOrderPoint;
    }

    public int getSpeedKmPerHour() {
        return speedKmPerHour;
    }

    public void setSpeedKmPerHour(int speedKmPerHour) {
        this.speedKmPerHour = speedKmPerHour;
    }

    public double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(double startLongitude) {
        this.startLongitude = startLongitude;
    }

    @Override
    public String toString() {
        return "DeliveryBoyConfig{" +
                "startWork=" + startWork +
                ", endWork=" + endWork +
                ", timeSpendingAtOrderPoint=" + timeSpendingAtOrderPoint +
                ", speedKmPerHour=" + speedKmPerHour +
                ", startLatitude=" + startLatitude +
                ", startLongitude=" + startLongitude +
                '}';
    }
}
