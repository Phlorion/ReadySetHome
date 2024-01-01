package com.example.readysethome.dao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Owner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public interface ListingDAO {
    /**
     * Διαγράφει μία αγγελία.
     * @param listing Η αγγελία
     */
    void delete(Listing listing);

    /**
     * Προσθέτει μία αγγελία.
     * @param listing Η αγγελία
     */
    void save(Listing listing);

    /**
     * Επιστρέφει όλες τις αγγελίες.
     * @return Τις αγγελίες
     */
    List<Listing> findAll();

    /**
     * Βρίσκει μία αγγελία με βάση το id της.
     * @param id Το id της αγγελίας
     * @return Την αγγελία
     */
    Listing findByID(int id);

    /**
     * Βρίσκει αγγελίς με βάση τον ιδιοκτήτη τους.
     * @param owner_id Τον ιδιοκτήτη των αγγελιών
     * @return Τις αγγελίες
     */
    ArrayList<Listing> findByOwner(String owner_id);
}
