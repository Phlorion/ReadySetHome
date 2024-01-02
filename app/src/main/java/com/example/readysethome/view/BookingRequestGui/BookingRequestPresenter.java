// BookingRequestPresenter.java
package com.example.readysethome.view.BookingRequestGui;

import com.example.readysethome.model.Listing;
import java.util.Date;

public class BookingRequestPresenter {
    private BookingRequestView view;
    private Date checkIn;
    private Date checkOut;
    private Listing listing;

    public BookingRequestPresenter(BookingRequestView view, Date checkIn, Date checkOut) {
        this.view = view;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public void onSubmitBookingRequest(double payment) {
        view.confirm(payment);
    }

    public void onCancelBookingRequest() {
        view.cancel();
    }

    public double calculatePaymentAmount() {
        double payment = 0;
        if (listing != null) {
            payment = listing.getPrice() * listing.daysBetween(checkIn, checkOut);
            view.setPaymentAmount(Double.toString(payment) + "€");
            view.setDepositAmount(Double.toString(0.2 * payment) + "€");
        }
        return payment;
    }
}
