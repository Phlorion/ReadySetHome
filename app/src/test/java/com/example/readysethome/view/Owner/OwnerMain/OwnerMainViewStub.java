package com.example.readysethome.view.Owner.OwnerMain;

import com.example.readysethome.R;
import com.example.readysethome.model.Owner;
import com.example.readysethome.view.Owner.OwnerHomeListingModel;
import com.example.readysethome.view.Owner.OwnerPendingModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OwnerMainViewStub implements OwnerMainView {
    OwnerHomeListingModel model1;
    OwnerHomeListingModel model2;
    OwnerHomeListingModel model3;
    OwnerPendingModel pendingModel1;
    OwnerPendingModel pendingModel2;

    public OwnerMainViewStub() {
        model1 = new OwnerHomeListingModel("Cool apartment", "Small apartment in Athens.", "56.0€", 1, 1);
        model2 = new OwnerHomeListingModel("Nice apartment", "Nice little apartment in Athens.", "64.0€", 2, 2);
        model3 = new OwnerHomeListingModel("Big apartment", "Big and awesome apartment in Athens.", "112.0€", 3, 3);
        java.util.Calendar c = java.util.Calendar.getInstance();
        Date d2 = new Date();
        c.set(java.util.Calendar.YEAR, 2023);
        c.set(java.util.Calendar.MONTH, 5);
        c.set(java.util.Calendar.DAY_OF_MONTH, 14);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        d2.setTime(c.getTimeInMillis());

        Date d3 = new Date();
        c.set(java.util.Calendar.YEAR, 2023);
        c.set(java.util.Calendar.MONTH, 5);
        c.set(java.util.Calendar.DAY_OF_MONTH, 27);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        d3.setTime(c.getTimeInMillis());
        pendingModel1 = new OwnerPendingModel("Anestis Thanasi", "Cool apartment", 1, 1, d2, d3);

        java.util.Calendar c5 = java.util.Calendar.getInstance();
        Date d5 = new Date();
        c5.set(java.util.Calendar.YEAR, 2023);
        c5.set(java.util.Calendar.MONTH, 5);
        c5.set(java.util.Calendar.DAY_OF_MONTH, 3);
        d5.setTime(c5.getTimeInMillis());

        java.util.Calendar c6 = java.util.Calendar.getInstance();
        Date d6 = new Date();
        c6.set(java.util.Calendar.YEAR, 2023);
        c6.set(java.util.Calendar.MONTH, 5);
        c6.set(java.util.Calendar.DAY_OF_MONTH, 7);
        d6.setTime(c6.getTimeInMillis());
        pendingModel2 = new OwnerPendingModel("Anestis Thanasi", "Cool apartment", 1, 2, d5, d6);
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

    public OwnerPendingModel getPendingModel1() {
        return pendingModel1;
    }

    public OwnerPendingModel getPendingModel2() {
        return pendingModel2;
    }
}
