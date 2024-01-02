package com.example.readysethome.dao;

import com.example.readysethome.model.Owner;
import com.example.readysethome.model.User;

import java.util.List;

public interface OwnerDAO {

    /**
     * Διαγράφει έναν ιδιοκτήτη.
     * @param owner Ο ιδιοκτήτης
     */
    void delete(Owner owner);

    /**
     * Προσθέτει έναν ιδιοκτήτη.
     * @param owner Ο ιδιοκτήτη
     */
    void save(Owner owner);

    /**
     * Επιστρέφει όλους τους ιδιοκτήτη.
     * @return Τους ιδιοκτήτη
     */
    List<Owner> findAll();

    /**
     * Βρίσκει έναν ιδιοκτήτη με βάση το id του.
     * @param id Το id του ιδιοκτήτη
     * @return Τον ιδιοκτήτη
     */
    Owner findByID(String id);

    /**
     * Βρίσκει έναν ιδιοκτήτη με βάση το email του.
     * @param email Το email του ιδιοκτήτη
     * @return Τον ιδιοκτήτη
     */
    Owner findByEmail(String email);

    /**
     * Επιστρέφει το επόμενο id που μπορεί να αποδοθεί σε έναν owner.
     * @return Το επόμενο id
     */
    String nextID();

}
