package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

public class AddressTest {
    private Address address;
    @Before
    public void setUp() throws Exception {
        address = new Address("Athens", "28h Oktovriou", "24");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCity() {
        Assert.assertEquals("Athens", address.getCity());
    }

    @Test
    public void setCity() {
        address.setCity("Tripolh");
        Assert.assertEquals("Tripolh", address.getCity());
    }

    @Test
    public void getStreet() {
        Assert.assertEquals("28h Oktovriou", address.getStreet());
    }

    @Test
    public void setStreet() {
        address.setStreet("V. Sofias");
        Assert.assertEquals("V. Sofias", address.getStreet());
    }

    @Test
    public void getAddress_num() {
        Assert.assertEquals("24", address.getAddress_num());
    }

    @Test
    public void setAddress_num() {
        address.setAddress_num("28A");
        Assert.assertEquals("28A", address.getAddress_num());
    }

    @Test
    public void isEqual() {
        Address new_addr = new Address("Athens", "28h Oktovriou", "24");
        Address new_addr2 = new Address("Athens", "adiuga", "24");
        Object new_o = new Object();
        Assert.assertNotEquals(address, new_o);
        Assert.assertEquals(address, new_addr);
        Assert.assertNotEquals(address, new_addr2);
    }

    @Test
    public void hashcode() {
        int hash = Objects.hash(address.getCity(), address.getStreet(), address.getAddress_num());
        Assert.assertEquals(hash, address.hashCode());
    }
}