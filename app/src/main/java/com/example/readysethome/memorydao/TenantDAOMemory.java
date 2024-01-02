package com.example.readysethome.memorydao;

import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.model.Owner;
import com.example.readysethome.model.Tenant;
import com.example.readysethome.model.User;

import java.util.ArrayList;
import java.util.List;

public class TenantDAOMemory implements TenantDAO {
    protected static ArrayList<Tenant> tenants = new ArrayList<Tenant>();

    @Override
    public void delete(Tenant tenant) {
        tenants.remove(tenant);
    }

    @Override
    public void save(Tenant tenant) {
        tenants.add(tenant);
    }

    @Override
    public List<Tenant> findAll() {
        return tenants;
    }

    @Override
    public Tenant findByID(String id) {
        for (Tenant tenant : tenants) {
            if (tenant.getId().equals(id)) return tenant;
        }
        return null;
    }

    @Override
    public Tenant findByEmail(String email) {
        for (Tenant tenant : tenants) {
            if (tenant.getEmail().getAddress().equals(email)) return tenant;
        }
        return null;
    }

    @Override
    public String nextID() {
        String next;
        char[] chars = User.last_tenant_ID.toCharArray();
        String temp = "";
        for (int i=1; i<chars.length; i++) {
            temp = temp + chars[i];
        }
        int temp_int = Integer.parseInt(temp);
        temp_int++;
        next = "t" + temp_int;

        return next;
    }
}
