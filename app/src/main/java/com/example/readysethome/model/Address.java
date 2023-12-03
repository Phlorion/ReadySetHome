package com.example.readysethome.model;

import androidx.annotation.Nullable;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return city.equals(address.city) && street.equals(address.street) && address_num.equals(address.address_num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, address_num);
    }
}
