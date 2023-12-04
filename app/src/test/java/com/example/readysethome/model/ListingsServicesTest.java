package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListingsServicesTest {
    final ListingsServices lServices = new ListingsServices(100.0f, ListingsServicesType.CLEANING_SERVICE);

    @Test
    public void getPrice() {
        assertEquals(100.0f, lServices.getPrice(), 0.01f);
    }

    @Test
    public void setPrice() {
        lServices.setPrice(50.0f);
        assertEquals(50.0f, lServices.getPrice(), 0.01f);
    }

    @Test
    public void getType() {
        assertEquals(ListingsServicesType.CLEANING_SERVICE, lServices.getType());
    }

    @Test
    public void setType() {
        lServices.setType(ListingsServicesType.LINEN_FEES);
        assertEquals(ListingsServicesType.LINEN_FEES, lServices.getType());
    }
}