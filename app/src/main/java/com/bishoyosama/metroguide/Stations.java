package com.bishoyosama.metroguide;

import java.util.List;

public class Stations {


    String station_name;
    int id;
    double stalat,stalong;

    public double getStalat() {
        return stalat;
    }

    public void setStalat(double stalat) {
        this.stalat = stalat;
    }

    public double getStalong() {
        return stalong;
    }

    public void setStalong(double stalong) {
        this.stalong = stalong;
    }
    public String getName() {
        return station_name;
    }

    public void setName(String station_name) {
        this.station_name = station_name;
    }

    public int getAge() {
        return id;
    }

    public void setAge(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Stations{" +
                "station_name='" + station_name + '\'' +
                ", id=" + id +
                ", lat=" + stalat +
                ", long=" + stalong +
                '}';
    }
}
