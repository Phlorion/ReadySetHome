package com.example.readysethome;

import java.util.ArrayList;
import java.util.Date;

class Tenant extends User {
    private ArrayList<BookingRequest> bookingRequests;
    private ArrayList<Booking> bookings;
    public Tenant(String firstName, String lastName, EmailAddress email, Password password, CreditCard creditCard, Date acc_bday) {
        super(firstName, lastName, email, password, creditCard, acc_bday);
    }

    public BookingRequest makeBookingRequest(Listing listing, Date checkIn, Date checkOut) {
        Date submit_date = new Date();
        BookingRequest booking_request = new BookingRequest(listing, submit_date, checkIn, checkOut, this);
        booking_request.submit();
        return booking_request;
    }

    public void cancelBookingRequest(BookingRequest booking_request) {
        booking_request.cancelRequest();
    }

    public void cancelBooking(Booking booking) {

        if (!booking.isActive()) {

            booking.cancel();  // Set the booking status to canceled or update other relevant fields
            System.out.println("Booking canceled successfully.");
        } else {
            System.out.println("Cant cancel booking.");
        }
    }

    public void deleteBooking(int bookingId) {
        /*Iterator<Date> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (booking.getId() == bookingId) {
                iterator.remove();
                break;
            }
        }*/
    }

    public void viewBookings() {
        /*for (Booking booking : bookings) {
            System.out.println(booking);
        }*/
    }
}
