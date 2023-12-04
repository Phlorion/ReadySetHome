package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class ChargingPolicyTest {

    final Date customDate = Date.from(Instant.parse("2024-01-01T00:00:00.000Z"));
    final Date customDate2 = Date.from(Instant.parse("2024-01-05T00:00:00.000Z"));
    final ChargingPolicy chargingPolicy = new ChargingPolicy(customDate, customDate2, "Something", 50);

    @Test
    public void getStart_index() {
        assertEquals(customDate, chargingPolicy.getStart_index());
    }

    @Test
    public void setStart_index() {
        final Date customDate = Date.from(Instant.parse("2023-01-01T00:00:00.000Z"));
        chargingPolicy.setStart_index(customDate);
        assertEquals(customDate, chargingPolicy.getStart_index());
    }

    @Test
    public void getEnd_index() {
        assertEquals(customDate2, chargingPolicy.getEnd_index());
    }

    @Test
    public void setEnd_index() {
        final Date customDate2 = Date.from(Instant.parse("2023-01-05T00:00:00.000Z"));
        chargingPolicy.setEnd_index(customDate);
        assertEquals(customDate, chargingPolicy.getStart_index());
    }

    @Test
    public void getDescription() {
        assertEquals("Something", chargingPolicy.getDescription());
    }

    @Test
    public void setDescription() {
        String description = "Description";
        chargingPolicy.setDescription(description);
        assertEquals(description, chargingPolicy.getDescription());
    }

    @Test
    public void getPrice_diff() {
        assertEquals(50, chargingPolicy.getPrice_diff(), 0.01f);
    }

    @Test
    public void setPrice_diff() {
        double priceDiff = 105.0f;
        chargingPolicy.setPrice_diff(priceDiff);
        assertEquals(priceDiff, chargingPolicy.getPrice_diff(), 0.01f);
    }
}