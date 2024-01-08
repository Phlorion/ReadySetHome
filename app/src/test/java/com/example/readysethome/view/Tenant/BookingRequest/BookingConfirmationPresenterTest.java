package com.example.readysethome.view.Tenant.BookingRequest;

import com.example.readysethome.dao.Initializer;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.model.CreditCard;
import com.example.readysethome.model.EmailAddress;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Password;
import com.example.readysethome.model.Tenant;
import com.example.readysethome.view.BookingRequestGui.BookingConfirmationPresenter;
import com.example.readysethome.view.Tenant.BookingRequest.BookingConfirmationViewStub;

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

    Initializer init;
    @Before
    public void setUp() {
        init = new MemoryInitializer();
        init.prepareData();

        view = new BookingConfirmationViewStub();
        listings = new ListingDAOMemory();
        listing = listings.findByID(1);
        tenants = new TenantDAOMemory();
        tenant = tenants.findByID("t1");

        checkIn = new Date();
        checkOut = new Date();

        presenter = new BookingConfirmationPresenter(view, listings, checkIn, checkOut, listing.getListing_id(), tenant.getId(), tenants);
    }

    @Test
    public void testOnViewCreated() {
        presenter.onViewCreated();

        Assert.assertTrue(view.isDisplayConfirmationMessageCalled());
        Assert.assertEquals("Booking Request confirmed!", view.getConfirmationMessage());

        Assert.assertFalse(view.isNavigateToHomePageCalled());
        presenter.onHomePageClicked();
        Assert.assertTrue(view.isNavigateToHomePageCalled());

        Assert.assertNotNull(presenter.getBookingrequest());

        Assert.assertEquals(listing, presenter.getBookingrequest().getListing());
        Assert.assertEquals(checkIn, presenter.getBookingrequest().getCheck_in());
        Assert.assertEquals(checkOut, presenter.getBookingrequest().getCheck_out());
    }
}
