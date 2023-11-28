package com.example.readysethome;

import java.util.ArrayList;
import java.util.Date;

public class Owner extends User {
    ArrayList<BookingRequest> pendingBookingRequests;

    public Owner(String firstName, String lastName, EmailAddress email, Password password, CreditCard creditCard, Date acc_bday) {
        super(firstName, lastName, email, password, creditCard, acc_bday);
        pendingBookingRequests = new ArrayList<>();
    }

    public void confirmBookingRequest(BookingRequest booking_request) {
        booking_request.confirm();
    }

    public void declineBookingRequest(BookingRequest booking_request) {
        booking_request.confirm();
    }

    // Owner adds a new Listing for an apartment to his name
    public Listing addListing(Apartment ap, String title, String description, double price, boolean promoted, String[] photos) {
        return new Listing(title, description, price, promoted, 0, photos, new Calendar(), this);
    }

    public ArrayList<BookingRequest> getBookingRequests() {
        return pendingBookingRequests;
    }

    public void setBookingRequests(ArrayList<BookingRequest> bookingRequests) {
        this.pendingBookingRequests = bookingRequests;
    }
}
