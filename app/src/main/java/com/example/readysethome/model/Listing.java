package com.example.readysethome.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.time.Instant;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;

public class Listing {
    private static int last_listing_ID = 0;
    private int listing_id;
    private Apartment apartment;
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
    private ArrayList<ChargingPolicy> chargingPolicies = new ArrayList<>();
    private ArrayList<ListingsServices> services = new ArrayList<>();

    public Listing(String title, String description, double price, boolean promoted, double rating, String[] photos, Calendar calendar, Owner owner, Apartment apartment) {
        last_listing_ID++;
        this.listing_id = last_listing_ID;
        this.title = title;
        this.description = description;
        this.price = price;
        this.promoted = promoted;
        this.rating = rating;
        this.photos = photos;
        this.calendar = calendar;
        this.owner = owner;
        this.apartment = apartment;
    }

    public Listing(Listing listing) {
        this.apartment = listing.apartment;
        this.listing_id = listing.listing_id;
        this.title = listing.title;
        this.description = listing.description;
        this.price = listing.price;
        this.promoted = listing.promoted;
        this.rating = listing.rating;
        this.photos = listing.photos;
        this.calendar = listing.calendar;
        this.owner = listing.owner;
        this.original_Price = listing.original_Price;
        this.updated_Price = listing.updated_Price;
        this.chargingPolicies = listing.chargingPolicies;
        this.services = listing.services;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public int getListing_id() {
        return listing_id;
    }

    public void setListing_id(int listing_id) {
        this.listing_id = listing_id;
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

    public void addNewChargingPolicies(ChargingPolicy chargingPolicy) {
        chargingPolicies.add(chargingPolicy);
    }

    public void updatePriceDueToPolicy() {
        setOriginal_Price(getPrice());
        double tmp_Price = getOriginal_Price();
        for (ChargingPolicy chargingPolicy : getChargingPolicies()) {
            tmp_Price += chargingPolicy.getPrice_diff();
        }
        setUpdated_Price(tmp_Price);
        setPrice(getUpdated_Price());
    }

    public void addService(ListingsServices listingsServices) {
        services.add(listingsServices);
    }

    public void updatePriceDueToServices() {
        setOriginal_Price(getPrice());
        double tmp_price = getOriginal_Price();
        for (ListingsServices listingsServices : getServices()) {
            tmp_price += listingsServices.getPrice();
        }
        setUpdated_Price(tmp_price);
        setPrice(getUpdated_Price());
    }
    public void addChargingPolicy(ChargingPolicy cPolicy) {
        if (chargingPolicies.isEmpty()) {
            chargingPolicies.add(cPolicy);
        }
        else {
            boolean toAdd = true;
            for (int i = 0; i < chargingPolicies.size(); i++) {
                // This charging policy has the same description and price as another policy
                boolean sameDetails = (chargingPolicies.get(i).getDescription().equals(cPolicy.getDescription())) && chargingPolicies.get(i).getPrice_diff() == cPolicy.getPrice_diff();
                // This charging policy overlaps with another one
                boolean overlap = (chargingPolicies.get(i).getStart_index().getTime() <= cPolicy.getEnd_index().getTime()) && (chargingPolicies.get(i).getEnd_index().getTime() >= cPolicy.getStart_index().getTime());

                if ((sameDetails || overlap)) {
                    toAdd = false;
                }
            }
            if (toAdd) chargingPolicies.add(cPolicy);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public double calculateAverageOccupancy(YearMonth targetMonth) {
        int totalDays = targetMonth.lengthOfMonth();
        int bookedDays = 0;

        for (Date checkIn : calendar.getAvailability().keySet()) {
            Date checkOut = calendar.getAvailability().get(checkIn);
            YearMonth checkInMonth = YearMonth.from(checkIn.toInstant());
            YearMonth checkOutMonth = YearMonth.from(checkOut.toInstant());

            if (checkInMonth.equals(targetMonth) || checkOutMonth.equals(targetMonth) ||
                    (checkInMonth.isBefore(targetMonth) && checkOutMonth.isAfter(targetMonth))) {
                long daysBetween = daysBetween(checkIn, checkOut);
                bookedDays += daysBetween;
            }
        }

        return (double) bookedDays / totalDays;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public double calculateTotalIncomePerMonth(YearMonth targetMonth) {
        double totalIncome = 0;

        for (Date checkIn : calendar.getAvailability().keySet()) {
            Date checkOut = calendar.getAvailability().get(checkIn);
            YearMonth checkInMonth = YearMonth.from(checkIn.toInstant());
            YearMonth checkOutMonth = YearMonth.from(checkOut.toInstant());

            if (checkInMonth.equals(targetMonth) || checkOutMonth.equals(targetMonth) ||
                    (checkInMonth.isBefore(targetMonth) && checkOutMonth.isAfter(targetMonth))) {
                long daysBetween = daysBetween(checkIn, checkOut);
                totalIncome += daysBetween * getPrice();
            }
        }

        return totalIncome;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int calculateCancellationsPerMonth(YearMonth targetMonth) {
        int cancellations = 0;

        for (Date checkIn : calendar.getAvailability().keySet()) {
            Date checkOut = calendar.getAvailability().get(checkIn);
            YearMonth checkInMonth = YearMonth.from(checkIn.toInstant());
            YearMonth checkOutMonth = YearMonth.from(checkOut.toInstant());

            if ((checkInMonth.equals(targetMonth) || checkOutMonth.equals(targetMonth)) &&
                    checkOut.before(new Date())) { // Assuming cancellations are for past months
                cancellations++;
            }
        }

        return cancellations;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private long daysBetween(Date startDate, Date endDate) {
        return Duration.between(startDate.toInstant(), endDate.toInstant()).toDays();
    }
}
