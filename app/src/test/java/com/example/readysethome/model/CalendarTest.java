package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class CalendarTest {
    private Calendar c;
    @Before
    public void setUp() throws Exception {
        c = new Calendar();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAvailability() {
        Hashtable<Date, Date> avail = new Hashtable<>();
        Assert.assertEquals(avail, c.getAvailability());
    }

    @Test
    public void setAvailability() {
        Hashtable<Date, Date> avail = new Hashtable<>();
        c.setAvailability(avail);
        Assert.assertEquals(avail, c.getAvailability());
    }

    @Test
    public void setUnavailable() {
        Hashtable<Date, Date> avail = new Hashtable<>();
        Date check_in = new Date();
        Date check_out = new Date();
        c.setUnavailable(check_in, check_out);
        avail.put(check_in, check_out);
        Assert.assertEquals(avail, c.getAvailability());
    }

    @Test
    public void isAvailable() {
        java.util.Calendar check_in_c = java.util.Calendar.getInstance();
        check_in_c.set(2023, 11, 1);
        Date check_in = check_in_c.getTime();
        java.util.Calendar check_out_c = java.util.Calendar.getInstance();
        check_out_c.set(2023, 11, 13);
        Date check_out = check_out_c.getTime();
        c.setUnavailable(check_in, check_out);
        assertFalse(c.isAvailable(check_in, check_out));

        java.util.Calendar other_check_in_c = java.util.Calendar.getInstance();
        other_check_in_c.set(2023, 10, 23);
        Date other_check_in = other_check_in_c.getTime();
        java.util.Calendar other_check_out_c = java.util.Calendar.getInstance();
        other_check_out_c.set(2023, 10, 28);
        Date other_check_out = other_check_out_c.getTime();
        assertTrue(c.isAvailable(other_check_in, other_check_out));

    }

    @Test
    public void testToString() {
        Hashtable<Date, Date> avail = new Hashtable<>();
        Date check_in = new Date();
        Date check_out = new Date();
        c.setUnavailable(check_in, check_out);
        avail.put(check_in, check_out);
        Enumeration<Date> e = avail.keys();
        String temp = "";
        while (e.hasMoreElements()) {
            Date key = e.nextElement();
            temp += "CheckIn: " + key + " CheckOut: " + avail.get(key) + "\n";
        }
        Assert.assertEquals(temp, c.toString());
    }
}