package com.example.readysethome.view.Tenant.BookingRequest;

import com.example.readysethome.view.BookingRequestGui.BookingRequestView;

import java.util.Date;

public class BookingRequestViewStub implements BookingRequestView {
    private boolean confirmCalled = false;
    private boolean insufficientFundsCalled = false;
    private boolean cancelCalled = false;

    private Date confirmCheckIn;
    private Date confirmCheckOut;
    private int confirmListingId;
    private String confirmTenantId;
    private String insufficientFundsTenantId;
    private String cancelTenantId;

    private String paymentAmount;
    private String depositAmount;
    @Override
    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public void setDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
    }

    @Override
    public void confirm(Date checkIn, Date checkOut, int listingId, String tenantId) {
        confirmCalled = true;
        confirmCheckIn = checkIn;
        confirmCheckOut = checkOut;
        confirmListingId = listingId;
        confirmTenantId = tenantId;
    }

    @Override
    public void insufficientFunds(String tenantId) {
        insufficientFundsCalled = true;
        insufficientFundsTenantId = tenantId;
    }

    @Override
    public void cancel(String tenantId) {
        cancelCalled = true;
        cancelTenantId = tenantId;
    }



    public boolean isConfirmCalled() {
        return confirmCalled;
    }

    public boolean isInsufficientFundsCalled() {
        return insufficientFundsCalled;
    }

    public boolean isCancelCalled() {
        return cancelCalled;
    }

    public Date getConfirmCheckIn() {
        return confirmCheckIn;
    }

    public Date getConfirmCheckOut() {
        return confirmCheckOut;
    }

    public int getConfirmListingId() {
        return confirmListingId;
    }

    public String getConfirmTenantId() {
        return confirmTenantId;
    }

    public String getInsufficientFundsTenantId() {
        return insufficientFundsTenantId;
    }

    public String getCancelTenantId() {
        return cancelTenantId;
    }
}
