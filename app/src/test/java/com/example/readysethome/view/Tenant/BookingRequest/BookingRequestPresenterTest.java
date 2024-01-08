package com.example.readysethome.view.Tenant.BookingRequest;

import com.example.readysethome.dao.Initializer;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.model.CreditCard;
import com.example.readysethome.model.EmailAddress;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Password;
import com.example.readysethome.model.Tenant;
import com.example.readysethome.view.BookingRequestGui.BookingRequestPresenter;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.TenantDAOMemory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class BookingRequestPresenterTest {

    private BookingRequestViewStub view;
    private BookingRequestPresenter presenter;
    private Date checkIn;
    private Date checkOut;
    private Listing listing;
    private ListingDAO listings;
    private TenantDAO tenants;
    private Tenant tenant;
    Initializer dataHelper;

    @Before
    public void setUp() {
        view = new BookingRequestViewStub();

        Calendar c = Calendar.getInstance();
        c.set(java.util.Calendar.YEAR, 2024);
        c.set(java.util.Calendar.MONTH, 5);
        c.set(java.util.Calendar.DAY_OF_MONTH, 13);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        checkIn = c.getTime();
        c.set(java.util.Calendar.YEAR, 2024);
        c.set(java.util.Calendar.MONTH, 5);
        c.set(java.util.Calendar.DAY_OF_MONTH, 23);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        checkOut = c.getTime();

        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        tenants = new TenantDAOMemory();
        listings = new ListingDAOMemory();

        tenant = tenants.findByID("t1");
        listing = listings.findByID(1);

        presenter = new BookingRequestPresenter(view, checkIn, checkOut, new ListingDAOMemory(), listing.getListing_id(), tenant.getId(), new TenantDAOMemory());
    }

    @Test
    public void testCalculatePaymentAmount() {
        // stay for 10 days (in setUp)
        listing.setPrice(100.0);
        double payment = presenter.calculatePaymentAmount();

        Assert.assertEquals(1000.0, payment, 0.01);
    }

    @Test
    public void testTenantHasSufficientFunds() {
        // tenant does have sufficient funds
        tenant.getCreditCard().setBalance(200.0);
        boolean hasSufficientFunds = presenter.tenantHasSufficientFunds();
        Assert.assertTrue(hasSufficientFunds);

        // tenant does not have sufficient funds
        tenant.getCreditCard().setBalance(0);
        hasSufficientFunds = presenter.tenantHasSufficientFunds();
        Assert.assertFalse(hasSufficientFunds);
    }

    @Test
    public void testOnSubmitBookingRequest() {
        tenant.getCreditCard().setBalance(200.0);
        presenter.onSubmitBookingRequest();

        Assert.assertTrue(view.isConfirmCalled());
        Assert.assertEquals(checkIn, view.getConfirmCheckIn());
        Assert.assertEquals(checkOut, view.getConfirmCheckOut());
        Assert.assertEquals(listing.getListing_id(), view.getConfirmListingId());
        Assert.assertEquals(tenant.getId(), view.getConfirmTenantId());
    }

    @Test
    public void testOnSubmitBookingRequestInsufficientFunds() {
        tenant.getCreditCard().setBalance(50.0);
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
