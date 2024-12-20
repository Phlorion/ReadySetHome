package com.example.readysethome.view.Owner.OwnerMain;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readysethome.R;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.OwnerDAO;
import com.example.readysethome.dao.UserDAO;
import com.example.readysethome.memorydao.UserDAOMemory;
import com.example.readysethome.model.BookingRequest;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Owner;
import com.example.readysethome.model.ReservationStatus;
import com.example.readysethome.model.User;
import com.example.readysethome.view.Owner.OwnerHomeListingModel;
import com.example.readysethome.view.Owner.OwnerPendingModel;
import com.example.readysethome.view.Owner.OwnerProfileFragment;

import java.util.ArrayList;

public class OwnerMainPresenter {
    private OwnerMainView view;
    private ListingDAO listings;
    private OwnerDAO owners;
    private Owner attachedOwner;
    ArrayList<OwnerHomeListingModel> listingModels;
    ArrayList<OwnerPendingModel> pendingModels;

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
        listingModels = new ArrayList<>();
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

    public ArrayList<OwnerPendingModel> setUpPendingModels() {
        pendingModels = new ArrayList<>();
        ArrayList<BookingRequest> brs = attachedOwner.getBookingRequests();
        for (BookingRequest br : brs) {
            if (!br.getBooking_status().equals(ReservationStatus.CANCELLED_BY_OWNER)) {
                int preview_photo;
                if (br.getListing().getPhotos() != null)
                    preview_photo = br.getListing().getPhotos()[0];
                else
                    preview_photo = R.drawable.child_po;
                pendingModels.add(new OwnerPendingModel(br.getTenant().getFirstName() + " " + br.getTenant().getLastName(), br.getListing().getTitle(), preview_photo, br.getBooking_id(), br.getCheck_in(), br.getCheck_out()));
            }
        }
        return pendingModels;
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

    public void pendingConfirm(int pos) {
        for (BookingRequest br : attachedOwner.getBookingRequests()) {
            if (br.getBooking_id() == pendingModels.get(pos).getBrId()) {
                attachedOwner.confirmBookingRequest(br);
                break;
            }
        }
    }

    public void pendingDecline(int pos) {
        for (BookingRequest br : attachedOwner.getBookingRequests()) {
            if (br.getBooking_id() == pendingModels.get(pos).getBrId()) {
                attachedOwner.declineBookingRequest(br);
                break;
            }
        }
    }
}
