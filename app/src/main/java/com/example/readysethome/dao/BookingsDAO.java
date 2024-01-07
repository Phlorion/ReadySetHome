package com.example.readysethome.dao;

import com.example.readysethome.model.Booking;

import java.util.List;

/**
 * Διεπαφή που παρέχει λειτουργίες για τη διαχείριση των κρατήσεων.
 */
public interface BookingsDAO {
    /**
     * Διαγράφει μια κράτηση.
     * @param booking Η κράτηση
     */
    void delete(Booking booking);

    /**
     * Αποθηκεύει μια κράτηση.
     * @param booking Η κράτηση
     */
    void save(Booking booking);

    /**
     * Επιστρέφει όλες τις κρατήσεις.
     * @return Η λίστα των κρατήσεων
     */
    List<Booking> findAll();

    /**
     * Βρίσκει μια κράτηση με βάση το ID της.
     * @param id Το ID της κράτησης
     * @return Η κράτηση
     */
    Booking findByID(int id);
}
