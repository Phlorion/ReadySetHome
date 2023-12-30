package com.example.readysethome.view.BookingRequestGui;

public class ListingDetailsPresenter {

    private ListingDetailsView view;

    public ListingDetailsPresenter(ListingDetailsView view) {
        this.view = view;
    }

    public void onSubmitRequestClicked() {
        view.navigateToBookingRequest();
    }
}
