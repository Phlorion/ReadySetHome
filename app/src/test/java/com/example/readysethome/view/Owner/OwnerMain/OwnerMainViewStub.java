package com.example.readysethome.view.Owner.OwnerMain;

import com.example.readysethome.R;
import com.example.readysethome.view.Owner.OwnerHomeListingModel;

import java.util.ArrayList;

public class OwnerMainViewStub implements OwnerMainView {
    OwnerHomeListingModel model1;
    OwnerHomeListingModel model2;
    OwnerHomeListingModel model3;

    public OwnerMainViewStub() {
        model1 = new OwnerHomeListingModel("Cool apartment", "Small apartment in Athens.", "56.0€", 1);
        model2 = new OwnerHomeListingModel("Nice apartment", "Nice little apartment in Athens.", "64.0€", 2);
        model3 = new OwnerHomeListingModel("Big apartment", "Big and awesome apartment in Athens.", "112.0€", 3);
    }

    public OwnerHomeListingModel getModel1() {
        return model1;
    }

    public OwnerHomeListingModel getModel2() {
        return model2;
    }

    public OwnerHomeListingModel getModel3() {
        return model3;
    }

}
