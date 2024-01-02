package com.example.readysethome.view.Tenant.TenantMain;

import com.example.readysethome.R;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.UserDAO;
import com.example.readysethome.memorydao.UserDAOMemory;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Tenant;
import com.example.readysethome.model.User;
import com.example.readysethome.view.Owner.OwnerHomeListingModel;
import com.example.readysethome.view.Tenant.TenantHomeListingModel;

import java.util.ArrayList;

public class TenantMainPresenter {

    private TenantMainView view;
    private ListingDAO listings;
    private Tenant attachedTenant;
    ArrayList<TenantHomeListingModel> homeListingModels;

    /**
     * Αρχικοποιήση μεταβλητών και δημιουργία του listing model μας για το recycler του χρήστη
     * @param view Το view
     * @param listings ΄Ενα listing DAO
     * @param user_id Το id του χρήστη που έχει κάνει login
     */
    TenantMainPresenter(TenantMainView view, ListingDAO listings, String user_id) {
        this.view = view;
        this.listings = listings;

        UserDAO users = new UserDAOMemory();
        User user = users.findByID(user_id);
        attachedTenant = new Tenant(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getCreditCard(), user.getAcc_bday());
        attachedTenant._setId(user_id);

        homeListingModels = new ArrayList<TenantHomeListingModel>();
    }

    /**
     * Βρίσκει όλα listings και μετά παίρνει τα στοιχεία τους
     * (τίτλο, περιγραφή, τιμή κα εικόνες) και δημιουργει τα αντίστοιχα listing models
     * για το recycler
     */
    public ArrayList<TenantHomeListingModel> setUpHomeListingModels() {
        for (Listing listing : listings.findAll()) {
            int preview_photo;
            if (listing.getPhotos() != null)
                preview_photo = listing.getPhotos()[0];
            else
                preview_photo = R.drawable.child_po;
            homeListingModels.add(new TenantHomeListingModel(listing.getTitle(), listing.getDescription(), Double.toString(listing.getPrice()) + "€", listing.getListing_id(), preview_photo));
        }
        return homeListingModels;
    }

}
