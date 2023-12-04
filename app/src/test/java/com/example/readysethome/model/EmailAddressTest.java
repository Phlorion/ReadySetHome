package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmailAddressTest {
    private EmailAddress e;
    private EmailAddress e2;
    @Before
    public void setUp() throws Exception {
        e = new EmailAddress("georgeavrb@gmail.com");
        e2 = new EmailAddress();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAddress() {
        Assert.assertEquals("georgeavrb@gmail.com", e.getAddress());
    }

    @Test
    public void setAddress() {
        e.setAddress("barbamixalhs@yahoo.com");
        Assert.assertEquals("barbamixalhs@yahoo.com", e.getAddress());
    }

    @Test
    public void send() {
        Assert.assertEquals(0, e.send(e2, "Hello", "BarbaMixalhsOfficial"));
    }
}