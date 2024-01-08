package com.example.readysethome.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Owner extends User {
    ArrayList<BookingRequest> pendingBookingRequests;

    public Owner(String firstName, String lastName, EmailAddress email, Password password, CreditCard creditCard, Date acc_bday) {
        super(firstName, lastName, email, password, creditCard, acc_bday);
        pendingBookingRequests = new ArrayList<>();
    }

    // add booking request to pendingBookingRequests
    public void addToPending(BookingRequest booking_request) {
        pendingBookingRequests.add(booking_request);
    }

    // remove booking request from pendingBookingRequests
    public void removeFromPending(BookingRequest booking_request) {
        pendingBookingRequests.remove(booking_request);
    }

    // owner confirms a booking request
    public boolean confirmBookingRequest(BookingRequest booking_request) {
        boolean state = true;
        // if something goes wrong during confirmation
        if(!booking_request.confirm()) {
            declineBookingRequest(booking_request);
            state = false;
        }
        removeFromPending(booking_request);
        return state;
    }

    // owner declines a booking request
    public void declineBookingRequest(BookingRequest booking_request) {
        booking_request.declineRequest();
        removeFromPending(booking_request);
    }

    public void cancelBooking(Booking booking) {
        booking.cancel(ReservationStatus.CANCELLED_BY_OWNER);
    }

    public double checkOccupancy(Listing listing, java.util.Calendar calendar) {
        return listing.calculateOccupancy(calendar);
    }

    // Owner add a new Listing with services for an apartment
    public Listing addListing(Apartment ap, String title, String desc, double price, boolean promoted, int[] photos, ListingsServices[] listingsServices) {
        Listing listing =  new Listing(title, desc, price, promoted, 0, photos, new Calendar(), this, ap);
        if (listingsServices != null) {
            for (ListingsServices service : listingsServices) {
                listing.addService(service);
            }
        }
        return listing;
    }

    public Listing updateListing(Listing listing, String title, String desc, double price, boolean promoted, int[] photos, ListingsServices[] listingsServices) {
        listing.setTitle(title);
        listing.setDescription(desc);
        listing.setPrice(price);
        listing.setPromoted(promoted);
        listing.setPhotos(photos);
        for (ListingsServices listingsService : listingsServices) {
            listing.addService(listingsService);
        }
        return listing;
    }

    public void removeListing(Listing listing, List<Listing> listings, ArrayList<Booking> bookings) {
        // check if there are bookings for the listing and if there are cancel them
        for (Iterator<Booking> iterator = bookings.iterator(); iterator.hasNext();) {
            Booking booking = iterator.next();
            if (booking.getListing().getListing_id() == listing.getListing_id()) {
                this.cancelBooking(booking);
                iterator.remove();
            }
        }
        for (BookingRequest b_r : pendingBookingRequests) {
            if (b_r.getListing().getListing_id() == listing.getListing_id()) {
                b_r.setBooking_status(ReservationStatus.CANCELLED_BY_OWNER);
            }
        }
        // remove listing from datastore
        listings.remove(listing);
    }


    public ArrayList<BookingRequest> getBookingRequests() {
        return pendingBookingRequests;
    }

    public void setBookingRequests(ArrayList<BookingRequest> bookingRequests) {
        this.pendingBookingRequests = bookingRequests;
    }
}
