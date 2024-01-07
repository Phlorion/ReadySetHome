package com.example.readysethome.view.Tenant.BookingRequest;

import com.example.readysethome.view.BookingRequestGui.BookingConfirmationView;

public class BookingConfirmationViewStub implements BookingConfirmationView {

    private boolean displayConfirmationMessageCalled = false;
    private String confirmationMessage;

    private boolean navigateToHomePageCalled = false;
    private String navigateToHomePageTenantId;

    @Override
    public void displayConfirmationMessage(String message) {
        displayConfirmationMessageCalled = true;
        confirmationMessage = message;
    }

    @Override
    public void navigateToHomePage(String tenantId) {
        navigateToHomePageCalled = true;
        navigateToHomePageTenantId = tenantId;
    }

    public boolean isDisplayConfirmationMessageCalled() {
        return displayConfirmationMessageCalled;
    }

    public String getConfirmationMessage() {
        return confirmationMessage;
    }

    public boolean isNavigateToHomePageCalled() {
        return navigateToHomePageCalled;
    }

    public String getNavigateToHomePageTenantId() {
        return navigateToHomePageTenantId;
    }
}
