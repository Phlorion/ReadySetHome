package com.example.readysethome.view.BookingRequestGui;

import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Tenant;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class BookingConfirmationPresenterTest {

    private BookingConfirmationViewStub view;
    private BookingConfirmationPresenter presenter;
    private ListingDAO listings;
    private Listing listing;
    private TenantDAO tenants;
    private Tenant tenant;
    private Date checkIn;
    private Date checkOut;

    @Before
    public void setUp() {
        view = new BookingConfirmationViewStub();
        listings = new ListingDAOMemory(); // Assuming you have an implementation for ListingDAOMemory
        listing = new Listing();
        tenants = new TenantDAOMemory(); // Assuming you have an implementation for TenantDAOMemory
        tenant = new Tenant();
        checkIn = new Date();
        checkOut = new Date();

        // Assuming you have proper implementations for findByID in your DAOs
        listings.save(listing);
        tenants.save(tenant);

        presenter = new BookingConfirmationPresenter(view, listings, checkIn, checkOut, listing.getId(), tenant.getId(), tenants);
    }

    @Test
    public void testOnViewCreated() {
        presenter.onViewCreated();

        Assert.assertTrue(view.isDisplayConfirmationMessageCalled());
        Assert.assertEquals("Booking Request confirmed!", view.getConfirmationMessage());

        Assert.assertTrue(view.isNavigateToHomePageCalled());
        Assert.assertEquals(tenant.getId(), view.getNavigateToHomePageTenantId());

        Assert.assertNotNull(presenter.getBookingrequest());
        Assert.assertEquals(listing, presenter.getBookingrequest().getListing());
        Assert.assertEquals(checkIn, presenter.getBookingrequest().getCheckIn());
        Assert.assertEquals(checkOut, presenter.getBookingrequest().getCheckOut());
    }
}
