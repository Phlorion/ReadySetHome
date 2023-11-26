package com.example.readysethome;

import java.util.Date;
import java.util.Hashtable;

public class Calendar {

    private Hashtable<Date, Boolean> availability;

    public Calendar() {
        availability = new Hashtable<>();
    }
}
