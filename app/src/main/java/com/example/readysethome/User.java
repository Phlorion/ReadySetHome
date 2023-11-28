package com.example.readysethome;

import java.util.Date;

public class User {

    private static int last_tenant_ID = 0;
    private static int last_owner_ID = 0;
    private int id;
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

    public User (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(String type) {
        if (type.equals("Tenant")) {
            last_tenant_ID++;
            this.id = last_tenant_ID;
        } else {
            last_owner_ID++;
            this.id = last_owner_ID;
        }
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
