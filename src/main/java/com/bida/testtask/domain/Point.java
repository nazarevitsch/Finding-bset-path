package com.bida.testtask.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "points")
public class Point {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private long id;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "delivery_from")
    private Time deliveryFrom;

    @Column(name = "delivery_to")
    private Time deliveryTo;

    @Transient
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Time getDeliveryFrom() {
        return deliveryFrom;
    }

    public void setDeliveryFrom(Time deliveryFrom) {
        this.deliveryFrom = deliveryFrom;
    }

    public Time getDeliveryTo() {
        return deliveryTo;
    }

    public void setDeliveryTo(Time deliveryTo) {
        this.deliveryTo = deliveryTo;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", deliveryFrom=" + deliveryFrom +
                ", deliveryTo=" + deliveryTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return id == point.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
