package com.example.readysethome.memorydao;

import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Owner;

import java.util.ArrayList;
import java.util.List;

public class ListingDAOMemory implements ListingDAO {
    protected static ArrayList<Listing> listings = new ArrayList<Listing>();
    /**
     * Διαγράφει μία αγγελία.
     * @param listing Η αγγελία
     */
    public void delete(Listing listing) {
        listings.remove(listing);
    }

    /**
     * Προσθέτει μία αγγελία.
     * @param listing Η αγγελία
     */
    public void save(Listing listing) {
        listings.add(listing);
    }

    /**
     * Επιστρέφει όλες τις αγγελίες.
     * @return Τις αγγελίες
     */
    public List<Listing> findAll() {
        return listings;
    }

    /**
     * Βρίσκει μία αγγελία με βάση το id της.
     * @param id Το id της αγγελίας
     * @return Την αγγελία
     */
    public Listing findByID(int id) {
        for (Listing listing : listings) {
            if (listing.getListing_id() == id) return listing;
        }
        return null;
    }

    /**
     * Βρίσκει αγγελίς με βάση τον ιδιοκτήτη τους.
     * @param owner Τον ιδιοκτήτη των αγγελιών
     * @return Τις αγγελίες
     */
    public ArrayList<Listing> findByOwner(Owner owner) {
        ArrayList<Listing> temp = new ArrayList<>();
        for (Listing listing : listings) {
            if (listing.getOwner().equals(owner)) temp.add(listing);
        }
        return temp;
    }
}
