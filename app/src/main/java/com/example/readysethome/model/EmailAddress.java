package com.example.readysethome.model;

import java.io.Serializable;
import java.util.Objects;

public class EmailAddress implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailAddress)) return false;
        EmailAddress that = (EmailAddress) o;
        return address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
