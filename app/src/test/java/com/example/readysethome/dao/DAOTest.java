package com.example.readysethome.dao;

import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.memorydao.OwnerDAOMemory;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.memorydao.UserDAOMemory;
import com.example.readysethome.model.CreditCard;
import com.example.readysethome.model.EmailAddress;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Owner;
import com.example.readysethome.model.Password;
import com.example.readysethome.model.Tenant;
import com.example.readysethome.model.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOTest {

    private UserDAO userDAO;
    private OwnerDAO ownerDAO;
    private TenantDAO tenantDAO;
    private ListingDAO listingDAO;

    public static final int INITIAL_USER_COUNT = 4;
    public static final int INITIAL_OWNER_COUNT = 2;
    public static final int INITIAL_TENANT_COUNT = 1;
    public static final int INITIAL_LISTING_COUNT = 3;

    @Before
    public void setUp() {
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        userDAO = new UserDAOMemory();
        ownerDAO = new OwnerDAOMemory();
        tenantDAO = new TenantDAOMemory();
        listingDAO = new ListingDAOMemory();
    }

    /**
     * Αναζήτηση χρήστη που υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findExistingUser() {
        User user = userDAO.findByID("o1");
        Assert.assertEquals("George", user.getFirstName());
        user = userDAO.findByEmail("p3210001@aueb.gr");
        Assert.assertEquals("George", user.getFirstName());
    }

    /**
     * Αναζήτηση ιδιοκτήτη που υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findExistingOwner() {
        Owner owner = ownerDAO.findByID("o2");
        Assert.assertEquals("Triantafyllos", owner.getFirstName());
        owner = ownerDAO.findByEmail("p3210079@aueb.gr");
        Assert.assertEquals("Triantafyllos", owner.getFirstName());
    }

    /**
     * Αναζήτηση ενοικιαστή που υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findExistingTenant() {
        Tenant tenant = tenantDAO.findByID("t1");
        Assert.assertEquals("Anestis", tenant.getFirstName());
        tenant = tenantDAO.findByEmail("p3210273@aueb.gr");
        Assert.assertEquals("Anestis", tenant.getFirstName());
    }

    /**
     * Αναζήτηση αγγελίας που υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findExistingListing() {
        for (Listing listing : listingDAO.findAll()) {
            System.out.println(listing.getListing_id());
        }
        Listing listing = listingDAO.findByID(1);
        Assert.assertEquals("Cool apartment", listing.getTitle());
    }

    /**
     * Αναζήτηση χρήστη που δεν υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findNonExistingUser() {
        Assert.assertNull(userDAO.findByID("o45"));
        Assert.assertNull(userDAO.findByEmail("ab1234@aueb.gr"));
    }

    /**
     * Αναζήτηση ιδιοκτήτη που δεν υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findNonExistingOwner() {
        Assert.assertNull(ownerDAO.findByID("o45"));
        Assert.assertNull(ownerDAO.findByEmail("ab1234@aueb.gr"));
    }

    /**
     * Αναζήτηση ενοικιαστή που δεν υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findNonExistingTenant() {
        Assert.assertNull(tenantDAO.findByID("t45"));
        Assert.assertNull(tenantDAO.findByEmail("ab1234@aueb.gr"));
    }

    /**
     * Αναζήτηση αγγελίας που δεν υπάρχει στη βάση δεδομένων
     */
    @Test
    public void findNonExistingListing() {
        Assert.assertNull(listingDAO.findByID(100));
    }

    /**
     * Εύρεση του επόμενου ελεύθερου id χρήστη
     */
    @Test
    public void findNextID() {
        User user_dummy = new User("George", "Avrabos");
        // System.out.println("Last owner_id: " + User.last_owner_ID + " | Last tenant_id: " + User.last_tenant_ID);
        user_dummy.setId("Owner");
        // System.out.println("Last owner_id: " + User.last_owner_ID + " | Last tenant_id: " + User.last_tenant_ID);
        user_dummy.setId("Owner");
        // System.out.println("Last owner_id: " + User.last_owner_ID + " | Last tenant_id: " + User.last_tenant_ID);
        user_dummy.setId("Tenant");
        // System.out.println("Last owner_id: " + User.last_owner_ID + " | Last tenant_id: " + User.last_tenant_ID);

        Assert.assertEquals("o3", userDAO.nextID("Owner"));
        Assert.assertEquals("t2", userDAO.nextID("Tenant"));
        Assert.assertEquals("o3", ownerDAO.nextID());
        Assert.assertEquals("t2", tenantDAO.nextID());
    }

    /**
     * Εύρεση όλων των χρηστών στη βαση δεδομένων
     */
    @Test
    public void findAllUsers() {
        List<User> users = userDAO.findAll();
        Assert.assertEquals(INITIAL_USER_COUNT, users.size());
    }

    /**
     * Εύρεση όλων των ιδιοκτητών στη βαση δεδομένων
     */
    @Test
    public void findAllOwners() {
        List<Owner> owners = ownerDAO.findAll();
        Assert.assertEquals(INITIAL_OWNER_COUNT, owners.size());
    }

    /**
     * Εύρεση όλων των ενοικιαστών στη βαση δεδομένων
     */
    @Test
    public void findAllTenants() {
        List<Tenant> tenants = tenantDAO.findAll();
        Assert.assertEquals(INITIAL_TENANT_COUNT, tenants.size());
    }

    /**
     * Εύρεση όλων των αγγελιών στη βαση δεδομένων
     */
    @Test
    public void findAllListings() {
        List<Listing> listings = listingDAO.findAll();
        Assert.assertEquals(INITIAL_LISTING_COUNT, listings.size());
    }

    /**
     * Εύρεση όλων των αγγελιών ενός ιδιοκτήτη στη βαση δεδομένων
     */
    @Test
    public void findAllOwnerListings() {
        Owner owner1 = ownerDAO.findByID("o1");
        Owner owner2 = ownerDAO.findByID("o2");

        ArrayList<Listing> owner1_listings = listingDAO.findByOwner(owner1);
        ArrayList<Listing> owner2_listings = listingDAO.findByOwner(owner2);

        Assert.assertEquals(3, owner1_listings.size());
        Assert.assertEquals(0, owner2_listings.size());
    }

    /**
     * Αποθήκευση χρήστη
     */
    @Test
    public void saveUser() {
        User user = new User("Peristereonas", "Aristidhs", new EmailAddress("gang1234@gmail.com"), new Password("123456789"), new CreditCard("8989565623230404"), new Date());
        user._setId("t100");
        userDAO.save(user);

        Assert.assertEquals(INITIAL_USER_COUNT + 1, userDAO.findAll().size());
        Assert.assertNotNull(userDAO.findByID(user.getId()));
        Assert.assertTrue(userDAO.findAll().contains(user));
    }

    /**
     * Αποθήκευση ιδιοκτήτη
     */
    @Test
    public void saveOwner() {
        User user = new User("Peristereonas", "Aristidhs", new EmailAddress("gang1234@gmail.com"), new Password("123456789"), new CreditCard("8989565623230404"), new Date());
        user._setId("o100");
        userDAO.save(user);
        Owner owner = new Owner(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getCreditCard(), user.getAcc_bday());
        owner._setId(user.getId());
        ownerDAO.save(owner);

        // saved in userDAO
        Assert.assertEquals(INITIAL_USER_COUNT + 1, userDAO.findAll().size());
        Assert.assertNotNull(userDAO.findByID(user.getId()));
        Assert.assertTrue(userDAO.findAll().contains(user));

        // and also in ownerDAO
        Assert.assertEquals(INITIAL_OWNER_COUNT + 1, ownerDAO.findAll().size());
        Assert.assertNotNull(ownerDAO.findByID(owner.getId()));
        Assert.assertTrue(ownerDAO.findAll().contains(owner));
    }

    /**
     * Αποθήκευση ενοικιαστή
     */
    @Test
    public void saveTenant() {
        User user = new User("Peristereonas", "Aristidhs", new EmailAddress("gang1234@gmail.com"), new Password("123456789"), new CreditCard("8989565623230404"), new Date());
        user._setId("t150");
        userDAO.save(user);
        Tenant tenant = new Tenant(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getCreditCard(), user.getAcc_bday());
        tenant._setId(user.getId());
        tenantDAO.save(tenant);

        // saved in userDAO
        Assert.assertEquals(INITIAL_USER_COUNT + 1, userDAO.findAll().size());
        Assert.assertNotNull(userDAO.findByID(user.getId()));
        Assert.assertTrue(userDAO.findAll().contains(user));

        // and also in tenantDAO
        Assert.assertEquals(INITIAL_TENANT_COUNT + 1, tenantDAO.findAll().size());
        Assert.assertNotNull(tenantDAO.findByID(tenant.getId()));
        Assert.assertTrue(tenantDAO.findAll().contains(tenant));
    }

    /**
     * Αποθήκευση αγγελίας
     */
    @Test
    public void saveListing() {
        Listing listing = new Listing();
        listing.setListing_id(100);
        listingDAO.save(listing);

        Assert.assertEquals(INITIAL_LISTING_COUNT + 1, listingDAO.findAll().size());
        Assert.assertNotNull(listingDAO.findByID(100));
    }

    /**
     * Διαγραφή χρήστη (ιδιοκτήτη)
     */
    @Test
    public void removeOwner() {
        User user = userDAO.findByID("o1");
        Owner owner = ownerDAO.findByID("o1");
        userDAO.delete(user);
        ownerDAO.delete(owner);
        for (Listing listing : listingDAO.findByOwner(owner)) {
            listingDAO.delete(listing);
        }

        Assert.assertEquals(INITIAL_USER_COUNT - 1, userDAO.findAll().size());
        Assert.assertNull(userDAO.findByID("o1"));
        Assert.assertEquals(INITIAL_OWNER_COUNT - 1, ownerDAO.findAll().size());
        Assert.assertNull(ownerDAO.findByID("o1"));
        Assert.assertEquals(0, listingDAO.findAll().size());
    }

    /**
     * Διαγραφή χρήστη (ενοικιαστή)
     */
    @Test
    public void removeTenant() {
        User user = userDAO.findByID("t1");
        Tenant tenant = tenantDAO.findByID("t1");
        userDAO.delete(user);
        tenantDAO.delete(tenant);

        Assert.assertEquals(INITIAL_USER_COUNT - 1, userDAO.findAll().size());
        Assert.assertNull(userDAO.findByID("t1"));
        Assert.assertEquals(INITIAL_TENANT_COUNT - 1, tenantDAO.findAll().size());
        Assert.assertNull(tenantDAO.findByID("t1"));
    }
}
