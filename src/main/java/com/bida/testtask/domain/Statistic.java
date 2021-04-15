package com.bida.testtask.domain;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class Statistic implements Comparable<Statistic>{

    private List<Point> path;
    private double distanceMeter;
    private long waitingTimeSeconds;
    private Time endWork;

    public Statistic(){
        path = new ArrayList<>();
    }

    public void addPathPoint(Point point){
        this.path.add(point);
    }

    public List<Point> getPath() {
        return path;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }

    public double getDistanceMeter() {
        return distanceMeter;
    }

    public void setDistanceMeter(double distanceMeter) {
        this.distanceMeter = distanceMeter;
    }

    public Time getEndWork() {
        return endWork;
    }

    public void setEndWork(Time endWork) {
        this.endWork = endWork;
    }

    public long getWaitingTimeSeconds() {
        return waitingTimeSeconds;
    }

    public void setWaitingTimeSeconds(long waitingTimeSeconds) {
        this.waitingTimeSeconds = waitingTimeSeconds;
    }

    public int getPathSize(){
        return this.path.size();
    }

    public long getMillisecondsOfEndWork(){
        return this.endWork.getTime();
    }

    public Statistic copy(){
        Statistic statistic = new Statistic();
        statistic.setPath(new ArrayList<>(this.path));
        statistic.setDistanceMeter(this.distanceMeter);
        statistic.setEndWork(this.endWork == null ? null : new Time(this.endWork.getTime()));
        statistic.setWaitingTimeSeconds(this.getWaitingTimeSeconds());
        return statistic;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "path=" + path +
                ", distanceMeter=" + distanceMeter +
                ", waitingTimeSeconds=" + waitingTimeSeconds +
                ", endWork=" + endWork +
                '}';
    }

    public String toStringSpecialVersion() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0 ; i < this.getPathSize(); i++){
            buffer.append(this.getPath().get(i).getId());
            buffer.append(i + 1 == this.getPathSize() ? " " : " => ");
        }
        buffer.append(", Distance: ");
        buffer.append((int) (this.getDistanceMeter() / 1000));
        buffer.append(" km, Waiting Time: ");
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        Time time = new Time(this.getWaitingTimeSeconds());
        buffer.append(time.toString());
        buffer.append(", End of Work: ");
        buffer.append(this.getEndWork().toString());
        buffer.append("\n");
        return buffer.toString();
    }

    @Override
    public int compareTo(Statistic statistic){
        return ((Integer)this.path.size()).compareTo(statistic.path.size());
    }
}
