package com.example.readysethome.model;

public class EmailAddress {

    private String address;

    public EmailAddress(String address) {
        this.address = address;
    }
    // debugging
    public EmailAddress() {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int send(EmailAddress to, String Title, String Details) {return 0;}
}
