// BookingRequestPresenter.java
package com.example.readysethome.view.BookingRequestGui;

import com.example.readysethome.model.Listing;
import java.util.Date;

public class BookingRequestPresenter {
    private BookingRequestView view;
    private Listing listing;

    public BookingRequestPresenter(BookingRequestView view) {
        this.view = view;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public void onSubmitBookingRequest() {
        double paymentAmount = calculatePaymentAmount();
        view.updatePaymentAndDepositAmounts();
        view.Confirmation();
    }

    public void onCancelBookingRequest() {
        view.Cancellation();
    }

    private double calculatePaymentAmount() {
        if (listing != null) {
            return listing.getPrice()* listing.daysBetween(view.getCheckin(),view.getCheckout());
        }
        return 0;
    }
}
