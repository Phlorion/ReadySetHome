package com.example.readysethome.model;

import java.io.Serializable;

public class CreditCard implements Serializable {

    private String number;
    private double balance;

    public CreditCard (String number) {
        this.number = number;
    }
    // debugging
    public CreditCard(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }
    public CreditCard () {}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean makePayment(double amount) {
        if (amount > this.balance || amount < 0) return false;
        balance = balance - amount;
        return true;
    }

    public boolean refund(double amount) {
        if (amount < 0) return false;
        balance = balance + amount;
        return true;
    }
}
