package com.bida.testtask.domain;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

public class BestResult {
    private Statistic bestDistance;
    private Statistic bestWaitingTime;
    private Statistic bestEndTime;

    public Statistic getBestDistance() {
        return bestDistance;
    }

    public void setBestDistance(Statistic bestDistance) {
        this.bestDistance = bestDistance;
    }

    public Statistic getBestWaitingTime() {
        return bestWaitingTime;
    }

    public void setBestWaitingTime(Statistic bestWaitingTime) {
        this.bestWaitingTime = bestWaitingTime;
    }

    public Statistic getBestEndTime() {
        return bestEndTime;
    }

    public void setBestEndTime(Statistic bestEndTime) {
        this.bestEndTime = bestEndTime;
    }

    @Override
    public String toString() {
        return "Best distance: " + bestDistance.toStringSpecialVersion() +
                "Best Waiting Time: " + bestWaitingTime.toStringSpecialVersion() +
                "Best End Time: " + bestEndTime.toStringSpecialVersion();
    }
}
