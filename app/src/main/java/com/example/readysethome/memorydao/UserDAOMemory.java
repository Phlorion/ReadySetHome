package com.example.readysethome.memorydao;

import com.example.readysethome.dao.UserDAO;
import com.example.readysethome.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOMemory implements UserDAO {
    protected static ArrayList<User> users = new ArrayList<User>();

    /**
     * Διαγράφει έναν χρήστη.
     * @param user Ο χρήστης
     */
    public void delete(User user) {
        users.remove(user);
    }

    /**
     * Προσθέτει έναν χρήστη.
     * @param user Ο χρήστης
     */
    public void save(User user) {
        users.add(user);
    }

    /**
     * Επιστρέφει όλους τους χρήστες.
     * @return Τους χρήστες
     */
    public List<User> findAll() {
        return users;
    }

    /**
     * Βρίσκει έναν χρήστη με βάση το id του.
     * @param id Το id του χρήστη
     * @return Τον χρήστη
     */
    public User findByID(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) return user;
        }
        return null;
    }

    /**
     * Βρίσκει έναν χρήστη με βάση το email του.
     * @param email Το email του χρήστη
     * @return Τον χρήστη
     */
    public User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().getAddress().equals(email)) return user;
        }
        return null;
    }

    /**
     * Επιστρέφει το επόμενο id που μπορεί να αποδοθεί σε έναν χρήστη.
     * @param type Ο τύπος του λογαριασμού (ενοικιαστή ή ιδιοκτήτη)
     * @return Το επόμενο id
     */
    public String nextID(String type) {
        String next;
        if (type.equals("Tenant")) {
            char[] chars = User.last_tenant_ID.toCharArray();
            String temp = "";
            for (int i=1; i<chars.length; i++) {
                temp = temp + chars[i];
            }
            int temp_int = Integer.parseInt(temp);
            temp_int++;
            next = "t" + temp_int;
        }
        else {
            char[] chars = User.last_owner_ID.toCharArray();
            String temp = "";
            for (int i=1; i<chars.length; i++) {
                temp = temp + chars[i];
            }
            int temp_int = Integer.parseInt(temp);
            temp_int++;
            next = "o" + temp_int;
        }
        return next;
    }
}
