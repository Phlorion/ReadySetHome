package com.example.readysethome.view.Owner.OwnerAddListing;

import com.example.readysethome.dao.Initializer;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.model.ChargingPolicy;
import com.example.readysethome.model.ListingsServices;
import com.example.readysethome.model.ListingsServicesType;
import com.example.readysethome.view.Owner.OwnerAddListing.AddListingServices.AddListingServicesPresenter;
import com.example.readysethome.view.Owner.OwnerAddListing.AddListingServices.ListingServicesModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class OwnerAddListingServicePresenterTest {
    OwnerAddListingServiceViewStub view;
    AddListingServicesPresenter presenter;
    Initializer dataHelper;
    ArrayList<ListingsServices> listingServices;

    @Before
    public void setUp() {
        this.view = new OwnerAddListingServiceViewStub();
        this.dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        presenter = new AddListingServicesPresenter(view);

        listingServices = new ArrayList<>();
        listingServices.add(new ListingsServices(32, ListingsServicesType.CLEANING_SERVICE));

        presenter.setListingServices(listingServices);
        listingServices = presenter.getListingServices();
        presenter.setListingServicesModel();
    }

    /**
     * Add listing services
     */
    @Test
    public void addNewListingService() {
        presenter.addListingService("CLEANING SERVICE", "12");
        presenter.addListingService("WIFI", "12");
        presenter.addListingService("LINEN FEES", "12");
        presenter.addListingService("RESORT FEES", "12");
        ArrayList<ListingServicesModel> models = presenter.getListingServicesModel();
        Assert.assertEquals(5, models.size());

        // null entry
        presenter.addListingService("CLEANING SERVICE", "");
        Assert.assertEquals("Εισάγετε όλα τα δεδομένα", view.getMessage());

        // price not double
        presenter.addListingService("WIFI", "abcdefg");
        Assert.assertEquals("Price must be a number", view.getMessage());


    }
}
