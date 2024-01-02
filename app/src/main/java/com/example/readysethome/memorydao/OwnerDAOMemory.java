package com.example.readysethome.memorydao;

import com.example.readysethome.dao.OwnerDAO;
import com.example.readysethome.model.Owner;
import com.example.readysethome.model.User;

import java.util.ArrayList;
import java.util.List;

public class OwnerDAOMemory implements OwnerDAO {
    protected static ArrayList<Owner> owners = new ArrayList<Owner>();

    @Override
    public void delete(Owner owner) {
        owners.remove(owner);
    }

    @Override
    public void save(Owner owner) {
        owners.add(owner);
    }

    @Override
    public List<Owner> findAll() {
        return owners;
    }

    @Override
    public Owner findByID(String id) {
        for (Owner owner : owners) {
            if (owner.getId().equals(id)) return owner;
        }
        return null;
    }

    @Override
    public Owner findByEmail(String email) {
        for (Owner owner : owners) {
            if (owner.getEmail().getAddress().equals(email)) return owner;
        }
        return null;
    }

    @Override
    public String nextID() {
        String next;
        char[] chars = User.last_owner_ID.toCharArray();
        String temp = "";
        for (int i=1; i<chars.length; i++) {
            temp = temp + chars[i];
        }
        int temp_int = Integer.parseInt(temp);
        temp_int++;
        next = "o" + temp_int;

        return next;
    }
}
