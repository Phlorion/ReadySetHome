package com.example.readysethome.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TenantTest {
    private Tenant tenant;
    private Tenant tenant2;
    private Listing listing;
    private BookingRequest bookingRequest;
    private Booking booking;

    @Before
    public void setUp() {
        // Set up Tenant, Listing, BookingRequest, and Booking instances
        tenant = new Tenant("John", "Doe", new EmailAddress("john.doe@example.com"),
                new Password("password123"), new CreditCard("1234567890123456", 10000), new Date());
        tenant2 = new Tenant("asdga", "sdjghsg");
        listing = new Listing("Cozy Apartment", "A nice place to stay", 50.0, false, 4.5, new int[]{1, 2},
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
    public void makeBookingRequestWhenInsufficientFunds() {
        Tenant tenant = new Tenant("John", "Doe", new EmailAddress("john.doe@example.com"),
                new Password("password123"), new CreditCard("1234567890123456", 0), new Date());
        Date checkIn = new Date();
        Date checkOut = new Date(checkIn.getTime() + 86400000);  // 1 day later
        BookingRequest newBookingRequest = tenant.makeBookingRequest(listing, checkIn, checkOut);
        assertNull(newBookingRequest);
    }


    @Test
    public void cancelBookingRequest() {

        assertTrue(tenant.getBookingRequests().contains(bookingRequest));
        tenant.cancelBookingRequest(bookingRequest);
        assertFalse(tenant.getBookingRequests().contains(bookingRequest));
    }
    @Test
    public void cancelBooking() {
        Booking newBooking = new Booking(new BookingRequest(listing, new Date(), new Date(), new Date(), tenant));
        tenant.addBooking(newBooking);
        assertTrue(tenant.getBookings().contains(newBooking));
        tenant.cancelBooking(newBooking);
        assertFalse(tenant.getBookings().contains(newBooking));
    }

    @Test
    public void deleteBooking() {
        assertTrue(tenant.deleteBookingById(booking.getId()));
        assertFalse(tenant.deleteBookingById(booking.getId()));
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

    @Test
    public void getBookingByID() {
        BookingRequest br = tenant.makeBookingRequest(listing, new Date(), new Date());
        br.confirm();
        Booking b = new Booking(br);
        int id = br.getBooking_id();
        Booking b2 = tenant.getBookingById(id);
        assertEquals(b.getId(), b2.getId());
    }

    @Test
    public void getBookingByIDButBookingDoesntExist() {
        BookingRequest br = tenant.makeBookingRequest(listing, new Date(), new Date());
        Booking b = new Booking(br);
        int id = br.getBooking_id();
        assertNull(tenant.getBookingById(id));
    }

    @Test
    public void hasSufficientFundsTest() {
        Assert.assertTrue(tenant.hasSufficientFunds(1));
    }
}
