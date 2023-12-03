package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BathroomTest {
    private Bathroom b;
    @Before
    public void setUp() throws Exception {
        b = new Bathroom(8, true, true, true);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getSize() {
        Assert.assertEquals(8, b.getSize(), 0.1);
    }

    @Test
    public void setSize() {
        b.setSize(10);
        Assert.assertEquals(10, b.getSize(), 0.1);
    }

    @Test
    public void isShower() {
        assertTrue(b.isShower());
    }

    @Test
    public void setShower() {
        b.setShower(false);
        assertFalse(b.isShower());
    }

    @Test
    public void isToilet() {
        assertTrue(b.isToilet());
    }

    @Test
    public void setToilet() {
        b.setToilet(false);
        assertFalse(b.isToilet());
    }

    @Test
    public void isHairdryer() {
        assertTrue(b.isHairdryer());
    }

    @Test
    public void setHairdryer() {
        b.setHairdryer(false);
        assertFalse(b.isHairdryer());
    }
}