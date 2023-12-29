package com.example.readysethome.dao;

import com.example.readysethome.model.Address;
import com.example.readysethome.model.Apartment;
import com.example.readysethome.model.Bathroom;
import com.example.readysethome.model.Bedroom;
import com.example.readysethome.model.CreditCard;
import com.example.readysethome.model.EmailAddress;
import com.example.readysethome.model.Kitchen;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Owner;
import com.example.readysethome.model.Password;
import com.example.readysethome.model.User;

import java.util.ArrayList;
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

        User user1 = new User("George", "Avrabos", new EmailAddress("p3210001@aueb.gr"), new Password("1234"), new CreditCard("1234123412341234"), new Date());
        user1.setId("Owner");
        userDAO.save(user1);
        User user2 = new User("Anestis", "Thanasi", new EmailAddress("p3210273@aueb.gr"), new Password("9090"), new CreditCard("0987098709870987"), new Date());
        user1.setId("Tenant");
        userDAO.save(user2);
        User user3 = new User("Triantafyllos", "Kiosse", new EmailAddress("p3210079@aueb.gr"), new Password("4545"), new CreditCard("467467467467"), new Date());
        user1.setId("Owner");
        userDAO.save(user3);

        // create some listings and assign them to some users
        ListingDAO listingDAO = getListingDAO();

        Owner owner1 = new Owner(user1.getFirstName(), user1.getLastName(), user1.getEmail(), user1.getPassword(), user1.getCreditCard(), user1.getAcc_bday());
        owner1._setId(user1.getId());

        // ap1
        ArrayList<Bathroom> ap1_baths = new ArrayList<>();
        ap1_baths.add(new Bathroom(8, true, true, false));
        ArrayList<Bedroom> ap1_beds = new ArrayList<>();
        ap1_beds.add(new Bedroom(12, 1, 0, true));
        ArrayList<Kitchen> ap1_kits = new ArrayList<>();
        ap1_kits.add(new Kitchen(8, true, false, true, true, false, false));
        Apartment ap1 = new Apartment(new Address("Athens", "Str123", "18"), 2, 28, true, true, true, ap1_baths, ap1_beds, ap1_kits);
        listingDAO.save(owner1.addListing(ap1, "Cool apartment", "Small apartment in Athens.", 56.00, false, null, null));

        // ap2
        ArrayList<Bathroom> ap2_baths = new ArrayList<>();
        ap2_baths.add(new Bathroom(12, true, true, true));
        ArrayList<Bedroom> ap2_beds = new ArrayList<>();
        ap2_beds.add(new Bedroom(18, 1, 1, true));
        ArrayList<Kitchen> ap2_kits = new ArrayList<>();
        ap2_kits.add(new Kitchen(14, true, true, true, true, true, false));
        Apartment ap2 = new Apartment(new Address("Athens", "Str889", "16A"), 3, 36, true, true, true, ap2_baths, ap2_beds, ap2_kits);
        listingDAO.save(owner1.addListing(ap2, "Nice apartment", "Nice little apartment in Athens.", 64.00, false, null, null));

        // ap2
        ArrayList<Bathroom> ap3_baths = new ArrayList<>();
        ap3_baths.add(new Bathroom(8, true, true, true));
        ap3_baths.add(new Bathroom(14, true, true, true));
        ArrayList<Bedroom> ap3_beds = new ArrayList<>();
        ap3_beds.add(new Bedroom(20, 2, 1, true));
        ArrayList<Kitchen> ap3_kits = new ArrayList<>();
        ap3_kits.add(new Kitchen(20, true, true, true, true, true, true));
        Apartment ap3 = new Apartment(new Address("Athens", "BigStreet1100", "9"), 1, 60, true, true, true, ap3_baths, ap3_beds, ap3_kits);
        listingDAO.save(owner1.addListing(ap3, "Big apartment", "Big and awesome apartment in Athens.", 112.00, false, null, null));

    }
}
