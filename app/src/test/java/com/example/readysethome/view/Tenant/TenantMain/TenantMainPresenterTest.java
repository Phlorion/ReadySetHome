package com.example.readysethome.view.Tenant.TenantMain;

import com.example.readysethome.R;
import com.example.readysethome.dao.Initializer;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.OwnerDAO;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.memorydao.OwnerDAOMemory;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Owner;
import com.example.readysethome.model.Tenant;
import com.example.readysethome.view.Owner.OwnerHomeListingModel;
import com.example.readysethome.view.Owner.OwnerMain.OwnerMainPresenter;
import com.example.readysethome.view.Owner.OwnerMain.OwnerMainViewStub;
import com.example.readysethome.view.Tenant.TenantHomeListingModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TenantMainPresenterTest {
    TenantMainViewStub view;
    TenantMainPresenter presenter;
    Initializer dataHelper;
    ListingDAO listings;
    TenantDAO tenants;
    Tenant tenant;
    ArrayList<TenantHomeListingModel> listingModels;


    @Before
    public void setUp() {
        view = new TenantMainViewStub();
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        listings = new ListingDAOMemory();
        tenants = new TenantDAOMemory();

        presenter = new TenantMainPresenter(view, listings, tenants, "t1");

        tenant = presenter.getAttachedTenant();

        listingModels = new ArrayList<>();
        listingModels.add(view.getModel1());
        listingModels.add(view.getModel2());
        listingModels.add(view.getModel3());
    }

    /**
     * Παίρνουμε τα listings που εμφανίζονται στο recycler view του ενοικιαστή μας
     * (στην κεντρική οθόνη)
     */
    @Test
    public void getTenantAllListings() {
        ArrayList<TenantHomeListingModel> temp = presenter.setUpHomeListingModels();
        // with photos
        Assert.assertEquals(listingModels.size(), temp.size());
        Assert.assertEquals(listingModels.get(0).getTitle(), temp.get(0).getTitle());
        // no photos
        Listing new_listing = new Listing();
        new_listing.setPhotos(null);
        listings.save(new_listing);
        temp = presenter.setUpHomeListingModels();

        Assert.assertEquals(R.drawable.child_po, temp.get(3).getImage());
    }

}
