package com.example.readysethome.view.Tenant.TenantMain;

import com.example.readysethome.view.Tenant.TenantHomeListingModel;

public class TenantMainViewStub implements TenantMainView {
    TenantHomeListingModel model1;
    TenantHomeListingModel model2;
    TenantHomeListingModel model3;

    public TenantMainViewStub() {
        model1 = new TenantHomeListingModel("Cool apartment", "Small apartment in Athens.", "56.0€", 1, 1);
        model2 = new TenantHomeListingModel("Nice apartment", "Nice little apartment in Athens.", "64.0€", 2, 2);
        model3 = new TenantHomeListingModel("Big apartment", "Big and awesome apartment in Athens.", "112.0€", 3, 3);
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
