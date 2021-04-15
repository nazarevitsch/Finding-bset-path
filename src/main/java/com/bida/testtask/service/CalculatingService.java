package com.bida.testtask.service;

import com.bida.testtask.domain.BestResult;
import com.bida.testtask.domain.DeliveryBoyConfig;
import com.bida.testtask.domain.Point;
import com.bida.testtask.domain.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculatingService {

    @Autowired
    private PointService pointService;

    @Autowired
    private DeliveryBoyService deliveryBoyService;

    private List<Statistic> statistics = new ArrayList<>();

    public BestResult calculate() {
        List<Point> points = pointService.getAllPoints();
        DeliveryBoyConfig deliveryBoy = deliveryBoyService.readFromJSON();

        Point deliveryBoyPoint = new Point();
        deliveryBoyPoint.setLongitude(deliveryBoy.getStartLongitude());
        deliveryBoyPoint.setLatitude(deliveryBoy.getStartLatitude());

        for (int i = 0; i < points.size(); i++) {
            Statistic statistic = new Statistic();
            double distanceMeter = calculateDistance(deliveryBoyPoint, points.get(i));
            long timeToPoint = (long) ((distanceMeter / (deliveryBoy.getSpeedKmPerHour() * 1000)) * 3600);
            Time currentTime = new Time(deliveryBoy.getStartWork().getTime() + timeToPoint * 1000);
            if (currentTime.getTime() > deliveryBoy.getEndWork().getTime()){
                break;
            }

            if (currentTime.getTime() < points.get(i).getDeliveryTo().getTime()) {
                if (points.get(i).getDeliveryFrom().getTime() - currentTime.getTime() > 0) {
                    statistic.setWaitingTimeSeconds(statistic.getWaitingTimeSeconds() + points.get(i).getDeliveryFrom().getTime() - currentTime.getTime());
                    currentTime.setTime(points.get(i).getDeliveryFrom().getTime());
                }
                statistic.setDistanceMeter(distanceMeter);
                statistic.addPathPoint(points.get(i));
            } else {
                continue;
            }
            calculateRecursive(points, statistic, currentTime, deliveryBoy);
        }
        return findBestResults();
    }

    private void calculateRecursive(List<Point> points, Statistic statistic, Time currentTime, DeliveryBoyConfig deliveryBoy) {
        boolean flag1 = true;
        boolean flag2 = true;
        for (int i = 0; i < points.size(); i++) {
            if (!statistic.getPath().contains(points.get(i))) {
                flag1 = false;
                double distanceMeter = calculateDistance(statistic.getPath().get(statistic.getPath().size() - 1), points.get(i));
                long timeToPoint = (long) ((distanceMeter / (deliveryBoy.getSpeedKmPerHour() * 1000)) * 3600);

                currentTime.setTime(currentTime.getTime() + timeToPoint * 1000);

                if (currentTime.getTime() > deliveryBoy.getEndWork().getTime()){
                 statistics.add(statistic);
                 continue;
                }
                if (currentTime.getTime() < points.get(i).getDeliveryTo().getTime()) {
                    flag2 = false;
                    if (points.get(i).getDeliveryFrom().getTime() - currentTime.getTime() > 0) {
                        statistic.setWaitingTimeSeconds(points.get(i).getDeliveryFrom().getTime() - currentTime.getTime());
                        currentTime.setTime(points.get(i).getDeliveryFrom().getTime());
                    }
                    statistic.setDistanceMeter(statistic.getDistanceMeter() + distanceMeter);
                    statistic.addPathPoint(points.get(i));
                    statistic.setEndWork(currentTime);
                } else {
                    continue;
                }
                calculateRecursive(points, statistic.copy(), currentTime, deliveryBoy);
            }
        }
        if (flag1 || flag2) {
//            statistic.setEndWork(currentTime);
            statistics.add(statistic);
        }
    }

    private BestResult findBestResults() {
        BestResult bestResult = new BestResult();
        statistics.sort(Comparator.comparing(Statistic::getPathSize));
        List<Statistic> best = statistics.stream().filter(el -> el.getPathSize() == statistics.get(statistics.size() - 1).getPathSize()).collect(Collectors.toList());
        for (Statistic s : best) System.out.println(s);
        best.sort(Comparator.comparing(Statistic::getDistanceMeter));
        bestResult.setBestDistance(best.get(0));
        best.sort(Comparator.comparing(Statistic::getWaitingTimeSeconds));
        bestResult.setBestWaitingTime(best.get(0));
        best.sort(Comparator.comparing(Statistic::getMillisecondsOfEndWork));
        bestResult.setBestEndTime(best.get(0));
        return bestResult;
    }

    private double calculateDistance(Point p1, Point p2){
        double R = 6371e3;
        double φ1 = p1.getLatitude() * Math.PI/180;
        double φ2 = p2.getLatitude() * Math.PI/180;
        double Δφ = (p2.getLatitude() - p1.getLatitude()) * Math.PI/180;
        double Δλ = (p2.getLongitude() - p1.getLongitude()) * Math.PI/180;

        double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                    Math.cos(φ1) * Math.cos(φ2) *
                            Math.sin(Δλ/2) * Math.sin(Δλ/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }
}
