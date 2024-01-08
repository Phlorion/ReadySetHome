package com.example.readysethome.view.Tenant.TenantViewListing;

import static org.junit.Assert.assertThrows;

import com.example.readysethome.dao.Initializer;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.model.Listing;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TenantViewListingPresenterTest {
    TenantViewListingViewStub view;
    TenantViewListingPresenter presenter;
    Initializer dataHelper;
    ListingDAO listingDAO;

    TenantDAO tenantDAO;
    Listing listing;

    @Before
    public void setUp() {
        view = new TenantViewListingViewStub();
        listingDAO = new ListingDAOMemory();
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        tenantDAO=new TenantDAOMemory();

        presenter = new TenantViewListingPresenter(view, listingDAO, 1,"t1",tenantDAO);
        listing = listingDAO.findByID(1);
    }

    /**
     * Τα στοιχεία που εμφανίζονται για την αγγελία στην οθόνη του χρήστη
     */
    @Test
    public void setUpListingDetails() {
        presenter.setUpListingViewPage();

        Assert.assertEquals("Cool apartment", view.getTitle());
        Assert.assertEquals("Small apartment in Athens.", view.getDesc());
        Assert.assertEquals("56.0€", view.getPrice());
        Assert.assertEquals("Athens Str123 18", view.getLocation());
        Assert.assertEquals("28.0 m²", view.getSize());
    }

    /**
     * Το availability ενός listing
     */
    @Test
    public void checkIfListingIsAvailable() {
        // make the dates
        java.util.Calendar c = java.util.Calendar.getInstance();
        Date d1 = new Date();
        c.set(java.util.Calendar.YEAR, 2024);
        c.set(java.util.Calendar.MONTH, 0);
        c.set(java.util.Calendar.DAY_OF_MONTH, 13);
        d1.setTime(c.getTimeInMillis());
        Date d2 = new Date();
        c.set(java.util.Calendar.YEAR, 2024);
        c.set(java.util.Calendar.MONTH, 0);
        c.set(java.util.Calendar.DAY_OF_MONTH, 23);
        d2.setTime(c.getTimeInMillis());

        // available
        boolean result = presenter.isListingAvailable(d1, d2);
        Assert.assertTrue(result);

        // unavailable
        listing.getCalendar().setUnavailable(d1, d2);
        result = presenter.isListingAvailable(d1, d2);
        Assert.assertFalse(result);
    }

    /**
     * Parse a string to date
     */
    @Test
    public void checkStringToDateParser() throws ParseException {
        java.util.Calendar c = java.util.Calendar.getInstance();
        Date d1 = new Date();
        c.set(java.util.Calendar.YEAR, 2024);
        c.set(java.util.Calendar.MONTH, 0);
        c.set(java.util.Calendar.DAY_OF_MONTH, 13);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        d1.setTime(c.getTimeInMillis());

        // wrong date string format
        String my_date_wrong = "JAN, 13, 2024";
        Assert.assertThrows(ParseException.class, () -> presenter.parseDate(my_date_wrong));

        // correct date string format
        Date parsed_date;
        String my_date = "JAN 13, 2024";
        parsed_date = presenter.parseDate(my_date);

        Assert.assertEquals(d1.getTime() / 1000, parsed_date.getTime() / 1000);
    }

    /**
     * Get check in - check out
     */
    @Test
    public void getCheckInCheckOut() throws ParseException {
        String check_in_wrong = "JAN, 13, 2024";
        String check_out_wrong = "JAN, 23, 2024";
        Assert.assertThrows(ParseException.class, () -> presenter.setCheckIn(check_in_wrong));
        Assert.assertThrows(ParseException.class, () -> presenter.setCheckOut(check_out_wrong));

        String check_in = "JAN 13, 2024";
        String check_out = "JAN 23, 2024";

        presenter.setCheckIn(check_in);
        presenter.setCheckOut(check_out);
        Assert.assertEquals(check_in, view.getCheckInTV());
        Assert.assertEquals(check_out, view.getCheckOutTV());
    }

    /**
     * Create the DatePickerDialog
     */
    @Test
    public void datePickerDialogCreation() {
        Assert.assertNotNull(presenter.createDPD());
    }

    /**
     * Set available days in date picker dialog calendar
     */
    @Test
    public void setAvailableDaysTest() throws ParseException {
        // make the dates
        java.util.Calendar c = java.util.Calendar.getInstance();
        Date d1 = new Date();
        c.set(java.util.Calendar.YEAR, 2024);
        c.set(java.util.Calendar.MONTH, 0);
        c.set(java.util.Calendar.DAY_OF_MONTH, 13);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        d1.setTime(c.getTimeInMillis());
        Date d2 = new Date();
        c.set(java.util.Calendar.YEAR, 2024);
        c.set(java.util.Calendar.MONTH, 0);
        c.set(java.util.Calendar.DAY_OF_MONTH, 23);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        d2.setTime(c.getTimeInMillis());

        // set unavailable
        listing.getCalendar().setUnavailable(d1, d2);
        DatePickerDialog dpd = presenter.createDPD();

        // check in is null
        presenter.setAvailableInCalendar(dpd, true);

        // get the dpd dates
        List<Calendar> dis = Arrays.asList(dpd.getDisabledDays());
        Collections.sort(dis);
        for (Calendar d : dis) {
            System.out.println(d.getTime());
        }

        Date dpd_d1 = new Date(); Date dpd_d2 = new Date();
        dpd_d1.setTime(dis.get(0).getTimeInMillis());
        System.out.println(dpd_d1.getTime());
        dpd_d2.setTime(dis.get(10).getTimeInMillis());
        System.out.println(dpd_d2.getTime());

        Assert.assertEquals(d1.getTime() / 1000, dpd_d1.getTime() / 1000);
        Assert.assertEquals(d2.getTime() / 1000, dpd_d2.getTime() / 1000);
        Assert.assertEquals(listing.getCalendar().isAvailable(d1, d2), listing.getCalendar().isAvailable(dpd_d1, dpd_d2));

        // press check out while check in not null
        presenter.setCheckIn("JAN 13, 2024");
        presenter.setAvailableInCalendar(dpd, false);

        Assert.assertEquals(listing.getCalendar().isAvailable(d1, d2), listing.getCalendar().isAvailable(dpd_d1, dpd_d2));

        // press check in while check out not null
        presenter.setCheckOut("JAN 23, 2024");
        presenter.setAvailableInCalendar(dpd, true);

        Assert.assertEquals(listing.getCalendar().isAvailable(d1, d2), listing.getCalendar().isAvailable(dpd_d1, dpd_d2));
    }

    /**
     * Submit the booking request
     */
    @Test
    public void submitBookingRequestTest() throws ParseException {
        // make the dates
        java.util.Calendar c = java.util.Calendar.getInstance();
        Date d1 = new Date();
        c.set(java.util.Calendar.YEAR, 2024);
        c.set(java.util.Calendar.MONTH, 0);
        c.set(java.util.Calendar.DAY_OF_MONTH, 13);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        d1.setTime(c.getTimeInMillis());
        Date d2 = new Date();
        c.set(java.util.Calendar.YEAR, 2024);
        c.set(java.util.Calendar.MONTH, 0);
        c.set(java.util.Calendar.DAY_OF_MONTH, 23);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        d2.setTime(c.getTimeInMillis());

        listing.getCalendar().setUnavailable(d1, d2);

        // check in, check out == null
        presenter.handleSubmission();
        Assert.assertEquals("Please fill the necessary info", view.getError_msg());

        // apartment unavailable for the given dates
        presenter.setCheckIn("JAN 13, 2024");
        presenter.setCheckOut("JAN 23, 2024");
        presenter.handleSubmission();
        Assert.assertEquals("Unavailable", view.getError_msg());

        // submit successfully
        presenter.setCheckIn("JAN 24, 2024");
        presenter.setCheckIn("JAN 27, 2024");
        presenter.handleSubmission();
        Assert.assertEquals("Successful request.", view.getSuccessful_msg());
    }

}
