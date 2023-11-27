package com.example.readysethome;

import java.util.ArrayList;
import java.util.Date;

class Tenant extends User {

    public Tenant(int id, String firstName, String lastName, EmailAddress email, Password password, CreditCard creditCard, Date acc_bday) {
        super(id, firstName, lastName, email, password, creditCard, acc_bday);
    }

    public void makeReservationRequest(Listing listing, Date checkIn, Date checkOut) {
        Date submit_date = new Date();
        BookingRequest bookingRequest = new BookingRequest(listing, submit_date, checkIn, checkOut, ReservationStatus.PENDING, this);
    }

    public void cancelReservation(Booking booking) {

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
