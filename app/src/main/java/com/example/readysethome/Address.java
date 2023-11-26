package com.example.readysethome;

public class Address {

    private String city;
    private String street;
    private String address_num;

    public Address(String city, String street, String address_num) {
        this.city = city;
        this.street = street;
        this.address_num = address_num;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress_num() {
        return address_num;
    }

    public void setAddress_num(String address_num) {
        this.address_num = address_num;
    }
}
