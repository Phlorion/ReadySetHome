package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KitchenTest {
    private Kitchen k;
    @Before
    public void setUp() throws Exception {
        k = new Kitchen(8, true, true, true, true, true, true);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getSize() {
        Assert.assertEquals(8, k.getSize(), 0.1);
    }

    @Test
    public void setSize() {
        k.setSize(10);
        Assert.assertEquals(10, k.getSize(), 0.1);
    }

    @Test
    public void isOven() {
        assertTrue(k.isOven());
    }

    @Test
    public void setOven() {
        k.setOven(false);
        assertFalse(k.isOven());
    }

    @Test
    public void isMicrowave() {
        assertTrue(k.isMicrowave());
    }

    @Test
    public void setMicrowave() {
        k.setMicrowave(false);
        assertFalse(k.isMicrowave());
    }

    @Test
    public void isRefrigerator() {
        assertTrue(k.isRefrigerator());
    }

    @Test
    public void setRefrigerator() {
        k.setRefrigerator(false);
        assertFalse(k.isRefrigerator());
    }

    @Test
    public void isToaster() {
        assertTrue(k.isToaster());
    }

    @Test
    public void setToaster() {
        k.setToaster(false);
        assertFalse(k.isToaster());
    }

    @Test
    public void isCoffee_machine() {
        assertTrue(k.isCoffee_machine());
    }

    @Test
    public void setCoffee_machine() {
        k.setCoffee_machine(false);
        assertFalse(k.isCoffee_machine());
    }

    @Test
    public void isDining_table() {
        assertTrue(k.isDining_table());
    }

    @Test
    public void setDining_table() {
        k.setDining_table(false);
        assertFalse(k.isDining_table());
    }
}