package com.example.readysethome.model;

import java.util.ArrayList;
import java.util.List;

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
    private double original_Price;
    private double updated_Price;
    private ArrayList<ChargingPolicy> chargingPolicies;
    private ArrayList<ListingsServices> services;
    private double monthlyIncome;
    private double monthlyOccupancy;

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

        this.services = new ArrayList<>();
        this.chargingPolicies = new ArrayList<>();
    }

    // debugging
    public Listing() {}

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

    public void setChargingPolicies(ArrayList<ChargingPolicy> chargingPolicies) {
        this.chargingPolicies = chargingPolicies;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public double getMonthlyOccupancy() {
        return monthlyOccupancy;
    }

    public void setMonthlyOccupancy(double monthlyOccupancy) {
        this.monthlyOccupancy = monthlyOccupancy;
    }

    public double getOriginal_Price() {
        return original_Price;
    }

    public void setOriginal_Price(double original_Price) {
        this.original_Price = original_Price;
    }

    public double getUpdated_Price() {
        return updated_Price;
    }

    public void setUpdated_Price(double updated_Price) {
        this.updated_Price = updated_Price;
    }

    public ArrayList<ListingsServices> getServices() {
        return services;
    }

    public void setServices(ArrayList<ListingsServices> services) {
        this.services = services;
    }

    public void addChargingPolicies(ChargingPolicy chargingPolicy) {
        chargingPolicies.add(chargingPolicy);
    }

    // Method to get the price after the charging policies are applied
    public void updatePriceDueToPolicy() {
        double tmp_Price = getOriginal_Price();
        for (ChargingPolicy chargingPolicy : getChargingPolicies()) {
            tmp_Price += chargingPolicy.getPrice_diff();
        }
        setUpdated_Price(tmp_Price + getUpdated_Price());
    }

    public void addService(ListingsServices listingsServices) {
        services.add(listingsServices);
    }

    // Method to get the price after the services are applied
    public void updatePriceDueToServices() {
        double tmp_price = getOriginal_Price();
        for (ListingsServices listingsServices : getServices()) {
            tmp_price += listingsServices.getPrice();
        }
        setUpdated_Price(tmp_price + getUpdated_Price());
    }
}