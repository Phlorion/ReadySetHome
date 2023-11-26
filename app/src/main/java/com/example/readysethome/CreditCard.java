package com.example.readysethome;

public class CreditCard {

    private long number;

    public CreditCard (long number) {
        this.number = number;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int validate() {return 0;}
}
