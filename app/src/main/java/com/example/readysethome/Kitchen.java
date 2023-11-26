package com.example.readysethome;

public class Kitchen {

    private double size;
    private boolean oven;
    private boolean microwave;
    private boolean refrigerator;
    private boolean toaster;
    private boolean coffee_machine;
    private boolean dining_table;

    public Kitchen(double size, boolean oven, boolean microwave, boolean refrigerator, boolean toaster, boolean coffee_machine, boolean dining_table) {
        this.size = size;
        this.oven = oven;
        this.microwave = microwave;
        this.refrigerator = refrigerator;
        this.toaster = toaster;
        this.coffee_machine = coffee_machine;
        this.dining_table = dining_table;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean isOven() {
        return oven;
    }

    public void setOven(boolean oven) {
        this.oven = oven;
    }

    public boolean isMicrowave() {
        return microwave;
    }

    public void setMicrowave(boolean microwave) {
        this.microwave = microwave;
    }

    public boolean isRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(boolean refrigerator) {
        this.refrigerator = refrigerator;
    }

    public boolean isToaster() {
        return toaster;
    }

    public void setToaster(boolean toaster) {
        this.toaster = toaster;
    }

    public boolean isCoffee_machine() {
        return coffee_machine;
    }

    public void setCoffee_machine(boolean coffee_machine) {
        this.coffee_machine = coffee_machine;
    }

    public boolean isDining_table() {
        return dining_table;
    }

    public void setDining_table(boolean dining_table) {
        this.dining_table = dining_table;
    }
}
