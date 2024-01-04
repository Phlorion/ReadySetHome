package com.example.readysethome.view.Owner.OwnerAddListing;

import com.example.readysethome.dao.Initializer;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.memorydao.OwnerDAOMemory;
import com.example.readysethome.model.Listing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OwnerAddListingPresenterTest {
    OwnerAddListingViewStub view;
    OwnerAddListingPresenter presenter;
    Initializer dataHelper;

    @Before
    public void setUp() {
        view = new OwnerAddListingViewStub();
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

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
        view.setStreet("Street 18");
        view.setAddress_num("22");
        view.setAp_size("56");
        view.setFloor("2");
        view.setBath_size("16");
        view.setKitchen_size("16");
        view.setBedroom_size("16");
        view.setDouble_beds("1");
        view.setSingle_beds("1");
        view.setTitle("New apartment in Athens");
        view.setDesc("New Listing Test Test 1234");
        view.setPrice("89");
        Listing listing = presenter.addListing();
        Assert.assertEquals("Επιτυχής προσθήκη της αγγελίας.", view.getSuccess_msg());
        Assert.assertEquals(4, listing.getListing_id());
        Assert.assertNotNull(listing);
    }

}
