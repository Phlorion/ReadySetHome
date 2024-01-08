package com.example.readysethome.model;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class BookingTest {
    private Tenant tenant;
    private Owner owner;
    private Listing listing;
    private Booking booking;

    @Before
    public void setUp() {

        tenant = new Tenant("John", "Papadopoulos", new EmailAddress("johnpapadop@gmail.com"),
                new Password("j0001"), new CreditCard("1600150014001300", 1000), new Date());


        owner = new Owner("Anestis", "Thanasi", new EmailAddress("thanasianestis@gmail.com"),
                new Password("qwerty1234"), new CreditCard("1234123412341234"), new Date());


        listing = owner.addListing(new Apartment(), "Apartment", "...", 34, false,
                new int[5], new ListingsServices[]{new ListingsServices(10, ListingsServicesType.WIFI)});


        Calendar checkInCalendar = Calendar.getInstance();
        checkInCalendar.set(2023, 11, 1);
        Date checkIn = checkInCalendar.getTime();

        Calendar checkOutCalendar = Calendar.getInstance();
        checkOutCalendar.set(2023, 11, 13);
        Date checkOut = checkOutCalendar.getTime();


        booking = new Booking(new BookingRequest(listing,new Date(),checkIn,checkOut,tenant));

    }


    @After
    public void tearDown() {

        booking = null;

    }

    @Test
    public void getId() {
        booking.setId(11);
        assertEquals(11, booking.getId());
    }

    @Test
    public void setId() {
        booking.setId(2);
        assertEquals(2, booking.getId());
    }

    @Test
    public void getCheckIn() {
        Calendar expectedCheckIn = Calendar.getInstance();
        expectedCheckIn.set(2023, 11, 1);
        assertEquals(expectedCheckIn.getTime(), booking.getCheckIn());
    }

    @Test
    public void setCheckIn() {
        Calendar newCheckIn = Calendar.getInstance();
        newCheckIn.set(2023, 11, 15);
        booking.setCheckIn(newCheckIn.getTime());
        assertEquals(newCheckIn.getTime(), booking.getCheckIn());
    }

    @Test
    public void getCheckOut() {
        Calendar expectedCheckOut = Calendar.getInstance();
        expectedCheckOut.set(2023, Calendar.DECEMBER, 13);


        Date bookingCheckOut = booking.getCheckOut();


        expectedCheckOut.set(Calendar.MILLISECOND, 0);
        Calendar bookingCheckOutCalendar = Calendar.getInstance();
        bookingCheckOutCalendar.setTime(bookingCheckOut);
        bookingCheckOutCalendar.set(Calendar.MILLISECOND, 0);

        assertEquals(expectedCheckOut.getTime(), bookingCheckOutCalendar.getTime());
    }

    @Test
    public void setCheckOut() {
        Calendar newCheckOut = Calendar.getInstance();
        newCheckOut.set(2023, 11, 20);
        booking.setCheckOut(newCheckOut.getTime());
        assertEquals(newCheckOut.getTime(), booking.getCheckOut());
    }

    @Test
    public void getTenant() {
        assertEquals(tenant, booking.getTenant());
    }

    @Test
    public void setTenant() {
        Tenant newTenant = new Tenant("New", "Tenant");
        booking.setTenant(newTenant);
        assertEquals(newTenant, booking.getTenant());
    }

    @Test
    public void getListing() {
        assertEquals(listing, booking.getListing());
    }

    @Test
    public void setListing() {
        Listing newListing = new Listing();
        booking.setListing(newListing);
        assertEquals(newListing, booking.getListing());
    }



    @Test
    public void rateApartmentStayCompleted() {
        Date currentDate = new Date();
        Calendar checkOutCalendar = Calendar.getInstance();
        checkOutCalendar.setTime(currentDate);
        checkOutCalendar.add(Calendar.DAY_OF_MONTH, -1);
        Date completedStayCheckOut = checkOutCalendar.getTime();

        //Booking instance with completed stay
        Booking bookingWithCompletedStay = new Booking(new BookingRequest(listing,new Date(), currentDate, completedStayCheckOut, tenant));

        bookingWithCompletedStay.rateApartment(4);

        assertEquals(4, bookingWithCompletedStay.getApartmentRating());
    }

    @Test
    public void rateApartmentStayNotCompleted() {
        Date currentDate = new Date();
        Calendar checkOutCalendar = Calendar.getInstance();
        checkOutCalendar.setTime(currentDate);
        checkOutCalendar.add(Calendar.DAY_OF_MONTH, 1); // checkOut to tomorrow
        Date notCompletedStayCheckOut = checkOutCalendar.getTime();

        // Booking instance with not completed stay
        Booking bookingWithNotCompletedStay = new Booking(new BookingRequest(listing, new Date(), new Date(), new Date(), tenant));

        bookingWithNotCompletedStay.rateApartment(4);


        assertEquals(-1, bookingWithNotCompletedStay.getApartmentRating());

    }


    @Test
    public void isCancelled() {
        assertFalse(booking.isCancelled());
    }

    @Test
    public void cancelBooking() {
        // 1. booking that has not been canceled before
        Booking bookingToCancel = new Booking(new BookingRequest(listing, new Date(), new Date(), new Date(), tenant));
        assertFalse(bookingToCancel.isCancelled());
        bookingToCancel.cancel(ReservationStatus.CANCELLED_BY_TENANT);
        assertTrue(bookingToCancel.isCancelled());
        assertEquals(ReservationStatus.CANCELLED_BY_TENANT, bookingToCancel.getBooking_status());
        // 2.cancel a booking that has already been canceled
        Booking alreadyCancelledBooking = new Booking(new BookingRequest(listing, new Date(), new Date(), new Date(), tenant));
        alreadyCancelledBooking.cancel(ReservationStatus.CANCELLED_BY_TENANT);
        //try to cancel again
        alreadyCancelledBooking.cancel(ReservationStatus.CANCELLED_BY_TENANT);
        assertTrue(alreadyCancelledBooking.isCancelled());
    }

    @Test
    public void getApartmentRating() {
        assertEquals(-1, booking.getApartmentRating());
    }

    @Test
    public void daysUntilCheckIn() {
        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.set(2023, 10, 1);
        Date currentDate = todayCalendar.getTime();

        long daysUntilFutureCheckIn = booking.daysUntilCheckIn(currentDate);

        assertEquals(30, daysUntilFutureCheckIn);
    }


    @Test
    public void isActive_CurrentlyActive() {
        assertFalse(booking.isActive());
    }


}