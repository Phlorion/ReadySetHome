// BookingRequestPresenter.java
package com.example.readysethome.view.BookingRequestGui;

import com.example.readysethome.model.Listing;

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
        view.updatePaymentAndDepositAmounts(
                paymentAmount

        );
        view.displayBookingConfirmation();
    }

    public void onCancelBookingRequest() {
        view.displayCancellationConfirmation();
    }

    private double calculatePaymentAmount() {
        if (listing != null) {
            return listing.getPrice();
        }
        return 0;
    }
}
