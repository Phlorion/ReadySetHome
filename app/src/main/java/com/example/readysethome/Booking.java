package com.example.readysethome;

import java.util.Date;

class Booking {
    private int id;
    private Date checkIn;
    private Date checkOut;
    private User renter;
    private Apartment apartment;

    public Booking(int id, Date checkIn, Date checkOut, User renter, Apartment apartment) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.renter = renter;
        this.apartment = apartment;
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

    public User getRenter() {
        return renter;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
