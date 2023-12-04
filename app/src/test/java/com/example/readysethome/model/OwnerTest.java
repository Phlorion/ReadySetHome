package com.example.readysethome.model;

import static org.junit.Assert.*;

import android.widget.ListAdapter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OwnerTest {
    private Tenant tenant;
    private Owner owner;
    private Listing listing;

    @Before
    public void setUp() {
        tenant = new Tenant("John", "Papadopoulos", new EmailAddress("johnpapadop@gmail.com"),
                new Password("j0001"), new CreditCard("1600150014001300", 1000), new Date());
        owner = new Owner("George", "Avrabos", new EmailAddress("georgeavr@gmail.com"),
                new Password("qwerty1234"), new CreditCard("1234123412341234"), new Date());
        listing = owner.addListing(new Apartment(), "Apartment", "...", 34, false, new String[5], new ListingsServices[]{new ListingsServices(10, ListingsServicesType.WIFI)});
    }

    @After
    public void tearDown() {
    }

    @Test
    public void confirmBookingRequest() {
        BookingRequest b_r = tenant.makeBookingRequest(listing, new Date(), new Date());

        assertTrue(owner.confirmBookingRequest(b_r));
        Assert.assertEquals(ReservationStatus.CONFIRMED, b_r.getBooking_status());
        Assert.assertEquals(0, owner.getBookingRequests().size());
    }

    @Test
    public void confirmBookingRequestWhenTenantInsufficientFunds() {
        Tenant tenant1 = new Tenant("John", "Papadopoulos", new EmailAddress("johnpapadop@gmail.com"),
                new Password("j0001"), new CreditCard("1600150014001300", -1), new Date());
        BookingRequest newBR = new BookingRequest(listing, new Date(), new Date(), new Date(), tenant1);
        assertFalse(owner.confirmBookingRequest(newBR));
        Assert.assertEquals(ReservationStatus.DECLINED, newBR.getBooking_status());
        Assert.assertEquals(0, owner.getBookingRequests().size());
    }

    @Test
    public void declineBookingRequest() {
        BookingRequest b_r = tenant.makeBookingRequest(listing, new Date(), new Date());

        owner.declineBookingRequest(b_r);
        Assert.assertEquals(ReservationStatus.DECLINED, b_r.getBooking_status());
        Assert.assertEquals(0, owner.getBookingRequests().size());
    }

    @Test
    public void updateListing() {
        Listing newListing = owner.updateListing(listing, "Ap", "...", 32, true, new String[5], new ListingsServices[]{new ListingsServices(10, ListingsServicesType.CLEANING_SERVICE)});
        Assert.assertEquals(newListing.getApartment_id(), listing.getApartment_id());
    }

    @Test
    public void removeListing() {
        ArrayList<Listing> listings = new ArrayList<>();
        Listing new_l = owner.addListing(new Apartment(), "Ap", "...", 32, true, new String[5], new ListingsServices[]{new ListingsServices(10, ListingsServicesType.CLEANING_SERVICE)});
        listings.add(listing);
        listings.add(new_l);
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking(1, new Date(), new Date(), tenant, listing));
        bookings.add(new Booking(2, new Date(), new Date(), tenant, listing));
        bookings.add(new Booking(3, new Date(), new Date(), tenant, new_l));

        owner.removeListing(listing, listings, bookings);
        Assert.assertEquals(1, listings.size());
        Assert.assertEquals(1, bookings.size());

    }

    @Test
    public void setBookingRequests() {
        BookingRequest b_r = tenant.makeBookingRequest(listing, new Date(), new Date());
        ArrayList<BookingRequest> new_pend = new ArrayList<>();
        new_pend.add(b_r);

        owner.setBookingRequests(new_pend);
        Assert.assertEquals(new_pend, owner.getBookingRequests());
    }
}