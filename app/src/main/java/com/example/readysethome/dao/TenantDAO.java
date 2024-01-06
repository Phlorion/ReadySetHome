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

    /**
     * Επιστρέφει όλες τις κρατήσεις και τα αιτήματα κρατήσεων για έναν ενοικιαστή.
     *
     * @param tenant Ο ενοικιαστής για τον οποίο θέλουμε να επιστραφούν κρατήσεις και αιτήματα
     * @return Λίστα που περιέχει τόσο κρατήσεις όσο και αιτήματα κρατήσεων
     */
    List<Object> getAllBookingsAndRequests(Tenant tenant);


    /**
     * Επιστρέφει όλες τις κρατήσεις ενός ενοικιαστή.
     *
     * @param tenant Ο ενοικιαστής
     * @return Η λίστα των κρατήσεων
     */
    List<Booking> findBookingsByTenant(Tenant tenant);

    /**
     * Επιστρέφει όλα τα αιτήματα κράτησης ενός ενοικιαστή.
     *
     * @param tenant Ο ενοικιαστής
     * @return Η λίστα των αιτημάτων κράτησης
     */
    List<BookingRequest> findBookingRequestsByTenant(Tenant tenant);
}
