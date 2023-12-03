package com.example.readysethome.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class UserTest {
    private User user;
    private User user2;
    private User user3;
    @Before
    public void setUp() throws Exception {
        user = new User("George", "Avrabos", new EmailAddress("georgeavrb@gmail.com"), new Password("1234"),
                new CreditCard("1600160016001600"), new Date());
        user.setId("Tenant");
        user2 = new User("Mitsos", "Mitsaras", new EmailAddress("mitsaras1234@gmail.com"), new Password("4321"),
                new CreditCard("1234123412341234"), new Date());
        user2.setId("Owner");
        user3 = new User("adgdg", "wigsd");
        user3.setId("Tenant");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getId() {
        char[] u_id_c = user.getId().toCharArray();
        char temp = u_id_c[0];
        Assert.assertEquals("t", String.valueOf(temp));

        char[] u2_id_c = user2.getId().toCharArray();
        char temp2 = u2_id_c[0];
        Assert.assertEquals("o", String.valueOf(temp2));
    }

    @Test
    public void setId() {
        user.setId("Tenant");
        char[] u_id_c = user.getId().toCharArray();
        char temp = u_id_c[0];
        Assert.assertEquals("t", String.valueOf(temp));
    }

    @Test
    public void getAllDetails() {
        String temp = "";
        temp += user.getId() + ": " + user.getFirstName() + " " + user.getLastName() + "\n";
        temp += user.getEmail().getAddress() + "\n";
        temp += user.getPassword().getPassword() + "\n";
        temp += user.getCreditCard().getNumber() + "\n";
        temp += "Account Creation: " + user.getAcc_bday();

        Assert.assertEquals(temp, user.getAllDetails());
    }

    @Test
    public void getFirstName() {
        Assert.assertEquals("George", user.getFirstName());
    }

    @Test
    public void setFirstName() {
        user.setFirstName("Nikolis");
        Assert.assertEquals("Nikolis", user.getFirstName());
    }

    @Test
    public void getLastName() {
        Assert.assertEquals("Avrabos", user.getLastName());
    }

    @Test
    public void setLastName() {
        user.setLastName("Aggelopoulos");
        Assert.assertEquals("Aggelopoulos", user.getLastName());
    }

    @Test
    public void getEmail() {
        Assert.assertEquals("georgeavrb@gmail.com", user.getEmail().getAddress());
    }

    @Test
    public void setEmail() {
        EmailAddress email = new EmailAddress("ahgsjakgd@gmail.com");
        user.setEmail(email);
        Assert.assertEquals("ahgsjakgd@gmail.com", user.getEmail().getAddress());
    }

    @Test
    public void getPassword() {
        Assert.assertEquals("1234", user.getPassword().getPassword());
    }

    @Test
    public void setPassword() {
        Password new_pass = new Password("1655");
        user.setPassword(new_pass);
        Assert.assertEquals("1655", user.getPassword().getPassword());
    }

    @Test
    public void getCreditCard() {
        Assert.assertEquals("1600160016001600", user.getCreditCard().getNumber());
    }

    @Test
    public void setCreditCard() {
        CreditCard new_cc = new CreditCard("1234123454675678");
        user.setCreditCard(new_cc);
        Assert.assertEquals("1234123454675678", user.getCreditCard().getNumber());
    }

    @Test
    public void getAcc_bday() {
        Date date = new Date();
        Assert.assertEquals(date , user.getAcc_bday());
    }

    @Test
    public void setAcc_bday() {
        Date date = new Date();
        user.setAcc_bday(date);
        Assert.assertEquals(date, user.getAcc_bday());
    }
}