package com.example.readysethome.view.BookingRequestGui;

public class BookingConfirmationPresenter {

    private BookingConfirmationView view;

    public BookingConfirmationPresenter(BookingConfirmationView view) {
        this.view = view;
    }

    public void onViewCreated() {

        String confirmationMessage = "Booking confirmed!";
        view.displayConfirmationMessage(confirmationMessage);
    }

    public void onHomePageClicked() {
        view.navigateToHomePage();
    }
}
