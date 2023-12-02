package com.example.readysethome.model;

public class CreditCard {

    private long number;
    private double balance;

    public CreditCard (long number) {
        this.number = number;
    }
    public CreditCard () {}

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int validate() {return 0;}

    public void makePayment(double amount) {
        balance = balance - amount;
    }

    public void refund(double amount) {
        balance = balance + amount;
    }
}
