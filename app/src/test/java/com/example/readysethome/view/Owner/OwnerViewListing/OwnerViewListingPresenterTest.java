package com.example.readysethome.view.Owner.OwnerViewListing;

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
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Owner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class OwnerViewListingPresenterTest {
    OwnerViewListingStub view;
    OwnerViewListingPresenter presenter;
    Initializer dataHelper;
    ListingDAO listings;
    TenantDAO tenants;
    OwnerDAO owners;

    @Before
    public void setUp() {
        view = new OwnerViewListingStub();
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        listings = new ListingDAOMemory();
        tenants = new TenantDAOMemory();
        owners = new OwnerDAOMemory();
        presenter = new OwnerViewListingPresenter(view, dataHelper.getListingDAO(), dataHelper.getTenantDAO(), 1);

        java.util.Calendar c5 = java.util.Calendar.getInstance();
        Date d5 = new Date();
        c5.set(java.util.Calendar.YEAR, 2023);
        c5.set(java.util.Calendar.MONTH, 5);
        c5.set(java.util.Calendar.DAY_OF_MONTH, 3);
        d5.setTime(c5.getTimeInMillis());

        Date d6 = new Date();
        c5.set(java.util.Calendar.YEAR, 2023);
        c5.set(java.util.Calendar.MONTH, 5);
        c5.set(java.util.Calendar.DAY_OF_MONTH, 7);
        d6.setTime(c5.getTimeInMillis());
        BookingRequest b_r = tenants.findByID("t1").makeBookingRequest(listings.findByID(1), d5, d6);
        listings.findByID(1).getOwner().confirmBookingRequest(b_r);
    }

    @Test
    public void ownerViewListingTest() {
        // getListing()
        Listing ls = listings.findByID(1);
        Assert.assertEquals(ls.getListing_id(), presenter.getListing().getListing_id());

        // findTheBookings
        int bookings = presenter.findTheBookings(1, "2023-06");
        Assert.assertEquals(1, bookings);

        // setUpBasicInfo()
        presenter.setUpBasicInfo();
        view.setTitle("title");
        Assert.assertEquals("title", view.getTitle());
        view.setDesc("desc");
        Assert.assertEquals("desc", view.getDesc());
        Listing ls2 = presenter.getListing();
        ls2.setPhotos(null);

        presenter.setUpBasicInfo();
        Assert.assertEquals(R.drawable.child_po, view.getImg());


        view.setYear("2023");
        view.setMonth(6);
        presenter.submitPressed();
        Assert.assertEquals("224.0", view.getIncome());
        Assert.assertEquals("0", view.getCancellations());
        Assert.assertEquals("1", view.getBookings());
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.YEAR, 2023);
        c1.set(Calendar.MONTH, 6);
        c1.set(Calendar.DAY_OF_MONTH, 0);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        Assert.assertEquals(4.0, ls2.calculateOccupancy(c1), 0.0);

        HashMap<String, Double> mi = ls2.getMonthlyIncome();
        mi.put("2023-06", null);
        ls2.setMonthlyIncome(mi);

        HashMap<String, Integer> ca = ls2.getMonthlyCancellations();
        ca.put("2023-06", null);
        ls2.setMonthlyCancellations(ca);

        presenter.submitPressed();
        Assert.assertEquals("0.0", view.getIncome());
        Assert.assertEquals("0", view.getCancellations());

        ca.replace("2023-06", 1);
        ls2.setMonthlyCancellations(ca);
        presenter.submitPressed();
        Assert.assertEquals("1", view.getCancellations());

    }

    @Test
    public void deleteListingTest() {
        Assert.assertEquals(listings.findByID(1).getListing_id(), presenter.deleteListing().getListing_id());
    }
}
