package com.example.readysethome.view.Tenant.TenantFilterListingsTest;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

import com.example.readysethome.view.Tenant.TenantFilterListings.FilterHolder;
import com.example.readysethome.view.Tenant.TenantFilterListings.TenantFilterListingsPresenter;
import com.example.readysethome.view.Tenant.TenantFilterListingsTest.TenantFilterListingsViewStub;

public class TenantFilterListingsPresenterTest {

    private TenantFilterListingsViewStub view;
    private TenantFilterListingsPresenter presenter;

    @Before
    public void setUp() {
        view = new TenantFilterListingsViewStub();
        presenter = new TenantFilterListingsPresenter(view);
    }

    @Test
    public void testReadFilters() {
        FilterHolder filterHolder = new FilterHolder(true, false, true, false, true, false, true, false, "100", "01-01-2022", "10-01-2022");
        presenter.readFilters(filterHolder);

        assertTrue(view.isAthChecked());
        assertFalse(view.isThesChecked());
        assertTrue(view.isPatraChecked());
        assertFalse(view.isHrakleioChecked());
        assertTrue(view.isIwanChecked());
        assertFalse(view.isVolosChecked());
        assertTrue(view.isSyrosChecked());
        assertFalse(view.isNafpionChecked());

        assertEquals("100", view.getWantedPrice());
    }

    @Test
    public void testAddFiltersWithValidDates() {
        view.setWantedCheckIn("01-01-2022");
        view.setWantedCheckOut("10-01-2022");

        FilterHolder filterHolder = presenter.addFilters();

        assertNotNull(filterHolder);
        assertTrue(view.showMessageCalled());
        assertEquals("Saved filters", view.getShowMessage());
    }

    @Test
    public void testAddFiltersWithInvalidDates() {
        view.setWantedCheckIn("01-01-2022");
        view.setWantedCheckOut("invalid-date");

        FilterHolder filterHolder = presenter.addFilters();

        assertNull(filterHolder);
        assertTrue(view.showMessageCalled());
        assertEquals("Invalid dates", view.getShowMessage());
    }

    @Test
    public void testAddFiltersWithNullCheckInAndCheckOut() {
        view.setWantedCheckIn("");
        view.setWantedCheckOut("01-01-2022");

        FilterHolder filterHolder = presenter.addFilters();

        assertNotNull(filterHolder);
        assertEquals("", view.getWantedCheckIn());
        assertEquals("", view.getWantedCheckOut());
        assertTrue(view.showMessageCalled());
        assertEquals("Saved filters", view.getShowMessage());
    }
}
