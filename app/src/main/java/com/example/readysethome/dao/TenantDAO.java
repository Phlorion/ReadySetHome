package com.example.readysethome.dao;

import com.example.readysethome.model.Booking;
import com.example.readysethome.model.BookingRequest;
import com.example.readysethome.model.Owner;
import com.example.readysethome.model.Tenant;

import java.util.List;

public interface TenantDAO {
    /**
     * Διαγράφει έναν ενοικιαστή.
     *
     * @param tenant Ο ενοικιαστή
     */
    void delete(Tenant tenant);

    /**
     * Προσθέτει έναν ενοικιαστή.
     *
     * @param tenant Ο ενοικιαστή
     */
    void save(Tenant tenant);

    /**
     * Επιστρέφει όλους τους ενοικιαστή.
     *
     * @return Τους ενοικιαστες
     */
    List<Tenant> findAll();

    /**
     * Βρίσκει έναν ενοικιαστή με βάση το id του.
     *
     * @param id Το id του ενοικιαστή
     * @return Τον ενοικιαστή
     */
    Tenant findByID(String id);

    /**
     * Βρίσκει έναν ενοικιαστή με βάση το email του.
     *
     * @param email Το email του ενοικιαστή
     * @return Τον ενοικιαστή
     */
    Tenant findByEmail(String email);

    /**
     * Επιστρέφει το επόμενο id που μπορεί να αποδοθεί σε έναν ενοικιαστή.
     *
     * @return Το επόμενο id
     */
    String nextID();
}
