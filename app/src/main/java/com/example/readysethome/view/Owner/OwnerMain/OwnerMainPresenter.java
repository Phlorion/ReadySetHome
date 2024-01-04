package com.example.readysethome.view.Owner.OwnerMain;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readysethome.R;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.OwnerDAO;
import com.example.readysethome.dao.UserDAO;
import com.example.readysethome.memorydao.UserDAOMemory;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Owner;
import com.example.readysethome.model.User;
import com.example.readysethome.view.Owner.OwnerHomeListingModel;
import com.example.readysethome.view.Owner.OwnerProfileFragment;

import java.util.ArrayList;

public class OwnerMainPresenter {
    private OwnerMainView view;
    private ListingDAO listings;
    private OwnerDAO owners;
    private Owner attachedOwner;
    ArrayList<OwnerHomeListingModel> listingModels;

    /**
     * Αρχικοποιήση μεταβλητών και δημιουργία του listing model μας για το recycler του χρήστη
     * @param view Το view
     * @param listings ΄Ενα listing DAO
     * @param owners ΄Ενα owner DAO
     * @param user_id Το id του χρήστη που έχει κάνει login
     */
    public OwnerMainPresenter(OwnerMainView view, ListingDAO listings, OwnerDAO owners, String user_id) {
        this.view = view;
        this.listings = listings;
        this.owners = owners;

        attachedOwner = owners.findByID(user_id);
        //System.out.println("----------------"+attachedOwner+"----------------");

        listingModels = new ArrayList<OwnerHomeListingModel>();
    }

    /**
     * Βρίσκει τα listings που ανήκουν στον χρήστη και μετά παίρνει τα στοιχεία τους
     * (τίτλο, περιγραφή, τιμή κα εικόνες) και δημιουργει τα αντίστοιχα listing models
     * για το recycler
     */
    public ArrayList<OwnerHomeListingModel> setUpListingModels() {
        ArrayList<Listing> owned = listings.findByOwner(attachedOwner);
        for (Listing listing : owned) {
            int preview_photo;
            if (listing.getPhotos() != null)
                preview_photo = listing.getPhotos()[0];
            else
                preview_photo = R.drawable.child_po;
            listingModels.add(new OwnerHomeListingModel(listing.getTitle(), listing.getDescription(), Double.toString(listing.getPrice()) + "€", preview_photo, listing.getListing_id()));
        }
        return listingModels;
    }

    public void setUpProfileInfo(View view) {
        ((TextView) view.findViewById(R.id.owner_profile_nameView)).setText(attachedOwner.getFirstName());
        ((TextView) view.findViewById(R.id.owner_profile_surnameView)).setText(attachedOwner.getLastName());
        ((TextView) view.findViewById(R.id.owner_profile_emailView)).setText(attachedOwner.getEmail().getAddress());
        ((ImageView)view.findViewById(R.id.owner_profile_profilePicture)).setImageResource(R.drawable.child_po);
    }
    public ArrayList<OwnerHomeListingModel> addListingModel(Listing listing) {
        int preview_photo;
        if (listing.getPhotos() != null)
            preview_photo = listing.getPhotos()[0];
        else
            preview_photo = R.drawable.child_po;

        listingModels.add(new OwnerHomeListingModel(listing.getTitle(), listing.getDescription(), Double.toString(listing.getPrice()) + "€", preview_photo, listing.getListing_id()));
        return listingModels;
    }

    public Owner getAttachedOwner() {
        return attachedOwner;
    }
}
