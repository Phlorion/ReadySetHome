package com.example.readysethome.view.Tenant.BookingRequest;

import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Tenant;
import com.example.readysethome.view.BookingRequestGui.BookingRequestPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class BookingRequestPresenterTest {

    private BookingRequestViewStub view;
    private BookingRequestPresenter presenter;
    private Date checkIn;
    private Date checkOut;
    private ListingDAO listings;
    private Listing listing;
    private TenantDAO tenants;
    private Tenant tenant;

    @Before
    public void setUp() {
        view = new BookingRequestViewStub();
        checkIn = new Date();
        checkOut = new Date();
        listings = new ListingDAOMemory();
        listing = new Listing();
        tenants = new TenantDAOMemory();
        tenant = new Tenant();

        // Assuming you have proper implementations for findByID in your DAOs
        listings.save(listing);
        tenants.save(tenant);

        presenter = new BookingRequestPresenter(view, checkIn, checkOut, listings, listing.getId(), tenant.getId(), tenants);
    }

    @Test
    public void testCalculatePaymentAmount() {
        listing.setPrice(100.0);
        int days = 5;
        when(listings.findByID(Mockito.anyInt())).thenReturn(listing);
        when(listing.daysBetween(Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(days);

        double payment = presenter.calculatePaymentAmount();

        Assert.assertEquals(500.0, payment, 0.01); // 100.0 * 5
    }

    @Test
    public void testTenantHasSufficientFunds() {
        tenant.setFunds(200.0);

        boolean hasSufficientFunds = presenter.tenantHasSufficientFunds();

        Assert.assertTrue(hasSufficientFunds);
    }

    @Test
    public void testOnSubmitBookingRequest() {
        tenant.setFunds(200.0);
        presenter.onSubmitBookingRequest();

        Assert.assertTrue(view.isConfirmCalled());
        Assert.assertEquals(checkIn, view.getConfirmCheckIn());
        Assert.assertEquals(checkOut, view.getConfirmCheckOut());
        Assert.assertEquals(listing.getId(), view.getConfirmListingId());
        Assert.assertEquals(tenant.getId(), view.getConfirmTenantId());
    }

    @Test
    public void testOnSubmitBookingRequestInsufficientFunds() {
        tenant.setFunds(50.0);
        presenter.onSubmitBookingRequest();

        Assert.assertTrue(view.isInsufficientFundsCalled());
        Assert.assertEquals(tenant.getId(), view.getInsufficientFundsTenantId());
    }

    @Test
    public void testOnCancelBookingRequest() {
        presenter.onCancelBookingRequest();

        Assert.assertTrue(view.isCancelCalled());
        Assert.assertEquals(tenant.getId(), view.getCancelTenantId());
    }
}
