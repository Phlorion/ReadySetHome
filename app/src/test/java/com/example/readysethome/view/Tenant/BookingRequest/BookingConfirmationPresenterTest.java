package com.example.readysethome.view.Tenant.BookingRequest;

import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.memorydao.ListingDAOMemory;
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

    @Before
    public void setUp() {
        view = new BookingConfirmationViewStub();
        listings = new ListingDAOMemory();
        listing = new Listing();
        tenants = new TenantDAOMemory();
        tenant = new Tenant("John", "Doe", new EmailAddress("john@example.com"), new Password("password"), new CreditCard(), new Date());
        checkIn = new Date();
        checkOut = new Date();


        listings.save(listing);
        tenants.save(tenant);

        presenter = new BookingConfirmationPresenter(view, listings, checkIn, checkOut, listing.getListing_id(), tenant.getId(), tenants);
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
        Assert.assertEquals(checkIn, presenter.getBookingrequest().getCheck_in());
        Assert.assertEquals(checkOut, presenter.getBookingrequest().getCheck_out());
    }
}
