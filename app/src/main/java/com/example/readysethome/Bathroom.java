package com.example.readysethome;

public class Bathroom {

    private double size;
    private boolean shower;
    private boolean toilet;
    private boolean hairdryer;

    public Bathroom(double size, boolean shower, boolean toilet, boolean hairdryer) {
        this.size = size;
        this.shower = shower;
        this.toilet = toilet;
        this.hairdryer = hairdryer;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean isShower() {
        return shower;
    }

    public void setShower(boolean shower) {
        this.shower = shower;
    }

    public boolean isToilet() {
        return toilet;
    }

    public void setToilet(boolean toilet) {
        this.toilet = toilet;
    }

    public boolean isHairdryer() {
        return hairdryer;
    }

    public void setHairdryer(boolean hairdryer) {
        this.hairdryer = hairdryer;
    }
}
