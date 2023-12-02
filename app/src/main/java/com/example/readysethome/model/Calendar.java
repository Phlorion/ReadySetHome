package com.example.readysethome.model;

import java.util.Date;
import java.util.Hashtable;

public class Calendar {

    private Hashtable<Date, Date> availability;

    public Calendar() {
        availability = new Hashtable<>();
    }

    public Hashtable<Date, Date> getAvailability() {
        return availability;
    }

    public void setAvailability(Hashtable<Date, Date> availability) {
        this.availability = availability;
    }

    public void setUnavailable(Date check_in, Date check_out) {
        this.availability.put(check_in, check_out);
    }
}
