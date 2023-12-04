package com.example.readysethome.model;

import java.util.Date;

public class User {

    private static String last_tenant_ID = "t0";
    private static String last_owner_ID = "o0";
    private String id;
    private String firstName;
    private String lastName;
    private EmailAddress email;
    private Password password;
    private CreditCard creditCard;
    private Date acc_bday;

    public User (String firstName, String lastName, EmailAddress email, Password password, CreditCard creditCard, Date acc_bday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
        this.acc_bday = acc_bday;
    }

    // debugging
    public User (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String type) {
        if (type.equals("Tenant")) {
            char[] chars = last_tenant_ID.toCharArray();
            String temp = "";
            for (int i=1; i<chars.length; i++) {
                temp = temp + chars[i];
            }
            int temp_int = Integer.parseInt(temp);
            temp_int++;
            last_tenant_ID = "t" + temp_int;
            this.id = last_tenant_ID;
            // System.out.println(this.id);
        } else {
            char[] chars = last_owner_ID.toCharArray();
            String temp = "";
            for (int i=1; i<chars.length; i++) {
                temp = temp + chars[i];
            }
            int temp_int = Integer.parseInt(temp);
            temp_int++;
            last_owner_ID = "o" + temp_int;
            this.id = last_owner_ID;
        }
    }

    public String getAllDetails() {
        String temp = "";
        temp += this.id + ": " + this.firstName + " " + this.lastName + "\n";
        temp += this.email.getAddress() + "\n";
        temp += this.password.getPassword() + "\n";
        temp += this.creditCard.getNumber() + "\n";
        temp += "Account Creation: " + this.acc_bday;
        return temp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public void setEmail(EmailAddress email) {
        this.email = email;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Date getAcc_bday() {
        return acc_bday;
    }

    public void setAcc_bday(Date acc_bday) {
        this.acc_bday = acc_bday;
    }
}
