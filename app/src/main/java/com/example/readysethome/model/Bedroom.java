package com.example.readysethome.model;

import java.io.Serializable;

public class Bedroom implements Serializable {

    private double size;
    private int double_beds;
    private int single_beds;
    private boolean tv;

    public Bedroom(double size, int double_beds, int single_beds, boolean tv) {
        this.size = size;
        this.double_beds = double_beds;
        this.single_beds = single_beds;
        this.tv = tv;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getDouble_beds() {
        return double_beds;
    }

    public void setDouble_beds(int double_beds) {
        this.double_beds = double_beds;
    }

    public int getSingle_beds() {
        return single_beds;
    }

    public void setSingle_beds(int single_beds) {
        this.single_beds = single_beds;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }
}
