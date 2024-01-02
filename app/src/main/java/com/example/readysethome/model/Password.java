package com.example.readysethome.model;

import java.io.Serializable;

public class Password implements Serializable {

    private String password;

    public Password(String password) {
        this.password = password;
    }
    // debugging
    public Password() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
