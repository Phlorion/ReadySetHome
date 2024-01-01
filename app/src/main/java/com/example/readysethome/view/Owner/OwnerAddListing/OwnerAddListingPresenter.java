package com.example.readysethome.view.Owner.OwnerAddListing;

import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.model.Address;
import com.example.readysethome.model.Apartment;
import com.example.readysethome.model.Bathroom;
import com.example.readysethome.model.Bedroom;
import com.example.readysethome.model.Kitchen;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Owner;

import java.util.ArrayList;

public class OwnerAddListingPresenter {
    OwnerAddListingView view;
    ListingDAO listings;
    Owner owner;

    OwnerAddListingPresenter(OwnerAddListingView view, ListingDAO listings, Owner owner) {
        this.view = view;
        this.listings = listings;
        this.owner = owner;
    }

    /**
     * 'Ελεγχος αν το στοιχείο είναι κενό.
     * @param str Το στοιχείο
     * @return Ένα bool για το αν το στοιχείο είναι κενό ή όχι
     */
    private boolean isNull(String str) {
        return str.equals("");
    }

    /**
     * Owner add new listing
     */
    public Listing addListing() {
        String address_city = view.getCity();
        String address_street = view.getStreet();
        String address_number = view.getAdrNumber();
        String ap_floor = view.getFloor();
        String ap_size = view.getApSize();
        boolean wifi = view.getWifi();
        boolean livingRoom = view.getLivingRoom();
        boolean balcony = view.getBalcony();
        String bath_size = view.getBathSize();
        boolean bath_shower = view.getBathShower();
        boolean bath_toilet = view.getBathToilet();
        boolean bath_hairdryer = view.getHairdryer();
        String kitchen_size = view.getKitchenSize();
        boolean kitchen_oven = view.getKitchenOven();
        boolean kitchen_microwave = view.getKitchenMicrowave();
        boolean kitchen_refrigerator = view.getKitchenRefrigerator();
        boolean kitchen_toaster = view.getKitchenToaster();
        boolean kitchen_coffeeMachine = view.getKitchenCoffee();
        boolean kitchen_diningTable = view.getKitchenDiningTable();
        String bedroom_size = view.getBedroomSize();
        String bedroom_doubleBeds = view.getBedroomDoubleBeds();
        String bedroom_singleBeds = view.getBedroomSingleBeds();
        boolean bedroom_tv = view.getBedroomTV();
        String title = view.getListingTitle();
        String desc = view.getListingDescription();
        String price = view.getListingPrice();

        if (isNull(address_city) || isNull(address_street) || isNull(address_number) || isNull(ap_floor) || isNull(ap_size)
        || isNull(bath_size) || isNull(kitchen_size) || isNull(bedroom_size) || isNull(bedroom_doubleBeds) || isNull(bedroom_singleBeds)) {
            view.showErrorMessage("Σφάλμα!", "Παρακαλώ εισάγετε όλα τα παραπάνω στοιχεία.");
            return null;
        }

        // create apartment
        Address address = new Address(address_city, address_street, address_number);
        Bathroom bathroom = new Bathroom(Double.parseDouble(bath_size), bath_shower, bath_toilet, bath_hairdryer);
        ArrayList<Bathroom> bathrooms = new ArrayList<>();
        bathrooms.add(bathroom);
        Kitchen kitchen = new Kitchen(Double.parseDouble(kitchen_size), kitchen_oven, kitchen_microwave, kitchen_refrigerator, kitchen_toaster, kitchen_coffeeMachine, kitchen_diningTable);
        ArrayList<Kitchen> kitchens = new ArrayList<>();
        kitchens.add(kitchen);
        Bedroom bedroom = new Bedroom(Double.parseDouble(bedroom_size), Integer.parseInt(bedroom_doubleBeds), Integer.parseInt(bedroom_singleBeds), bedroom_tv);
        ArrayList<Bedroom> bedrooms = new ArrayList<>();
        bedrooms.add(bedroom);
        Apartment apartment = new Apartment(address, Integer.parseInt(ap_floor), Double.parseDouble(ap_size), wifi, balcony, livingRoom, bathrooms, bedrooms, kitchens);

        // create and save listing
        Listing listing = owner.addListing(apartment, title, desc, Double.parseDouble(price), false, null, null);
        listings.save(listing);

        return listing;
    }
}
