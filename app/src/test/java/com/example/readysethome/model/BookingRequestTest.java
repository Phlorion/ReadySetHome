package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookingRequestTest {
    private Tenant tenant;
    private Owner owner;
    private Listing listing;
    private BookingRequest b_r;

    @Before
    public void setUp() {
        tenant = new Tenant("John", "Papadopoulos", new EmailAddress("johnpapadop@gmail.com"),
                new Password("j0001"), new CreditCard("1600150014001300", 1000), new Date());
        owner = new Owner("George", "Avrabos", new EmailAddress("georgeavr@gmail.com"),
                new Password("qwerty1234"), new CreditCard("1234123412341234", 0), new Date());
        listing = owner.addListing(new Apartment(), "Apartment", "...", 34, false, new String[5], new ListingsServices[]{new ListingsServices(10, ListingsServicesType.WIFI)});

        Calendar check_in_c = Calendar.getInstance();
        check_in_c.set(2023, 11, 1);
        Date check_in = check_in_c.getTime();
        Calendar check_out_c = Calendar.getInstance();
        check_out_c.set(2023, 11, 13);
        Date check_out = check_out_c.getTime();
        b_r = tenant.makeBookingRequest(listing, check_in, check_out);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void submit() {
        Assert.assertEquals(b_r, owner.getBookingRequests().get(0));
    }

    @Test
    public void submitWhenInsufficientFunds() {
        Tenant tenant1 = new Tenant("John", "Papadopoulos", new EmailAddress("johnpapadop@gmail.com"),
                new Password("j0001"), new CreditCard("1600150014001300", -1), new Date());
        BookingRequest newBR = new BookingRequest(listing, new Date(), new Date(), new Date(), tenant1);
        assertFalse(newBR.submit());
    }

    @Test
    public void cancelRequest() {
        tenant.cancelBookingRequest(b_r);
        Assert.assertEquals(0, owner.getBookingRequests().size());
    }

    @Test
    public void confirm() {
        double tenant_balance = tenant.getCreditCard().getBalance();
        double owner_balance = owner.getCreditCard().getBalance();

        // the booking request is removed from owners pending
        owner.confirmBookingRequest(b_r);
        Assert.assertEquals(0, owner.getBookingRequests().size());

        // the apartment is unavailable for this time period
        Calendar other_check_in_c = Calendar.getInstance();
        other_check_in_c.set(2023, 10, 23);
        Date other_check_in = other_check_in_c.getTime();
        Calendar other_check_out_c = Calendar.getInstance();
        other_check_out_c.set(2023, 11, 5);
        Date other_check_out = other_check_out_c.getTime();
        assertFalse(listing.getCalendar().isAvailable(other_check_in, other_check_out));

        // upfront payment is correct
        double upfront = 0.2 * (12 * listing.getPrice());
        Assert.assertEquals(tenant_balance - upfront, tenant.getCreditCard().getBalance(), 0.001);
        Assert.assertEquals(owner_balance + upfront, owner.getCreditCard().getBalance(), 0.001);
    }

    @Test
    public void confirmWhenInsufficientFunds() {
        Tenant tenant1 = new Tenant("John", "Papadopoulos", new EmailAddress("johnpapadop@gmail.com"),
                new Password("j0001"), new CreditCard("1600150014001300", -1), new Date());
        BookingRequest newBR = new BookingRequest(listing, new Date(), new Date(), new Date(), tenant1);
        assertFalse(newBR.confirm());
    }

    @Test
    public void declineRequest() {
        Calendar check_in_c = Calendar.getInstance();
        check_in_c.set(2023, 11, 1);
        Date check_in = check_in_c.getTime();
        Calendar check_out_c = Calendar.getInstance();
        check_out_c.set(2023, 11, 13);
        Date check_out = check_out_c.getTime();

        owner.declineBookingRequest(b_r);
        Assert.assertEquals(0, owner.getBookingRequests().size());
        assertTrue(listing.getCalendar().isAvailable(check_in, check_out));
    }

    @Test
    public void bookingID() {
        BookingRequest new_bookingRequest = new BookingRequest(listing, new Date(), new Date(), new Date(), tenant);
        Assert.assertEquals(new_bookingRequest.getBooking_id(), b_r.getBooking_id() + 1);

        b_r.setBooking_id(100);
        Assert.assertEquals(100, b_r.getBooking_id());
    }

    @Test
    public void submissionDate() {
        Calendar sub_date_c = Calendar.getInstance();
        Date sub_date = sub_date_c.getTime();
        Assert.assertEquals(sub_date, b_r.getSubmission_date());

        Calendar new_sub_date_c = Calendar.getInstance();
        new_sub_date_c.set(2023, 9, 20);
        Date new_sub_date = new_sub_date_c.getTime();
        b_r.setSubmission_date(new_sub_date);
        Assert.assertEquals(new_sub_date, b_r.getSubmission_date());
    }

    @Test
    public void checkIn_CheckOut() {
        Calendar check_in_c = Calendar.getInstance();
        check_in_c.set(2023, 11, 1);
        Date check_in = check_in_c.getTime();
        Calendar check_out_c = Calendar.getInstance();
        check_out_c.set(2023, 11, 13);
        Date check_out = check_out_c.getTime();
        Assert.assertEquals(check_in, b_r.getCheck_in());
        Assert.assertEquals(check_out, b_r.getCheck_out());

        Calendar other_check_in_c = Calendar.getInstance();
        other_check_in_c.set(2023, 10, 23);
        Date other_check_in = other_check_in_c.getTime();
        b_r.setCheck_in(other_check_in);
        Calendar other_check_out_c = Calendar.getInstance();
        other_check_out_c.set(2023, 11, 5);
        Date other_check_out = other_check_out_c.getTime();
        b_r.setCheck_out(other_check_out);
        Assert.assertEquals(other_check_in, b_r.getCheck_in());
        Assert.assertEquals(other_check_out, b_r.getCheck_out());
    }

    @Test
    public void bookingStatus() {
        Assert.assertEquals(ReservationStatus.PENDING, b_r.getBooking_status());

        b_r.setBooking_status(ReservationStatus.CONFIRMED);
        Assert.assertEquals(ReservationStatus.CONFIRMED, b_r.getBooking_status());
    }

    @Test
    public void get_set_listing() {
        Assert.assertEquals(listing, b_r.getListing());

        Listing new_l = new Listing();
        b_r.setListing(new_l);
        Assert.assertEquals(new_l, b_r.getListing());
    }

    @Test
    public void get_set_tenant() {
        Assert.assertEquals(tenant, b_r.getTenant());

        Tenant new_t = new Tenant("Mitsos", "Mitsaras");
        b_r.setTenant(new_t);
        Assert.assertEquals(new_t, b_r.getTenant());
    }

}