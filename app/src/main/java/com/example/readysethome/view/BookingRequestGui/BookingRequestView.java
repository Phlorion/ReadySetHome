package com.example.readysethome.view.BookingRequestGui;

public interface BookingRequestView {
    // TODO: Μπορείς να ονομάσεις αυτές τις μεθόδους confirm() και cancel(), επειδή δεν κάνουν κάτι display
    // TODO: απλά εκτελούν κάποια λειτουργία και δημιουργούν κάποιο intent.
    void displayBookingConfirmation();

    void displayCancellationConfirmation();

    void updatePaymentAndDepositAmounts(double paymentAmount);
}
