package com.example.readysethome;

import java.util.Date;

class Booking {
    private int id;
    private Date checkIn;
    private Date checkOut;
    private Tenant tenant;
    private Listing listing;

    public Booking(int id, Date checkIn, Date checkOut, Tenant tenant, Listing listing) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.tenant = tenant;
        this.listing = listing;
    }

    public void rateApartment(int rating) {}

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

    public Tenant getTenant() {
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
