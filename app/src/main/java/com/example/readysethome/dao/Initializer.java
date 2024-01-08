package com.example.readysethome.dao;

import com.example.readysethome.R;
import com.example.readysethome.model.Address;
import com.example.readysethome.model.Apartment;
import com.example.readysethome.model.Bathroom;
import com.example.readysethome.model.Bedroom;
import com.example.readysethome.model.BookingRequest;
import com.example.readysethome.model.CreditCard;
import com.example.readysethome.model.EmailAddress;
import com.example.readysethome.model.Kitchen;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Owner;
import com.example.readysethome.model.Password;
import com.example.readysethome.model.Tenant;
import com.example.readysethome.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public abstract class Initializer {
    /**
     * Διαγράφει τα αποθηκευμένα δεδομένα.
     */
    protected abstract void eraseData();

    /**
     * Επιστρέφει το DAO των users.
     * @return Το DAO των users
     */
    public abstract UserDAO getUserDAO();

    /**
     * Επιστρέφει το DAO των owners.
     * @return Το DAO των owners
     */
    public abstract OwnerDAO getOwnerDAO();

    /**
     * Επιστρέφει το DAO των tenants.
     * @return Το DAO των tenants
     */
    public abstract TenantDAO getTenantDAO();

    /**
     * Επιστρέφει το DAO των listings.
     * @return Το DAO των listings
     */
    public abstract ListingDAO getListingDAO();

    /**
     * Εισάγει τα δοκιμαστικά δεδομένα.
     */
    public void prepareData() {
        // πριν εισάγουμε τα δεδομένα διαγράφουμε ότι υπάρχει
        eraseData();

        // create some users
        UserDAO userDAO = getUserDAO();

        OwnerDAO ownerDAO = getOwnerDAO();

        TenantDAO tenantDAO = getTenantDAO();

        User user1 = new User("George", "Avrabos", new EmailAddress("p3210001@aueb.gr"), new Password("1234"), new CreditCard("1234123412341234"), new Date());
        user1._setId("o1");
        userDAO.save(user1);
        User user2 = new User("Anestis", "Thanasi", new EmailAddress("p3210273@aueb.gr"), new Password("9090"), new CreditCard("0987098709870987"), new Date());
        user2._setId("t1");
        userDAO.save(user2);

        User user3 = new User("Triantafyllos", "Kiosse", new EmailAddress("p3210079@aueb.gr"), new Password("4545"), new CreditCard("467467467467"), new Date());
        user3._setId("o2");
        userDAO.save(user3);

         User SYSTEM = new User("ReadySetHome", "Admin", new EmailAddress("readysethomeAdmin@gmail.com"),
                new Password("admin"), new CreditCard("0000000000000000"), new Date());
         SYSTEM._setId("t0");
         userDAO.save(SYSTEM);


        // create some listings and assign them to some users
        ListingDAO listingDAO = getListingDAO();

        Owner owner1 = new Owner(user1.getFirstName(), user1.getLastName(), user1.getEmail(), user1.getPassword(), user1.getCreditCard(), user1.getAcc_bday());
        owner1._setId(user1.getId());
        ownerDAO.save(owner1);

        Tenant tenant1 = new Tenant(user2.getFirstName(), user2.getLastName(), user2.getEmail(), user2.getPassword(), user2.getCreditCard(), user2.getAcc_bday());
        tenant1._setId(user2.getId());
        tenant1.getCreditCard().setBalance(10000);
        tenantDAO.save(tenant1);

        Owner owner2 = new Owner(user3.getFirstName(), user3.getLastName(), user3.getEmail(), user3.getPassword(), user3.getCreditCard(), user3.getAcc_bday());
        owner2._setId(user3.getId());
        ownerDAO.save(owner2);

        // ap1
        ArrayList<Bathroom> ap1_baths = new ArrayList<>();
        ap1_baths.add(new Bathroom(8, true, true, false));
        ArrayList<Bedroom> ap1_beds = new ArrayList<>();
        ap1_beds.add(new Bedroom(12, 1, 0, true));
        ArrayList<Kitchen> ap1_kits = new ArrayList<>();
        ap1_kits.add(new Kitchen(8, true, false, true, true, false, false));
        Apartment ap1 = new Apartment(new Address("Athens", "Str123", "18"), 2, 28, true, true, true, ap1_baths, ap1_beds, ap1_kits);
        Listing l1 = owner1.addListing(ap1, "Cool apartment", "Small apartment in Athens.", 56.00, false, new int[]{R.drawable.o1_l1_0, R.drawable.o1_l1_1}, null);
        l1.setListing_id(1);
        listingDAO.save(l1);

        java.util.Calendar c = java.util.Calendar.getInstance();
        Date d1 = new Date();
        c.set(java.util.Calendar.YEAR, 2023);
        c.set(java.util.Calendar.MONTH, 5);
        c.set(java.util.Calendar.DAY_OF_MONTH, 13);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        d1.setTime(c.getTimeInMillis());

        Date d2 = new Date();
        c.set(java.util.Calendar.YEAR, 2023);
        c.set(java.util.Calendar.MONTH, 5);
        c.set(java.util.Calendar.DAY_OF_MONTH, 14);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        d2.setTime(c.getTimeInMillis());

        Date d3 = new Date();
        c.set(java.util.Calendar.YEAR, 2023);
        c.set(java.util.Calendar.MONTH, 5);
        c.set(java.util.Calendar.DAY_OF_MONTH, 27);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        d3.setTime(c.getTimeInMillis());

        BookingRequest bookingRequest1 = tenant1.makeBookingRequest(l1, d2, d3);
//        owner1.confirmBookingRequest(bookingRequest1);

        java.util.Calendar c4 = java.util.Calendar.getInstance();
        Date d4 = new Date();
        c4.set(java.util.Calendar.YEAR, 2023);
        c4.set(java.util.Calendar.MONTH, 5);
        c4.set(java.util.Calendar.DAY_OF_MONTH, 2);
        d4.setTime(c4.getTimeInMillis());

        java.util.Calendar c5 = java.util.Calendar.getInstance();
        Date d5 = new Date();
        c5.set(java.util.Calendar.YEAR, 2023);
        c5.set(java.util.Calendar.MONTH, 5);
        c5.set(java.util.Calendar.DAY_OF_MONTH, 3);
        d5.setTime(c5.getTimeInMillis());

        java.util.Calendar c6 = java.util.Calendar.getInstance();
        Date d6 = new Date();
        c6.set(java.util.Calendar.YEAR, 2023);
        c6.set(java.util.Calendar.MONTH, 5);
        c6.set(java.util.Calendar.DAY_OF_MONTH, 7);
        d6.setTime(c6.getTimeInMillis());

        BookingRequest bookingRequest2 = tenant1.makeBookingRequest(l1, d5, d6);
        //owner1.confirmBookingRequest(bookingRequest2);



        // ap2
        ArrayList<Bathroom> ap2_baths = new ArrayList<>();
        ap2_baths.add(new Bathroom(12, true, true, true));
        ArrayList<Bedroom> ap2_beds = new ArrayList<>();
        ap2_beds.add(new Bedroom(18, 1, 1, true));
        ArrayList<Kitchen> ap2_kits = new ArrayList<>();
        ap2_kits.add(new Kitchen(14, true, true, true, true, true, false));
        Apartment ap2 = new Apartment(new Address("Athens", "Str889", "16A"), 3, 36, true, true, true, ap2_baths, ap2_beds, ap2_kits);
        Listing l2 = owner1.addListing(ap2, "Nice apartment", "Nice little apartment in Athens.", 64.00, false, new int[]{R.drawable.o1_l2_0}, null);
        l2.setListing_id(2);
        listingDAO.save(l2);

        // ap2
        ArrayList<Bathroom> ap3_baths = new ArrayList<>();
        ap3_baths.add(new Bathroom(8, true, true, true));
        ap3_baths.add(new Bathroom(14, true, true, true));
        ArrayList<Bedroom> ap3_beds = new ArrayList<>();
        ap3_beds.add(new Bedroom(20, 2, 1, true));
        ArrayList<Kitchen> ap3_kits = new ArrayList<>();
        ap3_kits.add(new Kitchen(20, true, true, true, true, true, true));
        Apartment ap3 = new Apartment(new Address("Athens", "BigStreet1100", "9"), 1, 60, true, true, true, ap3_baths, ap3_beds, ap3_kits);
        Listing l3 = owner1.addListing(ap3, "Big apartment", "Big and awesome apartment in Athens.", 112.00, false, new int[]{R.drawable.o1_l3_0}, null);
        l3.setListing_id(3);
        listingDAO.save(l3);

    }
}
