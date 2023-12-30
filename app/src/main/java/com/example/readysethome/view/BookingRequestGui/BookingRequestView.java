package com.example.readysethome.view.BookingRequestGui;

public interface BookingRequestView {
    void displayBookingConfirmation();

    void displayCancellationConfirmation();

    void updatePaymentAndDepositAmounts(double paymentAmount);
}
