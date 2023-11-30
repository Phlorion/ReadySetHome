package com.example.readysethome;

import java.util.ArrayList;
import java.util.Date;

public class Owner extends User {
    ArrayList<BookingRequest> pendingBookingRequests;

    public Owner(String firstName, String lastName, EmailAddress email, Password password, CreditCard creditCard, Date acc_bday) {
        super(firstName, lastName, email, password, creditCard, acc_bday);
        pendingBookingRequests = new ArrayList<>();
    }

    // add booking request to pendingBookingRequests
    public void addToPending(BookingRequest booking_request) {
        pendingBookingRequests.add(booking_request);
    }

    // remove booking request from pendingBookingRequests
    public void removeFromPending(BookingRequest booking_request) {
        pendingBookingRequests.remove(booking_request);
    }

    // owner confirms a booking request
    public void confirmBookingRequest(BookingRequest booking_request) {
        booking_request.confirm();
        removeFromPending(booking_request);
    }

    // owner declines a booking request
    public void declineBookingRequest(BookingRequest booking_request) {
        booking_request.declineRequest();
        removeFromPending(booking_request);
    }

    // Owner adds a new Listing for an apartment to his name
    public Listing addListing(Apartment ap, String title, String desc, double price, boolean promoted, String[] photos) {
        return new Listing(title, desc, price, promoted, 0, photos, new Calendar(), this);
    }

    public ArrayList<BookingRequest> getBookingRequests() {
        return pendingBookingRequests;
    }

    public void setBookingRequests(ArrayList<BookingRequest> bookingRequests) {
        this.pendingBookingRequests = bookingRequests;
    }
}
