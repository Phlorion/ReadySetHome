package com.example.readysethome.view.Owner.OwnerMain;

import com.example.readysethome.R;
import com.example.readysethome.dao.Initializer;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.OwnerDAO;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.memorydao.OwnerDAOMemory;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.model.BookingRequest;
import com.example.readysethome.model.CreditCard;
import com.example.readysethome.model.EmailAddress;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Owner;
import com.example.readysethome.model.Password;
import com.example.readysethome.model.Tenant;
import com.example.readysethome.model.User;
import com.example.readysethome.view.Owner.OwnerHomeListingModel;
import com.example.readysethome.view.Owner.OwnerPendingModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class OwnerMainPresenterTest {

    OwnerMainViewStub view;
    OwnerMainPresenter presenter;
    Initializer dataHelper;
    ListingDAO listings;
    OwnerDAO owners;
    Owner owner;

    TenantDAO tenants;
    ArrayList<OwnerHomeListingModel> listingModels;
    ArrayList<OwnerPendingModel> pendingModels;
    ArrayList<BookingRequest> brs;


    @Before
    public void setUp() {
        view = new OwnerMainViewStub();
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        listings = new ListingDAOMemory();
        owners = new OwnerDAOMemory();
        tenants = new TenantDAOMemory();

        presenter = new OwnerMainPresenter(view, listings, owners, "o1");

        owner = presenter.getAttachedOwner();

        listingModels = new ArrayList<>();
        listingModels.add(view.getModel1());
        listingModels.add(view.getModel2());
        listingModels.add(view.getModel3());

        pendingModels = new ArrayList<>();
        pendingModels.add(view.getPendingModel1());
        pendingModels.add(view.getPendingModel2());

        brs = new ArrayList<>();
    }

    /**
     * Παίρνουμε τα listings που εμφανίζονται στο recycler view του ιδιοκτήτη μας
     */
    @Test
    public void getOwnerListings() {
        ArrayList<OwnerHomeListingModel> temp = presenter.setUpListingModels();
        // with photos
        Assert.assertEquals(listingModels.size(), temp.size());
        Assert.assertEquals(listingModels.get(0).getTitle(), temp.get(0).getTitle());
        // no photos
        Listing new_listing = new Listing();
        new_listing.setOwner(owner);
        new_listing.setPhotos(null);
        listings.save(new_listing);
        temp = presenter.setUpListingModels();

        Assert.assertEquals(R.drawable.child_po, temp.get(3).getImage());
    }

    /**
     * Προσθήκη ενός listing στο recycler view
     */
    @Test
    public void addOwnerListing() {
        ArrayList<OwnerHomeListingModel> presenterListingModels = presenter.setUpListingModels();
        // no photo
        Listing new_listing = new Listing();
        new_listing.setOwner(owner);
        new_listing.setTitle("New");
        new_listing.setDescription("New Listing Test");
        new_listing.setPrice(150);
        new_listing.setPhotos(null);

        OwnerHomeListingModel new_model = new OwnerHomeListingModel(new_listing.getTitle(), new_listing.getDescription(), Double.toString(new_listing.getPrice()) + "€", R.drawable.child_po, 4);
        listingModels.add(new_model);
        presenterListingModels = presenter.addListingModel(new_listing);

        Assert.assertEquals(new_model.getTitle(), presenterListingModels.get(3).getTitle());
        Assert.assertEquals(listingModels.get(3).getImage(), presenterListingModels.get(3).getImage());

        // with photo
        new_listing.setPhotos(new int[]{10});

        OwnerHomeListingModel new_model2 = new OwnerHomeListingModel(new_listing.getTitle(), new_listing.getDescription(), Double.toString(new_listing.getPrice()) + "€", 10, 5);
        listingModels.add(new_model2);
        presenterListingModels = presenter.addListingModel(new_listing);

        Assert.assertEquals(listingModels.get(4).getImage(), presenterListingModels.get(4).getImage());

    }

    @Test
    public void getOwnerPendings() {
        ArrayList<OwnerPendingModel> temp = presenter.setUpPendingModels();
        Assert.assertEquals(pendingModels.size(), temp.size());
        Listing ls = listings.findByID(1);
        ls.setPhotos(null);
        temp = presenter.setUpPendingModels();
        Assert.assertEquals(R.drawable.child_po, temp.get(0).getImage());
    }

    @Test
    public void pendingConfirmTest() {
        ArrayList<OwnerPendingModel> temp = presenter.setUpPendingModels();
        presenter.pendingConfirm(0);
        Assert.assertEquals(1, owner.getBookingRequests().size());
    }

    @Test
    public void pendingDeclineTest() {
        ArrayList<OwnerPendingModel> temp = presenter.setUpPendingModels();
        presenter.pendingDecline(0);
        Assert.assertEquals(1, owner.getBookingRequests().size());
    }
}
