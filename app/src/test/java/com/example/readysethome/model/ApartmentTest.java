package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;

import java.util.ArrayList;
import java.util.Objects;

public class ApartmentTest {
    private Apartment ap;
    ArrayList<Bathroom> bathrooms;
    ArrayList<Kitchen> kitchens;
    ArrayList<Bedroom> bedrooms;

    @Before
    public void setUp() throws Exception {
        bathrooms = new ArrayList<>();
        bathrooms.add(new Bathroom(8, true, true, true));
        kitchens = new ArrayList<>();
        kitchens.add(new Kitchen(8, true, true, true, true, true, false));
        bedrooms = new ArrayList<>();
        bedrooms.add(new Bedroom(20, 1, 2, true));
        ap = new Apartment(new Address("Athens", "Str", "24"), 2, 54, true, true, true, bathrooms, bedrooms, kitchens);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getLocation() {
        Assert.assertEquals(new Address("Athens", "Str", "24"), ap.getLocation());
    }

    @Test
    public void setLocation() {
        Address new_addr = new Address("Athens", "THE", "18A");
        ap.setLocation(new_addr);
        Assert.assertEquals(new_addr, ap.getLocation());
    }

    @Test
    public void getFloor() {
        Assert.assertEquals(2, ap.getFloor());
    }

    @Test
    public void setFloor() {
        ap.setFloor(3);
        Assert.assertEquals(3, ap.getFloor());
    }

    @Test
    public void getSize() {
        Assert.assertEquals(54, ap.getSize(), 0.1);
    }

    @Test
    public void setSize() {
        ap.setSize(88);
        Assert.assertEquals(88, ap.getSize(), 0.1);
    }

    @Test
    public void isWifi() {
        assertTrue(ap.isWifi());
    }

    @Test
    public void setWifi() {
        ap.setWifi(false);
        assertFalse(ap.isWifi());
    }

    @Test
    public void isBalcony() {
        assertTrue(ap.isBalcony());
    }

    @Test
    public void setBalcony() {
        ap.setBalcony(false);
        assertFalse(ap.isBalcony());
    }

    @Test
    public void isLiving_room() {
        assertTrue(ap.isLiving_room());
    }

    @Test
    public void setLiving_room() {
        ap.setLiving_room(false);
        assertFalse(ap.isLiving_room());
    }

    @Test
    public void getBathrooms() {
        Assert.assertEquals(bathrooms, ap.getBathrooms());
    }

    @Test
    public void setBathrooms() {
        Bathroom new_bath = new Bathroom(4, false, true, false);
        ArrayList<Bathroom> temp_bathrooms = new ArrayList<>();
        temp_bathrooms.add(new_bath);
        ap.setBathrooms(temp_bathrooms);
        Assert.assertEquals(temp_bathrooms, ap.getBathrooms());

    }

    @Test
    public void getBedrooms() {
        Assert.assertEquals(bedrooms, ap.getBedrooms());
    }

    @Test
    public void setBedrooms() {
        Bedroom new_bed = new Bedroom(10, 1, 0, true);
        ArrayList<Bedroom> temp_bedrooms = new ArrayList<>();
        temp_bedrooms.add(new_bed);
        ap.setBedrooms(temp_bedrooms);
        Assert.assertEquals(temp_bedrooms, ap.getBedrooms());
    }

    @Test
    public void getKitchens() {
        Assert.assertEquals(kitchens, ap.getKitchens());
    }

    @Test
    public void setKitchens() {
        Kitchen new_kitch = new Kitchen(5, false, true, true, false, false, false);
        ArrayList<Kitchen> temp_kitchen = new ArrayList<>();
        temp_kitchen.add(new_kitch);
        ap.setKitchens(temp_kitchen);
        Assert.assertEquals(temp_kitchen, ap.getKitchens());
    }

    @Test
    public void isEqual() {
        Apartment ap2 = new Apartment(new Address("Athens", "Str", "24"), 2, 54, true, true, true, bathrooms, bedrooms, kitchens);
        Assert.assertEquals(ap, ap2);
    }

    @Test
    public void hashcode() {
        int hash = Objects.hash(ap.getLocation(), ap.getFloor());
        Assert.assertEquals(hash, ap.hashCode());
    }
}