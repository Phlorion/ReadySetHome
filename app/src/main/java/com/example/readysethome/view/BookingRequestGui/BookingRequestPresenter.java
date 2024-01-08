// BookingRequestPresenter.java
package com.example.readysethome.view.BookingRequestGui;

import android.util.Log;

import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Tenant;

import java.util.Date;

public class BookingRequestPresenter {
    private BookingRequestView view;
    private Date checkIn;
    private Date checkOut;
    ListingDAO listings;
    private Listing listing;

    TenantDAO tenants;
    Tenant tenant;

    public BookingRequestPresenter(BookingRequestView view, Date checkIn, Date checkOut, ListingDAO listings, int listing_id, String tenant_id, TenantDAO tenants) {
        this.view = view;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.listings = listings;

        listing = listings.findByID(listing_id);
        this.tenants = tenants;
        this.tenant = tenants.findByID(tenant_id);

        if (tenant == null) {
            Log.e("BookingRequestPresenter", "Unable to find tenant with ID: " + tenant_id);
        }
    }

    /**
     * Υποβολή αιτήματος κράτησης.
     */
    public void onSubmitBookingRequest() {

        if (tenantHasSufficientFunds()) {
            view.confirm(checkIn, checkOut, listing.getListing_id(), tenant.getId());
        } else {
            view.insufficientFunds(tenant.getId());

        }
    }

    /**
     * Έλεγχος αν ο ενοικιαστής έχει επαρκή funds για την προκαταβολή.
     *
     * @return true αν ο ενοικιαστής έχει επαρκή funds, διαφορετικά false
     */
    public boolean tenantHasSufficientFunds() {
        if (tenant != null) {
            double paymentAmount = calculatePaymentAmount();
            return tenant.hasSufficientFunds(paymentAmount * 0.2);
        } else {
            Log.e("BookingRequestPresenter", "Tenant is null");
            return false; // Handle the case when tenant is null
        }
    }



    /**
     * Ακύρωση του αιτήματος κράτησης.
     */
    public void onCancelBookingRequest() {
        view.cancel( tenant.getId());
    }

    /**
     * Υπολογισμός του ποσού πληρωμής και ενημέρωση της προθεσμίας πληρωμής.
     *
     * @return Το ποσό πληρωμής
     */
    public double calculatePaymentAmount() {
        double payment = 0;
        if (listing != null) {
            payment = listing.getPrice() * listing.daysBetween(checkIn, checkOut);
            view.setPaymentAmount(Double.toString(payment) + "€");
            double depositAmount = 0.2 * payment;
            String formattedDeposit = String.format("%.2f€", depositAmount);
            view.setDepositAmount(formattedDeposit);
        }
        return payment;
    }
}
