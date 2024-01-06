package com.example.readysethome.view.BookingRequestGui;
import java.util.Date;
public interface BookingRequestView {

    void setPaymentAmount(String paymentAmount);
    void setDepositAmount(String depositAmount);
    void confirm(Date checkin,Date checkout,int listingid,String tenant_id);
    void cancel(String tenant_id);
    void insufficientFunds(String tenant_id);

}
