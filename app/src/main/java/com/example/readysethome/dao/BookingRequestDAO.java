package com.example.readysethome.dao;

import com.example.readysethome.model.BookingRequest;

import java.util.List;

/**
 * Η διεπαφή που παρέχει λειτουργίες για τα αιτήματα κρατήσεων.
 */
public interface BookingRequestDAO {
    /**
     * Διαγράφει ένα αίτημα κράτησης.
     * @param bookingRequest Το αίτημα κράτηση
     */
    void delete(BookingRequest bookingRequest);

    /**
     * Προσθέτει ένα αίτημα κράτησης.
     * @param bookingRequest Το αίτημα κράτησης
     */
    void save(BookingRequest bookingRequest);

    /**
     * Επιστρέφει όλα τα αιτήματα κρατήσεων.
     * @return Τα αιτήματα κρατήσεων
     */
    List<BookingRequest> findAll();

    /**
     * Βρίσκει ένα αίτημα κράτησης με βάση το id του.
     * @param id Το id του αιτήματος κράτησης
     * @return Το αίτημα κράτησης
     */
    BookingRequest findByID(int id);


}
