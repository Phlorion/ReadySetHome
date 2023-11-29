package com.example.readysethome;

import java.util.ArrayList;

public class Listing {
    private static int last_apartment_ID = 0;
    private int apartment_id;
    private String title;
    private String description;
    private double price;
    private boolean promoted;
    private double rating;
    private String[] photos;
    private Calendar calendar;
    private Owner owner;

    private ArrayList<ChargingPolicy> chargingPolicies;

    public Listing(String title, String description, double price, boolean promoted, double rating, String[] photos, Calendar calendar, Owner owner) {
        last_apartment_ID++;
        this.apartment_id = last_apartment_ID;
        this.title = title;
        this.description = description;
        this.price = price;
        this.promoted = promoted;
        this.rating = rating;
        this.photos = photos;
        this.calendar = calendar;
        this.owner = owner;
    }

    public int getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(int apartment_id) {
        this.apartment_id = apartment_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public ArrayList<ChargingPolicy> getChargingPolicies() {
        return chargingPolicies;
    }

    public void setChargingPolicies(chargingPolicy) {
        chargingPolicies.add(chargingPolicy);
    }
}
