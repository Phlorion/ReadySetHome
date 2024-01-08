package com.example.readysethome.view.Owner.OwnerAddListing;

import com.example.readysethome.dao.Initializer;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.memorydao.OwnerDAOMemory;
import com.example.readysethome.model.ChargingPolicy;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.ListingsServices;
import com.example.readysethome.model.ListingsServicesType;
import com.example.readysethome.model.ReservationStatus;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class OwnerAddListingPresenterTest {
    OwnerAddListingViewStub view;
    OwnerAddListingPresenter presenter;
    ListingDAO listings;
    Initializer dataHelper;

    @Before
    public void setUp() {
        view = new OwnerAddListingViewStub();
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        listings = new ListingDAOMemory();

        presenter = new OwnerAddListingPresenter(view, new ListingDAOMemory(), new OwnerDAOMemory(), "o1");
    }

    /**
     * Empty fields
     */
    @Test
    public void emptyFields() {
        Listing listing = presenter.addListing();
        Assert.assertEquals("Σφάλμα!", view.getError_title());
        Assert.assertEquals("Παρακαλώ εισάγετε όλα τα παραπάνω στοιχεία.", view.getError_msg());
        Assert.assertNull(listing);
    }

    /**
     * Successful addition of listing
     */
    @Test
    public void addNewListing() {
        view.setCity("Athens");
        view.setStreet("Str889");
        view.setAddress_num("16A");
        view.setAp_size("56");
        view.setFloor("3");
        view.setBath_size("16");
        view.setKitchen_size("16");
        view.setBedroom_size("16");
        view.setDouble_beds("1");
        view.setSingle_beds("1");
        view.setTitle("New apartment in Athens");
        view.setDesc("New Listing Test Test 1234");
        view.setPrice("89");

        // add listing services
        ArrayList<ListingsServices> temp_ls = new ArrayList<>();
        temp_ls.add(new ListingsServices(10.0, ListingsServicesType.CLEANING_SERVICE));
        temp_ls.add(new ListingsServices(12.0, ListingsServicesType.WIFI));
        presenter.addListingServices(temp_ls);
        Assert.assertEquals(2, presenter.getListingsServices().size());

        // add charging policies
        ArrayList<ChargingPolicy> temp_cp = new ArrayList<>();
        temp_cp.add(new ChargingPolicy(new Date(), new Date(), "Charging Policy 1", 7.5));
        temp_cp.add(new ChargingPolicy(new Date(), new Date(), "Charging Policy 2", -3.));
        presenter.addChargingPolicy(temp_cp);
        Assert.assertEquals(2, presenter.getChargingPolicies().size());

        // Apartment already exists
        Listing listing = presenter.addListing();
        Assert.assertEquals("Το διαμέρισμα είναι ήδη καταχωρημένο.", view.getError_msg());

        view.setCity("Athens");
        view.setStreet("1234");
        view.setAddress_num("23");
        view.setFloor("1");
        listing = presenter.addListing();
        Assert.assertEquals("Επιτυχής προσθήκη της αγγελίας.", view.getSuccess_msg());
        Assert.assertEquals(4, listing.getListing_id());
        Assert.assertNotNull(listing);
    }

}
