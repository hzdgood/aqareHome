package com.aqara.common.entity;

//地理位置
public class Coordinate {
    private double lng = 0;
    private double lat = 0;

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
