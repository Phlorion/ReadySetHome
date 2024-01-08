package com.example.readysethome.view.Tenant.TenantMain;

import androidx.core.widget.TextViewCompat;

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
import com.example.readysethome.view.Tenant.TenantBookingModel;
import com.example.readysethome.view.Tenant.TenantHomeListingModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TenantMainPresenterTest {
    TenantMainViewStub view;
    TenantMainPresenter presenter;
    Initializer dataHelper;
    ListingDAO listings;
    TenantDAO tenants;
    Tenant tenant;
    Listing listing;
    ArrayList<TenantHomeListingModel> listingModels;
    ArrayList<TenantBookingModel> bookingModels;


    @Before
    public void setUp() {
        view = new TenantMainViewStub();
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        listings = new ListingDAOMemory();
        tenants = new TenantDAOMemory();

        presenter = new TenantMainPresenter(view, listings, tenants, "t1");

        tenant = presenter.getAttachedTenant();
        listing = listings.findByID(1);

        listingModels = new ArrayList<>();
        listingModels.add(view.getModel1());
        listingModels.add(view.getModel2());
        listingModels.add(view.getModel3());

        bookingModels = new ArrayList<>();
        bookingModels.add(view.getBmodel1());
        bookingModels.add(view.getBmodel2());
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
        listing.setPhotos(null);
        temp = presenter.setUpHomeListingModels();

        Assert.assertEquals(R.drawable.child_po, temp.get(0).getImage());
    }

    /**
     * Παίρνουμε τα bookings που εμφανίζονται στο recycler view του ενοικιαστή μας
     * (στο bookings fragment)
     */
    @Test
    public void getTenantAllBookings() {
         // 2 booking requests
        ArrayList<TenantBookingModel> temp = presenter.setUpBookingModels();
        // with photos
        Assert.assertEquals(bookingModels.size(), temp.size());
        Assert.assertEquals(bookingModels.get(0).getTitle(), temp.get(0).getTitle());

        // no photos
        listing.setPhotos(null);
        temp = presenter.setUpBookingModels();
        Assert.assertEquals(R.drawable.child_po, temp.get(0).getImage());

        // Accept booking requests, so now we have bookings
        Owner owner = tenant.getBookingRequests().get(0).getListing().getOwner();
        owner.confirmBookingRequest(tenant.getBookingRequests().get(0));
        owner.confirmBookingRequest(tenant.getBookingRequests().get(0));

        temp = presenter.setUpBookingModels();
        // with photos
        listing.setPhotos(new int[] {R.drawable.o1_l1_0, R.drawable.o1_l1_1});
        Assert.assertEquals(bookingModels.size(), temp.size());
        // no photo
        listing.setPhotos(null);
        temp = presenter.setUpBookingModels();
        Assert.assertEquals(R.drawable.child_po, temp.get(0).getImage());

    }

    /**
     * Cancel booking
     */
    @Test
    public void cancelBooking() {
        // Cancel 1 booking request
        presenter.cancelBookingRequest(presenter.getAttachedTenant().getBookingRequests().get(0));
        Assert.assertEquals(1, presenter.getAttachedTenant().getBookingRequests().size());

        // Accept the other booking request
        Owner owner = tenant.getBookingRequests().get(0).getListing().getOwner();
        owner.confirmBookingRequest(tenant.getBookingRequests().get(0));
        // Cancel it
        presenter.cancelBooking(presenter.getAttachedTenant().getBookings().get(0));
        Assert.assertEquals(0, presenter.getAttachedTenant().getBookings().size());
    }

    /**
     * Get attached tenant
     */
    @Test
    public void getAttachedTenant() {
        Assert.assertEquals(tenant, presenter.getAttachedTenant());
    }

}
