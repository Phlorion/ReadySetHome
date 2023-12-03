package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BedroomTest {
    private Bedroom b;
    @Before
    public void setUp() throws Exception {
        b = new Bedroom(20, 1, 2, true);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getSize() {
        Assert.assertEquals(20, b.getSize(), 0.1);
    }

    @Test
    public void setSize() {
        b.setSize(25);
        Assert.assertEquals(25, b.getSize(), 0.1);
    }

    @Test
    public void getDouble_beds() {
        Assert.assertEquals(1, b.getDouble_beds());
    }

    @Test
    public void setDouble_beds() {
        b.setDouble_beds(3);
        Assert.assertEquals(3, b.getDouble_beds());
    }

    @Test
    public void getSingle_beds() {
        Assert.assertEquals(2, b.getSingle_beds());
    }

    @Test
    public void setSingle_beds() {
        b.setSingle_beds(3);
        Assert.assertEquals(3, b.getSingle_beds());
    }

    @Test
    public void isTv() {
        assertTrue(b.isTv());
    }

    @Test
    public void setTv() {
        b.setTv(false);
        assertFalse(b.isTv());
    }
}