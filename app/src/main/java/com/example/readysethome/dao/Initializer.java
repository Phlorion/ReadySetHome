package com.example.readysethome.dao;

import com.example.readysethome.model.CreditCard;
import com.example.readysethome.model.EmailAddress;
import com.example.readysethome.model.Password;
import com.example.readysethome.model.User;

import java.util.Date;

public abstract class Initializer {
    /**
     * Διαγράφει τα αποθηκευμένα δεδομένα.
     */
    protected abstract void eraseData();

    /**
     * Επιστρέφει το DAO των users.
     * @return Το DAO των users
     */
    public abstract UserDAO getUserDAO();

    /**
     * Εισάγει τα δοκιμαστικά δεδομένα.
     */
    public void prepareData() {
        // πριν εισάγουμε τα δεδομένα διαγράφουμε ότι υπάρχει
        eraseData();

        UserDAO userDAO = getUserDAO();

        User user1 = new User("George", "Avrabos", new EmailAddress("p3210001@aueb.gr"), new Password("1234"), new CreditCard("1234123412341234"), new Date());
        user1.setId("Tenant");
        userDAO.save(user1);
        User user2 = new User("Anestis", "Thanasi", new EmailAddress("p3210273@aueb.gr"), new Password("9090"), new CreditCard("0987098709870987"), new Date());
        user1.setId("Tenant");
        userDAO.save(user2);
        User user3 = new User("Triantafyllos", "Kiosse", new EmailAddress("p3210079@aueb.gr"), new Password("4545"), new CreditCard("467467467467"), new Date());
        user1.setId("Owner");
        userDAO.save(user3);
    }
}
