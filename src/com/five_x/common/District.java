package com.five_x.common;

import java.awt.*;

public class District {
    private final String name;
    private final int peopleCount;
    private final double area;
    private final Polygon polygon;

    public District(String name, int peopleCount,double area) {
        this.name = name;
        this.peopleCount = peopleCount;
        this.area = area/1000000;
        this.polygon = new Polygon();
    }

    public String getName() {
        return name;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void addToPolygon(int x,int y){
        polygon.addPoint(x,y);
    }

    public double getArea() {
        return area;
    }

    @Override
    public String toString() {
        return "Квартал{" +
                "име='" + name + '\'' +
                ", брой жители=" + peopleCount +
                ", площ=" + area +
                " кв.км.}";
    }
}
