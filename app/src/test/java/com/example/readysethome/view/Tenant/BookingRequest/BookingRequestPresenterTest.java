package com.example.readysethome.view.Tenant.BookingRequest;

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

import java.util.Date;

public class BookingRequestPresenterTest {

    private BookingRequestViewStub view;
    private BookingRequestPresenter presenter;
    private Date checkIn;
    private Date checkOut;
    private Listing listing;
    private Tenant tenant;

    @Before
    public void setUp() {
        view = new BookingRequestViewStub();
        checkIn = new Date();
        checkOut = new Date();
        listing = new Listing();
        tenant = new Tenant("John", "Doe", new EmailAddress("john@example.com"), new Password("password"), new CreditCard(), new Date());

        presenter = new BookingRequestPresenter(view, checkIn, checkOut, new ListingDAOMemory(), listing.getListing_id(), tenant.getId(), new TenantDAOMemory());
    }

    @Test
    public void testCalculatePaymentAmount() {
        listing.setPrice(100.0);
        double payment = presenter.calculatePaymentAmount();

        Assert.assertEquals(500.0, payment, 0.01); // 100.0 * 5
    }

    @Test
    public void testTenantHasSufficientFunds() {
        tenant.getCreditCard().setBalance(200.0);
        boolean hasSufficientFunds = presenter.tenantHasSufficientFunds();

        Assert.assertTrue(hasSufficientFunds);
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
