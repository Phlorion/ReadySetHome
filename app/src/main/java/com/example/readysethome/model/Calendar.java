package com.example.readysethome.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class Calendar implements Serializable {

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

    public void setAvailable(Date check_in, Date check_out) {
        this.availability.remove(check_in, check_out);
    }

    public boolean isAvailable(Date start, Date end) {
        Enumeration<Date> e = availability.keys();
        while(e.hasMoreElements()) {
            Date key = e.nextElement();
            if (start.equals(key) || start.equals(availability.get(key)) || end.equals(key) || end.equals(availability.get(key))
            || (start.before(key) && end.after(key)) || (start.after(key) && start.before(availability.get(key)))) {
                return false;
            }
        }
        return true;
    }

    @NonNull
    @Override
    public String toString() {
        Enumeration<Date> e = availability.keys();
        String temp = "";
        while (e.hasMoreElements()) {
            Date key = e.nextElement();
            temp += "CheckIn: " + key + " CheckOut: " + availability.get(key) + "\n";
        }
        return temp;
    }
}
