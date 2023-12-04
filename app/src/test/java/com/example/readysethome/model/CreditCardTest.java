package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreditCardTest {
    private CreditCard c;
    private CreditCard c1;
    private CreditCard c2;
    @Before
    public void setUp() throws Exception {
        c = new CreditCard("1200230024003400", 1050.70);
        c1 = new CreditCard("1010202030304040");
        c2 = new CreditCard();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNumber() {
        Assert.assertEquals("1200230024003400", c.getNumber());
    }

    @Test
    public void setNumber() {
        c.setNumber("0000111122223333");
        Assert.assertEquals("0000111122223333", c.getNumber());
    }

    @Test
    public void getBalance() {
        Assert.assertEquals(1050.70, c.getBalance(), 0.001);
    }

    @Test
    public void setBalance() {
        c.setBalance(50);
        Assert.assertEquals(50, c.getBalance(), 0.001);
    }

    @Test
    public void makePayment() {
        boolean ans = c.makePayment(50.5);
        Assert.assertEquals(1000.20, c.getBalance(), 0.001);

        ans = c.makePayment(2000);
        assertFalse(ans);
    }

    @Test
    public void makeNegativePayment() {
        boolean ans = c.makePayment(-20);
        assertFalse(ans);
    }

    @Test
    public void payAmountGreaterThanBalance() {
        boolean ans = c.makePayment(2500);
        assertFalse(ans);
    }

    @Test
    public void refund() {
        c.makePayment(50.5);
        c.refund(20);
        Assert.assertEquals(1020.20, c.getBalance(), 0.001);
    }

    @Test
    public void refundNegativeAmount() {
        c.makePayment(100);
        boolean ans = c.refund(-100);
        assertFalse(ans);
    }
}