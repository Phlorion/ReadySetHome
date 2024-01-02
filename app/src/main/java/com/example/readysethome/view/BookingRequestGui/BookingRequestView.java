package com.example.readysethome.view.BookingRequestGui;
import java.util.Date;
public interface BookingRequestView {

    void Confirmation();

    void Cancellation();

    void updatePaymentAndDepositAmounts();

    Date getCheckin();
    Date getCheckout();

}
