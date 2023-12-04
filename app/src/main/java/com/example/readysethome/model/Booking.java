package com.example.readysethome.model;

import java.util.Date;

class Booking {
    private int id;
    private Date checkIn;
    private Date checkOut;
    private Tenant tenant;
    private Listing listing;

    private boolean isCancelled = false;

    int apartmentRating=-1;
    public Booking(int id, Date checkIn, Date checkOut, Tenant tenant, Listing listing) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.tenant = tenant;
        this.listing = listing;
    }
    //methodos elegxou na yparxei energh krathsh gia mia xronikh periodo
    // TO DO


    /*public boolean isActive() {
        //Date currentDate= get todays date //TO DO
        // return currentDate.after(checkIn) && currentDate.before(checkOut);
    }*/
    public void rateApartment(int rating) {
        if (isStayCompleted()) {
            apartmentRating = rating;
            System.out.println("Apartment rated with " + rating + " stars.");
        } else {
            System.out.println("Cannot rate the apartment before the stay is completed.");
        }
    }

    // Method to check if stay is completed
    private boolean isStayCompleted() {
        Date currentDate = new Date();
        return currentDate.after(checkOut);
    }

    public int getApartmentRating() {
        return apartmentRating;
    }

    public boolean isCancelled() {
        return isCancelled;
    }
    public void cancel() {
        if (!isCancelled) {
            isCancelled = true;
            System.out.println("Booking canceled successfully.");
        } else {
            System.out.println("Booking is already canceled.");
        }
    }

// Setters kai getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public User getTenant() {
        return this.tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Listing getListing() {
        return this.listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }
}
