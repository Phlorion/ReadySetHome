package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ListingTest {
    final Address address = new Address("", "", "");
    final int floor = 0;
    final double size = 0;
    final boolean wifi = false;
    final boolean balcony = false;
    final boolean living_room = false;
    final ArrayList <Bathroom> bathrooms = new ArrayList<>();
    final ArrayList <Bedroom> bedrooms = new ArrayList<>();
    final ArrayList <Kitchen> kitchens = new ArrayList<>();
    final Apartment apartment = new Apartment(address, floor, size, wifi, balcony, living_room, bathrooms, bedrooms, kitchens);
    final int[] photos = {1, 2};
    final Calendar calendar = new Calendar();
    final String cnumber = "1234567890123456";
    final CreditCard creditcard = new CreditCard(cnumber, 1000000);
    final Date b_day = new Date();
    final EmailAddress emailAddress = new EmailAddress("johndoe@email.com");
    final Password password = new Password("password");
    final Owner owner = new Owner("John", "Doe", emailAddress, password, creditcard, b_day);
    final Listing test = new Listing("House1", "Description1", 100, false, 4.5, photos, calendar, owner, apartment);
    final Listing testListing = new Listing();

    @Test
    public void getApartment_id() {
        final Listing test2 = new Listing(test);
        assertEquals(test2.getListing_id(), test.getListing_id());
    }

    @Test
    public void setListing_id() {
        test.setListing_id(15);
        assertEquals(15, test.getListing_id());
    }

    @Test
    public void getTitle() {
        assertEquals("House1", test.getTitle());
    }

    @Test
    public void setTitle() {
        test.setTitle("House5");
        assertEquals("House5", test.getTitle());
    }

    @Test
    public void getDescription() {
        assertEquals("Description1", test.getDescription());
    }

    @Test
    public void setDescription() {
        test.setDescription("Other");
        assertEquals("Other", test.getDescription());
    }

    @Test
    public void getPrice() {
        assertEquals(100f, test.getPrice(), 0.0001f);
    }

    @Test
    public void setPrice() {
        test.setPrice(500.0f);
        assertEquals(500.0f, test.getPrice(), 0.01f);
    }

    @Test
    public void isPromoted() {
        assertEquals(false, test.isPromoted());
    }

    @Test
    public void setPromoted() {
        test.setPromoted(true);
        assertEquals(true, test.isPromoted());
    }

    @Test
    public void getRating() {
        assertEquals(4.5f, test.getRating(), 0.01f);
    }

    @Test
    public void setRating() {
        test.setRating(3.0f);
        assertEquals(3.0f, test.getRating(), 0.001f);
    }

    @Test
    public void getPhotos() {
        final int[] tmp = test.getPhotos();
        assertEquals(tmp, test.getPhotos());
    }

    @Test
    public void setPhotos() {
        final int[] photos2 = {2, 1};
        final int[] photos3 = {2, 1};
        test.setPhotos(photos2);
        assertEquals(photos2, test.getPhotos());
    }

    @Test
    public void getCalendar() {
        final Calendar tmp = test.getCalendar();
        assertEquals(tmp, test.getCalendar());
    }

    @Test
    public void setCalendar() {
        final Date customDate = Date.from(Instant.parse("2024-01-01T00:00:00.000Z"));
        final Date customDate2 = Date.from(Instant.parse("2024-01-05T00:00:00.000Z"));
        final Calendar c1 = test.getCalendar();
        c1.setUnavailable(customDate, customDate2);
        test.setCalendar(c1);
        assertEquals(c1, test.getCalendar());
    }

    @Test
    public void getOwner() {
        Owner tmp = test.getOwner();
        assertEquals(tmp, test.getOwner());
    }

    @Test
    public void setOwner() {
        final EmailAddress emailAddress2 = new EmailAddress("jackjones@email.com");
        final Password password2 = new Password("jackjones");
        final String cnumber2 = "1234561234567890";
        final CreditCard creditcard2 = new CreditCard(cnumber2);
        final Date b_day2 = new Date();
        Owner owner = new Owner("John", "Doe", emailAddress2, password2, creditcard2, b_day2);
        test.setOwner(owner);
        assertEquals(owner, test.getOwner());
    }

    @Test
    public void getChargingPolicies() {
        final ArrayList tmp = test.getChargingPolicies();
        assertEquals(tmp, test.getChargingPolicies());
    }

    @Test
    public void setChargingPolicies() {
        final ArrayList<ChargingPolicy> chargingPolicies = new ArrayList<ChargingPolicy>();
        test.setChargingPolicies(chargingPolicies);
        assertEquals(chargingPolicies, test.getChargingPolicies());
    }

    @Test
    public void getOriginal_Price() {
        test.setOriginal_Price(100f);
        final double tmp = test.getOriginal_Price();
        assertEquals(tmp, test.getOriginal_Price(), 0.01f);
    }

    @Test
    public void setOriginal_Price() {
        test.setOriginal_Price(50f);
        assertEquals(50f, test.getOriginal_Price(), 0.01f);
    }

    @Test
    public void getUpdated_Price() {
        test.setUpdated_Price(200f);
        final double tmp = test.getUpdated_Price();
        assertEquals(tmp, test.getUpdated_Price(), 0.01f);
    }

    @Test
    public void setUpdated_Price() {
        test.setUpdated_Price(500f);
        assertEquals(500f, test.getUpdated_Price(), 0.01f);
    }

    @Test
    public void getServices() {
        final ArrayList<ListingsServices> tmp = test.getServices();
        assertEquals(tmp, test.getServices());
    }

    @Test
    public void setServices() {
        final ArrayList<ListingsServices> services = new ArrayList<ListingsServices>();
        test.setServices(services);
        assertEquals(services, test.getServices());
    }

    @Test
    public void addNewChargingPolicies() {
        final Date customDate = Date.from(Instant.parse("2024-01-01T00:00:00.000Z"));
        final Date customDate2 = Date.from(Instant.parse("2024-01-05T00:00:00.000Z"));
        final ChargingPolicy chargingPolicy = new ChargingPolicy(customDate, customDate2, "Something", 50);
        test.addNewChargingPolicies(chargingPolicy);
        final Listing test2 = new Listing(test);
        assertEquals(test2.getChargingPolicies(), test.getChargingPolicies());
    }

    @Test
    public void updatePriceDueToPolicy() {
        final Date customDate = Date.from(Instant.parse("2024-01-01T00:00:00.000Z"));
        final Date customDate2 = Date.from(Instant.parse("2024-01-05T00:00:00.000Z"));
        final ChargingPolicy chargingPolicy = new ChargingPolicy(customDate, customDate2, "Something", 50);
        test.addNewChargingPolicies(chargingPolicy);
        test.updatePriceDueToPolicy();
        test.setOriginal_Price(test.getPrice());
        assertEquals(150, test.getPrice(), 0.01f);
    }

    @Test
    public void addService() {
        final ListingsServices listingsService = new ListingsServices(100, ListingsServicesType.CLEANING_SERVICE);
        final ArrayList<ListingsServices> services = new ArrayList<>();
        services.add(listingsService);
        test.addService(listingsService);
        assertEquals(services, test.getServices());
    }

    @Test
    public void updatePriceDueToServices() {
        final ListingsServices listingsService = new ListingsServices(100, ListingsServicesType.CLEANING_SERVICE);
        test.addService(listingsService);
        test.updatePriceDueToServices();
        assertEquals(200, test.getPrice(), 0.01f);
    }

    @Test
    public void addChargingPolicy() {
        // 3 overlapping charging policies
        final Date customDate1 = Date.from(Instant.parse("2024-01-06T00:00:00.000Z"));
        final Date customDate12 = Date.from(Instant.parse("2024-01-07T00:00:00.000Z"));
        final ChargingPolicy chargingPolicy1 = new ChargingPolicy(customDate1, customDate12, "chargingPolicy1", 100);

        final Date customDate2 = Date.from(Instant.parse("2024-01-06T00:00:00.000Z"));
        final Date customDate22 = Date.from(Instant.parse("2024-01-07T00:00:00.000Z"));
        final ChargingPolicy chargingPolicy2 = new ChargingPolicy(customDate2, customDate22, "chargingPolicy2", 30);

        final Date customDate3 = Date.from(Instant.parse("2024-01-06T00:00:00.000Z"));
        final Date customDate32 = Date.from(Instant.parse("2024-01-07T00:00:00.000Z"));
        final ChargingPolicy chargingPolicy3 = new ChargingPolicy(customDate3, customDate32, "chargingPolicy3", 40);

        // 3 exactly the same charging policies with the charging policy 3

        final Date customDate4 = Date.from(Instant.parse("2024-01-12T00:00:00.000Z"));
        final Date customDate42 = Date.from(Instant.parse("2024-01-13T00:00:00.000Z"));
        final ChargingPolicy chargingPolicy4 = new ChargingPolicy(customDate4, customDate42, "chargingPolicy3", 40);

        final Date customDate5 = Date.from(Instant.parse("2024-01-13T00:00:00.000Z"));
        final Date customDate52 = Date.from(Instant.parse("2024-01-14T00:00:00.000Z"));
        final ChargingPolicy chargingPolicy5 = new ChargingPolicy(customDate5, customDate52, "chargingPolicy3", 40);

        // another valid charging policy
        final Date customDate6 = Date.from(Instant.parse("2023-01-01T00:00:00.000Z"));
        final Date customDate62 = Date.from(Instant.parse("2023-01-02T00:00:00.000Z"));
        final ChargingPolicy chargingPolicy6 = new ChargingPolicy(customDate6, customDate62, "chargingPolicy6", 6);

        final int[] photos = {1, 2};
        final Calendar calendar = new Calendar();
        final String cnumber = "1234567890123456";
        final CreditCard creditcard = new CreditCard(cnumber);
        final Date b_day = new Date();
        final EmailAddress emailAddress = new EmailAddress("johndoe@email.com");
        final Password password = new Password("password");
        final Owner owner = new Owner("John", "Doe", emailAddress, password, creditcard, b_day);
        final Listing listing = new Listing("House1", "Description1", 100, false, 4.5, photos, calendar, owner, apartment);


        listing.addChargingPolicy(chargingPolicy1);
        listing.addChargingPolicy(chargingPolicy2);
        listing.addChargingPolicy(chargingPolicy3);
        listing.addChargingPolicy(chargingPolicy4);
        listing.addChargingPolicy(chargingPolicy5);
        listing.addChargingPolicy(chargingPolicy6);

        ArrayList<ChargingPolicy> charPolicies = new ArrayList<>();
        charPolicies.add(chargingPolicy1);
        charPolicies.add(chargingPolicy2);
        charPolicies.add(chargingPolicy3);
        charPolicies.add(chargingPolicy4);
        charPolicies.add(chargingPolicy5);
        charPolicies.add(chargingPolicy6);

        assertNotEquals(charPolicies, listing.getChargingPolicies());
    }

    @Test
    public void setApartment() {
        final Apartment apartment2 = new Apartment(address, floor, size, wifi, balcony, living_room, bathrooms, bedrooms, kitchens);
        test.setApartment(apartment2);
        assertEquals(apartment2, test.getApartment());
    }

    @Test
    public void getApartment() {
        assertEquals(apartment, test.getApartment());
    }

    // check-in during month of interest, check-out after
    @Test
    public void calculateOccupancy() {
        Tenant tenant = new Tenant("John", "Doe", new EmailAddress("john.doe@example.com"),
                new Password("password123"), new CreditCard("1234567890123456", 10000), new Date());
        final Date customDate6 = Date.from(Instant.parse("2023-01-09T00:00:00.000Z"));
        final Date customDate62 = Date.from(Instant.parse("2023-02-05T00:00:00.000Z"));
        BookingRequest br = tenant.makeBookingRequest(test, customDate6, customDate62);
        owner.confirmBookingRequest(br);
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(2023, 0,1);
        double days = test.calculateOccupancy(cal);
        assertEquals(22, days, 0.01f);
    }
    // check-in during month of interest, check-out during month of interest as well
    @Test
    public void calculateOccupancy2() {
        Tenant tenant = new Tenant("John", "Doe", new EmailAddress("john.doe@example.com"),
                new Password("password123"), new CreditCard("1234567890123456", 10000), new Date());
        final Date customDate6 = Date.from(Instant.parse("2023-01-09T00:00:00.000Z"));
        final Date customDate62 = Date.from(Instant.parse("2023-01-15T00:00:00.000Z"));
        BookingRequest br = tenant.makeBookingRequest(test, customDate6, customDate62);
        owner.confirmBookingRequest(br);
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(2023, 0,1);
        double days = test.calculateOccupancy(cal);
        assertEquals(6, days, 0.01f);
    }
    // check-in before month of interest, check-out during month of interest
    @Test
    public void calculateOccupancy3() {
        Tenant tenant = new Tenant("John", "Doe", new EmailAddress("john.doe@example.com"),
                new Password("password123"), new CreditCard("1234567890123456", 10000), new Date());
        final Date customDate6 = Date.from(Instant.parse("2023-01-23T00:00:00.000Z"));
        final Date customDate62 = Date.from(Instant.parse("2023-02-03T00:00:00.000Z"));
        BookingRequest br = tenant.makeBookingRequest(test, customDate6, customDate62);
        owner.confirmBookingRequest(br);
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(2023, 1,1);
        double days = test.calculateOccupancy(cal);
        assertEquals(3, days, 0.01f);
    }
    // check-in before month of interest, check-out after month of interest
    @Test
    public void calculateOccupancy4() {
        Tenant tenant = new Tenant("John", "Doe", new EmailAddress("john.doe@example.com"),
                new Password("password123"), new CreditCard("1234567890123456", 10000), new Date());
        final Date customDate6 = Date.from(Instant.parse("2022-12-23T00:00:00.000Z"));
        final Date customDate62 = Date.from(Instant.parse("2023-02-03T00:00:00.000Z"));
        BookingRequest br = tenant.makeBookingRequest(test, customDate6, customDate62);
        owner.confirmBookingRequest(br);
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(2023, 0,1);
        double days = test.calculateOccupancy(cal);
        assertEquals(31, days, 0.01f);
    }

    @Test
    public void calculateMonthlyIncome() {
        java.util.Calendar c = java.util.Calendar.getInstance();
        Date d1 = new Date();
        c.set(java.util.Calendar.YEAR, 2023);
        c.set(java.util.Calendar.MONTH, 5);
        c.set(java.util.Calendar.DAY_OF_MONTH, 13);
        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
        c.set(java.util.Calendar.MINUTE, 0);
        c.set(java.util.Calendar.SECOND, 0);
        d1.setTime(c.getTimeInMillis());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String yearmonth = dateFormat.format(d1);
        test.calculateMonthlyIncome(d1, 100);

        assertEquals(100.0, test.getMonthlyIncome().get(yearmonth), 0.0);
    }

    @Test
    public void getMonthlyIncome() {
        final HashMap<String, Double> tmp = new HashMap<>();
        test.setMonthlyIncome(tmp);
        assertEquals(tmp, test.getMonthlyIncome());
    }

    @Test
    public void setMonthlyIncome() {
        final HashMap<String, Double> tmp = new HashMap<>();
        test.setMonthlyIncome(tmp);
        assertEquals(tmp, test.getMonthlyIncome());
    }

    @Test
    public void getMonthlyCancellations() {
        final HashMap<String, Integer> tmp = new HashMap<>();
        test.setMonthlyCancellations(tmp);
        assertEquals(tmp, test.getMonthlyCancellations());
    }

    @Test
    public void setMonthlyCancellations() {
        final HashMap<String, Integer> tmp = new HashMap<>();
        test.setMonthlyCancellations(tmp);
        assertEquals(tmp, test.getMonthlyCancellations());
    }

    @Test
    public void calculateCancellationsPerMonth() {
        // Set up Tenant, Listing, BookingRequest, and Booking instances
        Tenant tenant = new Tenant("John", "Doe", new EmailAddress("john.doe@example.com"),
                new Password("password123"), new CreditCard("1234567890123456", 100000), new Date());
        final Date customDate6 = Date.from(Instant.parse("2023-01-09T00:00:00.000Z"));
        final Date customDate62 = Date.from(Instant.parse("2023-02-05T00:00:00.000Z"));
        BookingRequest br = tenant.makeBookingRequest(test, customDate6, customDate62);
        owner.confirmBookingRequest(br);

        // Set up Tenant, Listing, BookingRequest, and Booking instances
        Tenant tenant2 = new Tenant("John", "Doe", new EmailAddress("john.doe@example.com"),
                new Password("password123"), new CreditCard("1234567890123456", 100000), new Date());
        final Date customDate1 = Date.from(Instant.parse("2024-01-06T00:00:00.000Z"));
        final Date customDate12 = Date.from(Instant.parse("2024-01-07T00:00:00.000Z"));
        BookingRequest br2 = tenant.makeBookingRequest(test, customDate1, customDate12);
        owner.confirmBookingRequest(br2);

        // Set up Tenant, Listing, BookingRequest, and Booking instances
        Tenant tenant3 = new Tenant("John", "Doe", new EmailAddress("john.doe@example.com"),
                new Password("password123"), new CreditCard("1234567890123456", 100000), new Date());
        final Date customDate3 = Date.from(Instant.parse("2024-01-06T00:00:00.000Z"));
        final Date customDate32 = Date.from(Instant.parse("2024-01-07T00:00:00.000Z"));
        BookingRequest br3 = tenant.makeBookingRequest(test, customDate3, customDate32);
        owner.confirmBookingRequest(br3);

        Booking idBr = tenant.getBookingById(br.getBooking_id());
        Booking idBr2 =  tenant.getBookingById(br2.getBooking_id());
        Booking idBr3 = tenant.getBookingById(br3.getBooking_id());

        idBr.cancel(ReservationStatus.CANCELLED_BY_TENANT);
        idBr2.cancel(ReservationStatus.CANCELLED_BY_TENANT);
        idBr3.cancel(ReservationStatus.CANCELLED_BY_TENANT);

        assertEquals(0, tenant.getBookings().size());
    }
}