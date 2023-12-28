package com.example.readysethome.memorydao;

import com.example.readysethome.dao.Initializer;
import com.example.readysethome.dao.UserDAO;
import com.example.readysethome.model.User;

public class MemoryInitializer extends Initializer {

    @Override
    protected void eraseData() {
        for (User user : getUserDAO().findAll()) {
            getUserDAO().delete(user);
        }
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOMemory();
    }
}
