package com.example.readysethome.view.BookingRequestGui;
import java.util.Date;
public interface BookingRequestView {

    void setPaymentAmount(String paymentAmount);
    void setDepositAmount(String depositAmount);
    void confirm(double payment);
    void cancel();

}
