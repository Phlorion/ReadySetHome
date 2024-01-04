package com.example.readysethome.view.Owner.OwnerAddListing;

public class OwnerAddListingViewStub implements OwnerAddListingView {
    String city, street, address_num, floor, ap_size, bath_size, kitchen_size, bedroom_size, double_beds, single_beds, title, desc, price;
    boolean wifi, living_room, balcony, shower, toilet, hairdryer, oven, microwave, refrigerator, toaster, coffee_machine, dining_table, tv;
    String error_title, error_msg, success_msg;

    public OwnerAddListingViewStub() {
        city = street = address_num = floor = ap_size = bath_size = kitchen_size = bedroom_size = double_beds = single_beds = title = desc = price = "";
        wifi = living_room = balcony = shower = toilet = hairdryer = oven = microwave = refrigerator = toaster = coffee_machine = dining_table = tv = true;
        error_title = error_msg = success_msg = "";
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public String getAdrNumber() {
        return address_num;
    }

    @Override
    public String getFloor() {
        return floor;
    }

    @Override
    public String getApSize() {
        return ap_size;
    }

    @Override
    public boolean getWifi() {
        return wifi;
    }

    @Override
    public boolean getLivingRoom() {
        return living_room;
    }

    @Override
    public boolean getBalcony() {
        return balcony;
    }

    @Override
    public String getBathSize() {
        return bath_size;
    }

    @Override
    public boolean getBathShower() {
        return shower;
    }

    @Override
    public boolean getBathToilet() {
        return toilet;
    }

    @Override
    public boolean getHairdryer() {
        return hairdryer;
    }

    @Override
    public String getKitchenSize() {
        return kitchen_size;
    }

    @Override
    public boolean getKitchenOven() {
        return oven;
    }

    @Override
    public boolean getKitchenMicrowave() {
        return microwave;
    }

    @Override
    public boolean getKitchenRefrigerator() {
        return refrigerator;
    }

    @Override
    public boolean getKitchenToaster() {
        return toaster;
    }

    @Override
    public boolean getKitchenCoffee() {
        return coffee_machine;
    }

    @Override
    public boolean getKitchenDiningTable() {
        return dining_table;
    }

    @Override
    public String getBedroomSize() {
        return bedroom_size;
    }

    @Override
    public String getBedroomDoubleBeds() {
        return double_beds;
    }

    @Override
    public String getBedroomSingleBeds() {
        return single_beds;
    }

    @Override
    public boolean getBedroomTV() {
        return tv;
    }

    @Override
    public String getListingTitle() {
        return title;
    }

    @Override
    public String getListingDescription() {
        return desc;
    }

    @Override
    public String getListingPrice() {
        return price;
    }

    public String getError_title() {
        return error_title;
    }

    public String getError_msg() {
        return error_msg;
    }

    public String getSuccess_msg() {
        return success_msg;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setAddress_num(String address_num) {
        this.address_num = address_num;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setAp_size(String ap_size) {
        this.ap_size = ap_size;
    }

    public void setBath_size(String bath_size) {
        this.bath_size = bath_size;
    }

    public void setKitchen_size(String kitchen_size) {
        this.kitchen_size = kitchen_size;
    }

    public void setBedroom_size(String bedroom_size) {
        this.bedroom_size = bedroom_size;
    }

    public void setDouble_beds(String double_beds) {
        this.double_beds = double_beds;
    }

    public void setSingle_beds(String single_beds) {
        this.single_beds = single_beds;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public void setLiving_room(boolean living_room) {
        this.living_room = living_room;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public void setShower(boolean shower) {
        this.shower = shower;
    }

    public void setToilet(boolean toilet) {
        this.toilet = toilet;
    }

    public void setHairdryer(boolean hairdryer) {
        this.hairdryer = hairdryer;
    }

    public void setOven(boolean oven) {
        this.oven = oven;
    }

    public void setMicrowave(boolean microwave) {
        this.microwave = microwave;
    }

    public void setRefrigerator(boolean refrigerator) {
        this.refrigerator = refrigerator;
    }

    public void setToaster(boolean toaster) {
        this.toaster = toaster;
    }

    public void setCoffee_machine(boolean coffee_machine) {
        this.coffee_machine = coffee_machine;
    }

    public void setDining_table(boolean dining_table) {
        this.dining_table = dining_table;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    @Override
    public void successfullyFinishActivity(String message) {
        success_msg = message;
    }

    @Override
    public void showErrorMessage(String title, String message) {
        error_title = title;
        error_msg = message;
    }
}
