package com.example.readysethome.model;

import java.util.ArrayList;

public class Apartment {

    private Address location;
    private int floor;
    private double size;
    private boolean wifi;
    private boolean balcony;
    private boolean living_room;
    private ArrayList<Bathroom> bathrooms;
    private ArrayList<Bedroom> bedrooms;
    private ArrayList<Kitchen> kitchens;

    public Apartment(Address location, int floor, double size, boolean wifi, boolean balcony, boolean living_room, ArrayList<Bathroom> bathrooms, ArrayList<Bedroom> bedrooms, ArrayList<Kitchen> kitchens) {
        this.location = location;
        this.floor = floor;
        this.size = size;
        this.wifi = wifi;
        this.balcony = balcony;
        this.living_room = living_room;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.kitchens = kitchens;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public boolean isLiving_room() {
        return living_room;
    }

    public void setLiving_room(boolean living_room) {
        this.living_room = living_room;
    }

    public ArrayList<Bathroom> getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(ArrayList<Bathroom> bathrooms) {
        this.bathrooms = bathrooms;
    }

    public ArrayList<Bedroom> getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(ArrayList<Bedroom> bedrooms) {
        this.bedrooms = bedrooms;
    }

    public ArrayList<Kitchen> getKitchens() {
        return kitchens;
    }

    public void setKitchens(ArrayList<Kitchen> kitchens) {
        this.kitchens = kitchens;
    }
}
