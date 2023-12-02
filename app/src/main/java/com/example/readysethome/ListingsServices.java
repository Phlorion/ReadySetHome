package com.example.readysethome;

public class ListingsServices {
    private double price;
    private ListingsServicesType type;

    public ListingsServices(double price, ListingsServicesType type) {
        this.price = price;
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ListingsServicesType getType() {
        return type;
    }

    public void setType(ListingsServicesType type) {
        this.type = type;
    }
}
