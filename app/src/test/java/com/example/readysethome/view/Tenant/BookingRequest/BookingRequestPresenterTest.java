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

import java.util.Calendar;
import java.util.Date;

/**
 * Δοκιμαστική κλάση για την {@link BookingRequestPresenter}.
 */
public class BookingRequestPresenterTest {

    private BookingRequestViewStub view;
    private BookingRequestPresenter presenter;
    private Date checkIn;
    private Date checkOut;
    private Listing listing;
    private Tenant tenant;

    /**
     * Ρύθμιση του περιβάλλοντος δοκιμών.
     */
    @Before
    public void setUp() {
        // Αρχικοποίηση δεδομένων δοκιμής
        view = new BookingRequestViewStub();
        checkIn = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkIn);
        calendar.add(Calendar.DAY_OF_YEAR, 5);
        checkOut = calendar.getTime();
        listing = new Listing();
        tenant = new Tenant("John", "Doe", new EmailAddress("john@example.com"),
                new Password("password"), new CreditCard(), new Date());

        // Δημιουργία της κλάσης BookingRequestPresenter για δοκιμές
        presenter = new BookingRequestPresenter(view, checkIn, checkOut,
                new ListingDAOMemory(), listing.getListing_id(),
                tenant.getId(), new TenantDAOMemory());
    }

    /**
     * Δοκιμή του υπολογισμού του ποσού πληρωμής.
     */
    @Test
    public void testCalculatePaymentAmount() {
        listing.setPrice(100.0);
        double payment = presenter.calculatePaymentAmount();

        // Επιβεβαίωση ότι το υπολογισμένο ποσό είναι όπως αναμένεται
        Assert.assertEquals(500.0, payment, 0.01); // 100.0 * 5
    }

    /**
     * Δοκιμή αν ο ενοικιαστής έχει επαρκή κεφάλαια.
     */
    @Test
    public void testTenantHasSufficientFunds() {
        tenant.getCreditCard().setBalance(200.0);
        boolean hasSufficientFunds = presenter.tenantHasSufficientFunds();

        // Επιβεβαίωση ότι ο ενοικιαστής έχει επαρκή κεφάλαια
        Assert.assertTrue(hasSufficientFunds);
    }

    /**
     * Δοκιμή υποβολής αιτήματος κράτησης με επαρκή funds.
     */
    @Test
    public void testOnSubmitBookingRequest() {
        tenant.getCreditCard().setBalance(200.0);
        presenter.onSubmitBookingRequest();

        // Επιβεβαίωση ότι η μέθοδος επιβεβαίωσης καλείται με τις σωστές παραμέτρους
        Assert.assertTrue(view.isConfirmCalled());
        Assert.assertEquals(checkIn, view.getConfirmCheckIn());
        Assert.assertEquals(checkOut, view.getConfirmCheckOut());
        Assert.assertEquals(listing.getListing_id(), view.getConfirmListingId());
        Assert.assertEquals(tenant.getId(), view.getConfirmTenantId());
    }

    /**
     * Δοκιμή υποβολής αιτήματος κράτησης με έλλειψη funds.
     */
    @Test
    public void testOnSubmitBookingRequestInsufficientFunds() {
        tenant.getCreditCard().setBalance(50.0);
        presenter.onSubmitBookingRequest();

        // Επιβεβαίωση ότι η μέθοδος έλλειψης κεφαλαίων καλείται με τον σωστό αριθμό ακινήτων
        Assert.assertTrue(view.isInsufficientFundsCalled());
        Assert.assertEquals(tenant.getId(), view.getInsufficientFundsTenantId());
    }

    /**
     * Δοκιμή ακύρωσης αιτήματος κράτησης.
     */
    @Test
    public void testOnCancelBookingRequest() {
        presenter.onCancelBookingRequest();

        // Επιβεβαίωση ότι η μέθοδος ακύρωσης καλείται με τον σωστό αριθμό ακινήτων
        Assert.assertTrue(view.isCancelCalled());
        Assert.assertEquals(tenant.getId(), view.getCancelTenantId());
    }
}
