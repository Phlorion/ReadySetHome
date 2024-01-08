package com.example.readysethome.view.Owner.OwnerAddListing.AddListingServices;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readysethome.model.ChargingPolicy;
import com.example.readysethome.model.ListingsServices;
import com.example.readysethome.model.ListingsServicesType;
import com.example.readysethome.view.Owner.OwnerAddListing.AddChargingPolicies.ChargingPolicyModel;

import java.util.ArrayList;

public class AddListingServicesPresenter {
    AddListingServicesView view;
    ArrayList<ListingServicesModel> listingServicesModel;
    ArrayList<ListingsServices> listingServices;
    RecyclerView recyclerView;
    ListingServicesAdaptor adapter;

    /**
     * 'Ελεγχος αν το στοιχείο είναι κενό.
     * @param str Το στοιχείο
     * @return Ένα bool για το αν το στοιχείο είναι κενό ή όχι
     */
    private boolean isNull(String str) {
        return str.equals("");
    }

    public AddListingServicesPresenter(AddListingServicesView view, RecyclerView recyclerView) {
        this.view = view;
        this.recyclerView = recyclerView;

        this.listingServices = new ArrayList<>();
        this.listingServicesModel = new ArrayList<>();
    }

    public void setAdapter() {
        adapter = new ListingServicesAdaptor((Context) view, listingServicesModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((Context) view));
    }

    public void addListingService(String type, String price) {
        // check if null strings
        if (isNull(price)) {
            view.showMessage("Εισάγετε όλα τα δεδομένα");
            return;
        }

        // price not double
        try {
            Double.parseDouble(price);
        } catch (NumberFormatException e) {
            view.showMessage("Price dif must be a number");
            return;
        }

        ListingsServicesType l_s_type = null;
        switch (type) {
            case "CLEANING SERVICE":
                l_s_type = ListingsServicesType.CLEANING_SERVICE;
                break;
            case "WIFI":
                l_s_type = ListingsServicesType.WIFI;
                break;
            case "LINEN FEES":
                l_s_type = ListingsServicesType.LINEN_FEES;
                break;
            case "RESORT FEES":
                l_s_type = ListingsServicesType.RESORT_FEES;
                break;
            default:
                break;
        }

        ListingsServices listingsService = new ListingsServices(Double.parseDouble(price), l_s_type);
        listingServices.add(listingsService);

        listingServicesModel.add(new ListingServicesModel(type, price));
        adapter.setListingServicesModels(listingServicesModel);
    }

    public ArrayList<ListingsServices> getListingServices() {
        return listingServices;
    }

    public void setListingServices(ArrayList<ListingsServices> listingServices) {
        this.listingServices = listingServices;
    }

    public void setListingServicesModel() {
        for (ListingsServices l_s : listingServices) {
            listingServicesModel.add(new ListingServicesModel(l_s.getType().toString(), Double.toString(l_s.getPrice())));
        }
        adapter.setListingServicesModels(listingServicesModel);
    }
}
