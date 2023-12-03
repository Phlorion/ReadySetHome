package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PasswordTest {
    private Password p;
    private Password p2;
    @Before
    public void setUp() throws Exception {
        p = new Password("1234");
        p2 = new Password();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPassword() {
        Assert.assertEquals("1234", p.getPassword());
    }

    @Test
    public void setPassword() {
        p.setPassword("1000");
        Assert.assertEquals("1000", p.getPassword());
    }
}