package com.example.readysethome.view.Tenant.TenantMain;

import com.example.readysethome.model.Calendar;
import com.example.readysethome.model.Listing;
import com.example.readysethome.view.Tenant.TenantHomeListingModel;

public class TenantMainViewStub implements TenantMainView {
    TenantHomeListingModel model1;
    TenantHomeListingModel model2;
    TenantHomeListingModel model3;

    public TenantMainViewStub() {
        model1 = new TenantHomeListingModel("Cool apartment", "Small apartment in Athens.", "56.0€", "Address1", 1, 1, new Listing());
        model2 = new TenantHomeListingModel("Nice apartment", "Nice little apartment in Athens.", "64.0€", "Address2", 2, 2, new Listing());
        model3 = new TenantHomeListingModel("Big apartment", "Big and awesome apartment in Athens.", "112.0€", "Address3", 3, 3, new Listing());
    }

    public TenantHomeListingModel getModel1() {
        return model1;
    }

    public TenantHomeListingModel getModel2() {
        return model2;
    }

    public TenantHomeListingModel getModel3() {
        return model3;
    }
}
