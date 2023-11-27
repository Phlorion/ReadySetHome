package com.example.readysethome;

import java.util.Date;

public class Owner extends User {
    public Owner(int id, String firstName, String lastName, EmailAddress email, Password password, CreditCard creditCard, Date acc_bday) {
        super(id, firstName, lastName, email, password, creditCard, acc_bday);
    }
}
