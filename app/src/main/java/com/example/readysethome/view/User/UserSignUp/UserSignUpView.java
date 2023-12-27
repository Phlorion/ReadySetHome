package com.example.readysethome.view.User.UserSignUp;

import com.example.readysethome.model.CreditCard;
import com.example.readysethome.model.EmailAddress;
import com.example.readysethome.model.Password;

import java.util.Date;

public interface UserSignUpView {

    String getFirstName();
    String getLastName();
    EmailAddress getEmail();
    Password getPass();
    CreditCard getCreditCard();
    Date getAccBirthday();
    String getAttachedID();

    /**
     * Θέτει το πρώτο όνομα του user.
     * @param value Το πρώτο όνομα του user
     */
    void setFirstName(String value);

    /**
     * Θέτει το επώνυμο του user.
     * @param value Το επώνυμο του user
     */
    void setLastName(String value);

    /**
     * Θέτει το email του user.
     * @param value Το email (σε string) του user
     */
    void setEmail(String value);

    /**
     * Θέτει το password του user.
     * @param value Το password (σε string) του user
     */
    void setPass(String value);

    /**
     * Θέτει το credit card του user.
     * @param value Το credit card (σε string) του user
     */
    void setCreditCard(String value);


}
