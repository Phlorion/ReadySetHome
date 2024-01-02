package com.example.readysethome.memorydao;

import com.example.readysethome.dao.Initializer;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.OwnerDAO;
import com.example.readysethome.dao.TenantDAO;
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

    @Override
    public OwnerDAO getOwnerDAO() {
        return new OwnerDAOMemory();
    }

    @Override
    public TenantDAO getTenantDAO() {
        return new TenantDAOMemory();
    }

    @Override
    public ListingDAO getListingDAO() {
        return new ListingDAOMemory();
    }
}
