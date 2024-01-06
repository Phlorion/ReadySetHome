package com.example.readysethome.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Tenant extends User {
    private ArrayList<BookingRequest> bookingRequests;
    private ArrayList<Booking> bookings;

    public Tenant (String firstName, String lastName, EmailAddress email, Password password, CreditCard creditCard, Date acc_bday) {
        super(firstName, lastName, email, password, creditCard, acc_bday);
        this.bookings=new ArrayList<>();
        this.bookingRequests=new ArrayList<>();
    }

    public Tenant (String f_n, String l_n) {
        super(f_n, l_n);
    }

    public BookingRequest makeBookingRequest(Listing listing, Date checkIn, Date checkOut) {
        Date submit_date = new Date();
        BookingRequest booking_request = new BookingRequest(listing, submit_date, checkIn, checkOut, this);
        // if something went wrong during submit
        if (!booking_request.submit()) {
            cancelBookingRequest(booking_request);
            return null;
        }

        bookingRequests.add(booking_request);

        return booking_request;
    }

    public void cancelBookingRequest(BookingRequest booking_request) {
        booking_request.cancelRequest();
    }


    public void cancelBooking(Booking booking) {
        if (!booking.isActive()) {
            booking.cancel();
            deleteBookingById(booking.getId());
            System.out.println("Booking canceled successfully.");
        }
    }

    public boolean deleteBookingById(int bookingId) {
        boolean state = false;
        for (Iterator<Booking> iterator = bookings.iterator(); iterator.hasNext();) {
            Booking booking = iterator.next();
            if (booking.getId() == bookingId) {
                iterator.remove();
                state = true;
            }
        }
        return state;
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public ArrayList<Booking> getBookings() {
        return this.bookings;
    }

    public void addBookingRequest(BookingRequest bookingRequest) {
        this.bookingRequests.add(bookingRequest);
    }

    public ArrayList<BookingRequest> getBookingRequests() {
        return this.bookingRequests;
    }

    public Booking getBookingById(int id) {
        for (Booking booking : bookings) {
            if (booking.getId() == id) {
                return booking;
            }
        }
        return null;
    }
    public boolean hasSufficientFunds(double amount) {

        if (getCreditCard() != null) {
            return getCreditCard().getBalance() >= amount;
        }
        return false;
    }
}
