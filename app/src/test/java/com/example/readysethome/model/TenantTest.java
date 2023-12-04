package com.example.readysethome.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TenantTest {
    private Tenant tenant;
    private Listing listing;
    private BookingRequest bookingRequest;
    private Booking booking;

    @Before
    public void setUp() {
        // Set up Tenant, Listing, BookingRequest, and Booking instances
        tenant = new Tenant("John", "Doe", new EmailAddress("john.doe@example.com"),
                new Password("password123"), new CreditCard("1234567890123456"), new Date());
        listing = new Listing("Cozy Apartment", "A nice place to stay", 50.0, false, 4.5, new String[]{"photo1", "photo2"},
                new Calendar(), new Owner("Owner", "Smith", new EmailAddress("owner@example.com"),
                new Password("ownerPass"), new CreditCard("9876543210987654"), new Date()), new Apartment());
        Date checkIn = new Date();
        Date checkOut = new Date(checkIn.getTime() + 86400000);  // 1 day later
        bookingRequest = new BookingRequest(listing, new Date(), checkIn, checkOut, tenant);
        booking = new Booking(bookingRequest);
        tenant.addBookingRequest(bookingRequest);
        tenant.addBooking(booking);
    }

    @Test
    public void makeBookingRequest() {
        // Test the makeBookingRequest method
        Date checkIn = new Date();
        Date checkOut = new Date(checkIn.getTime() + 86400000);  // 1 day later
        BookingRequest newBookingRequest = tenant.makeBookingRequest(listing, checkIn, checkOut);

        assertNotNull(newBookingRequest);

        // Check if the bookingRequests list contains the newBookingRequest
        assertTrue(tenant.getBookingRequests().contains(newBookingRequest));
    }


    @Test
    public void cancelBookingRequest() {

        assertTrue(tenant.getBookingRequests().contains(bookingRequest));
        tenant.cancelBookingRequest(bookingRequest);
        assertFalse(tenant.getBookingRequests().contains(bookingRequest));
    }
    @Test
    public void cancelBooking() {
        assertTrue(tenant.getBookings().contains(booking));
        tenant.cancelBooking(booking);
        assertFalse(tenant.getBookings().contains(booking));
    }

    @Test
    public void deleteBooking() {
        assertTrue(tenant.getBookings().contains(booking));
        tenant.deleteBookingById(booking.getId());
        assertFalse(tenant.getBookings().contains(booking));
    }

    @Test
    public void addBooking() {

        Booking newBooking = new Booking(new BookingRequest(listing, new Date(), new Date(), new Date(), tenant));
        tenant.addBooking(newBooking);
        assertTrue(tenant.getBookings().contains(newBooking));
    }

    @Test
    public void getBookings() {
        Booking newBooking = new Booking(new BookingRequest(listing, new Date(), new Date(), new Date(), tenant));
        tenant.addBooking(newBooking);
        ArrayList<Booking> bookings = tenant.getBookings();
        assertNotNull(bookings);
        assertTrue(bookings.contains(newBooking));
    }
}
