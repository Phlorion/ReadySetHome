package com.example.readysethome.view.Owner.OwnerAddListing;

import com.example.readysethome.view.Owner.OwnerAddListing.AddListingServices.AddListingServicesView;

public class OwnerAddListingServiceViewStub implements AddListingServicesView {
    String message;
    public OwnerAddListingServiceViewStub() {
        message = "";
    }

    @Override
    public void showMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
