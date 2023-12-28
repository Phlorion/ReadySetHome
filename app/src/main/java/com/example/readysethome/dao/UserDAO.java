package com.example.readysethome.dao;

import com.example.readysethome.model.User;

import java.util.List;

public interface UserDAO {

    /**
     * Διαγράφει έναν χρήστη.
     * @param user Ο χρήστης
     */
    void delete(User user);

    /**
     * Προσθέτει έναν χρήστη.
     * @param user Ο χρήστης
     */
    void save(User user);

    /**
     * Επιστρέφει όλους τους χρήστες.
     * @return Τους χρήστες
     */
    List<User> findAll();

    /**
     * Βρίσκει έναν χρήστη με βάση το id του.
     * @param id Το id του χρήστη
     * @return Τον χρήστη
     */
    User findByID(String id);

    /**
     * Βρίσκει έναν χρήστη με βάση το email του.
     * @param email Το email του χρήστη
     * @return Τον χρήστη
     */
    User findByEmail(String email);

    /**
     * Επιστρέφει το επόμενο id που μπορεί να αποδοθεί σε έναν χρήστη.
     * @param type Ο τύπος του λογαριασμού (ενοικιαστή ή ιδιοκτήτη)
     * @return Το επόμενο id
     */
    String nextID(String type);
}
