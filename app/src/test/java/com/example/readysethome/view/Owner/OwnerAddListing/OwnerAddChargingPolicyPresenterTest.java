package com.example.readysethome.view.Owner.OwnerAddListing;

import com.example.readysethome.dao.Initializer;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.model.ChargingPolicy;
import com.example.readysethome.view.Owner.OwnerAddListing.AddChargingPolicies.AddChargingPolicyPresenter;
import com.example.readysethome.view.Owner.OwnerAddListing.AddChargingPolicies.ChargingPolicyModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class OwnerAddChargingPolicyPresenterTest {
    OwnerAddChargingPolicyViewStub view;
    AddChargingPolicyPresenter presenter;
    ListingDAO listingDAO;
    Initializer dataHelper;
    ArrayList<ChargingPolicy> chargingPolicies;

    @Before
    public void setUp() {
        view = new OwnerAddChargingPolicyViewStub();
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        listingDAO = new ListingDAOMemory();

        presenter = new AddChargingPolicyPresenter(view);

        chargingPolicies = new ArrayList<>();
        chargingPolicies.add(new ChargingPolicy(new Date(), new Date(), "", 12));

        presenter.setChargingPolicies(chargingPolicies);
        chargingPolicies = presenter.getChargingPolicies();
        presenter.setChargingPoliciesModels();
    }

    /**
     * Add charging policies
     */
    @Test
    public void addNewChargingPolicy() {
        presenter.addChargingPolicy("29-06-2024", "29-08-2024", "7", "");
        ArrayList<ChargingPolicyModel> models = presenter.getChargingPolicyModels();
        Assert.assertEquals(2, models.size());

        // null entry
        presenter.addChargingPolicy("", "29-08-2024", "7", "");
        Assert.assertEquals("Εισάγετε όλα τα δεδομένα", view.getMessage());

        // start_ind or end_ind don't match the string format
        presenter.addChargingPolicy("asgfasgasgsa", "29-08-2024", "7", "");
        Assert.assertEquals("Dates should be: dd-MM-yyyy", view.getMessage());

        // price dif not double
        presenter.addChargingPolicy("29-06-2024", "29-08-2024", "asdgasdg", "");
        Assert.assertEquals("Price dif must be a number", view.getMessage());
    }
}
