package com.example.readysethome.view.Tenant.TenantViewListing;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Date;

public class TenantViewListingViewStub implements TenantViewListingView, DatePickerDialog.OnDateSetListener {
    String title, desc, price, location, size, checkIn, checkOut,owner,bathrooms,bedrooms,kitchens,floor;
    boolean wifi,balcony;
    String error_msg, successful_msg, onDateSet;

    public TenantViewListingViewStub() {
        title = desc = price = location = size = checkIn = checkOut = error_msg = successful_msg = onDateSet = owner = bathrooms  = bedrooms = kitchens = floor ="";
        wifi =balcony=true;
    }

    @Override
    public void setListingTitle(String title) {
        this.title = title;
    }

    @Override
    public void setListingDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public void setListingPrice(String price) {
        this.price = price;
    }

    @Override
    public void setListingLocation(String location) {
        this.location = location;
    }

    @Override
    public void setListingSize(String size) {
        this.size = size;
    }
    @Override
    public String getCheckInTV() {
        return checkIn;
    }

    @Override
    public String getCheckOutTV() {
        return checkOut;
    }

    @Override
    public void setCheckInTV(String checkIn) {
        this.checkIn = checkIn;
    }

    @Override
    public void setCheckOutTV(String checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public void submit(Date checkInTime, Date checkOutTime, int listing_id, String tenant_id) {
        successful_msg = "Successful request.";
    }

    @Override
    public void showErrorMessage(String error) {
        this.error_msg = error;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getSize() {
        return size;
    }

    public String getOwner() {
        return owner;
    }

    public String getError_msg() {
        return error_msg;
    }

    public String getSuccessful_msg() {
        return successful_msg;
    }

    public String getOnDateSet() {
        return onDateSet;
    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        }
    }

