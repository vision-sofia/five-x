package com.five_x.common;

public class Bus {
    private int numberOfBus;
    private String busPath;
    private int capacity;


    public Bus(int numberOfBus, String busPath, int capacity) {
        this.numberOfBus = numberOfBus;
        this.busPath = busPath;
        this.capacity = capacity;
    }

    public int getNumberOfBus() {
        return numberOfBus;
    }

    public String getBusPath() {
        return busPath;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return numberOfBus + " " + busPath + " " + capacity;
    }
}
