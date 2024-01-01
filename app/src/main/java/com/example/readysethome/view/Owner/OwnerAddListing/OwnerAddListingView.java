package com.example.readysethome.view.Owner.OwnerAddListing;

public interface OwnerAddListingView {

    String getCity();
    String getStreet();
    String getAdrNumber();
    String getFloor();
    String getApSize();
    boolean getWifi();
    boolean getLivingRoom();
    boolean getBalcony();
    String getBathSize();
    boolean getBathShower();
    boolean getBathToilet();
    boolean getHairdryer();
    String getKitchenSize();
    boolean getKitchenOven();
    boolean getKitchenMicrowave();
    boolean getKitchenRefrigerator();
    boolean getKitchenToaster();
    boolean getKitchenCoffee();
    boolean getKitchenDiningTable();
    String getBedroomSize();
    String getBedroomDoubleBeds();
    String getBedroomSingleBeds();
    boolean getBedroomTV();
    String getListingTitle();
    String getListingDescription();
    String getListingPrice();

    /**
     * Το μήνυμα που εμφανίζεται όταν τελειώνει
     * επιτυχώς ένα activity.
     * @param message Το μήνυμα που θα εμφανίσει
     */
    void successfullyFinishActivity(String message);

    /**
     * Το μήνυμα που εμφανίζεται σε
     * περίπτωση error.
     * @param title O τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showErrorMessage(String title, String message);
}
