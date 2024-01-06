package com.example.readysethome.view.Tenant.TenantMain;

import com.example.readysethome.R;
import com.example.readysethome.dao.BookingRequestDAO;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.dao.UserDAO;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.memorydao.UserDAOMemory;
import com.example.readysethome.model.Booking;
import com.example.readysethome.model.BookingRequest;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Tenant;
import com.example.readysethome.model.User;
import com.example.readysethome.view.Owner.OwnerHomeListingModel;
import com.example.readysethome.view.Tenant.TenantBookingModel;
import com.example.readysethome.view.Tenant.TenantBookingsAdapter;
import com.example.readysethome.view.Tenant.TenantHomeListingModel;

import java.util.ArrayList;

public class TenantMainPresenter {

    private TenantMainView view;
    private ListingDAO listings;
    private TenantDAO tenants;
    private Tenant attachedTenant;
    ArrayList<TenantHomeListingModel> homeListingModels;
    ArrayList<TenantBookingModel> bookingModels;



    /**
     * Αρχικοποιήση μεταβλητών και δημιουργία του listing model μας για το recycler του χρήστη
     * @param view Το view
     * @param listings ΄Ενα listing DAO
     * @param tenants ΄Ενα tenant DAO
     * @param user_id Το id του χρήστη που έχει κάνει login
     */
    TenantMainPresenter(TenantMainView view, ListingDAO listings, TenantDAO tenants, String user_id) {
        this.view = view;
        this.listings = listings;
        this.tenants = tenants;

        attachedTenant = tenants.findByID(user_id);

        homeListingModels = new ArrayList<TenantHomeListingModel>();
        bookingModels = new ArrayList<TenantBookingModel>();
    }

    /**
     * Βρίσκει όλα listings και μετά παίρνει τα στοιχεία τους
     * (τίτλο, περιγραφή, τιμή κα εικόνες) και δημιουργει τα αντίστοιχα listing models
     * για το recycler
     */
    public ArrayList<TenantHomeListingModel> setUpHomeListingModels() {
        homeListingModels = new ArrayList<>();
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

    /**
     * Δημιουργεί μια λίστα με τα αντικείμενα TenantBookingModel για το recycler view
     * με τις κρατήσεις/Αιτήματα κράτησης του ενοικιαστή.
     *
     * @return Η λίστα με τα αντικείμενα TenantBookingModel
     */
    public ArrayList<TenantBookingModel> setUpBookingModels() {
        bookingModels = new ArrayList<>();

        // Add bookings
        ArrayList<Booking> bookings = attachedTenant.getBookings();
        for (Booking booking : bookings) {
            Listing listing = booking.getListing();

            int preview_photo;
            if (listing.getPhotos() != null) {
                preview_photo = listing.getPhotos()[0];
            } else {
                preview_photo = R.drawable.child_po;
            }

            bookingModels.add(new TenantBookingModel(
                    listing.getTitle(),
                    booking.getCheckIn().toString(),
                    booking.getBooking_status().toString(),
                    booking.getId(),
                    preview_photo
            ));
        }

        // Add booking requests
        ArrayList<BookingRequest> bookingRequests = attachedTenant.getBookingRequests();
        for (BookingRequest bookingRequest : bookingRequests) {
            Listing listing = bookingRequest.getListing();

            int preview_photo;
            if (listing.getPhotos() != null) {
                preview_photo = listing.getPhotos()[0];
            } else {
                preview_photo = R.drawable.child_po;
            }

            bookingModels.add(new TenantBookingModel(
                    listing.getTitle(),
                    bookingRequest.getCheck_in().toString(),
                    bookingRequest.getBooking_status().toString(),
                    bookingRequest.getBooking_id(),
                    preview_photo
            ));
        }

        return bookingModels;
    }

    public ArrayList<TenantBookingModel> addBookingModel(BookingRequest bookingRequest) {
        Listing listing = bookingRequest.getListing();
        int preview_photo;
        if (listing.getPhotos() != null) {
            preview_photo = listing.getPhotos()[0];
        } else {
            preview_photo = R.drawable.child_po;
        }

        bookingModels.add(new TenantBookingModel(
                listing.getTitle(),
                bookingRequest.getCheck_in().toString(),
                bookingRequest.getBooking_status().toString(),
                bookingRequest.getBooking_id(),
                preview_photo
        ));

        return bookingModels;
    }

    /**
     * Επιστρέφει τον συνδεδεμένο ενοικιαστή.
     * @return Ο ενοικιαστής που είναι συνδεδεμένος
     */
    public Tenant getAttachedTenant() {
        return attachedTenant;
    }

    public ArrayList<TenantBookingModel> getBookingModels() {
        return bookingModels;
    }
}
