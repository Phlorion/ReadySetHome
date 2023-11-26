package com.example.readysethome;

import java.util.Date;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private EmailAddress email;
    private Password password;
    private CreditCard creditCard;
    private Date acc_bday;

    public User (int id, String firstName, String lastName, EmailAddress email, Password password, CreditCard creditCard, Date acc_bday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
        this.acc_bday = acc_bday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
