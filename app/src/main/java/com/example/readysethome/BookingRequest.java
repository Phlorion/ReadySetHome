package com.example.readysethome;

import com.example.readysethome.ReservationStatus;

import java.util.Date;

class BookingRequest {
     private static int lastBookingId = 0;
     private int booking_id;
    private Date submission_date;
    private Date check_in;
    private Date check_out;
    private ReservationStatus booking_status;
    private Listing listing;
    private Tenant tenant;

    public BookingRequest(Listing listing, Date submission_date, Date check_in, Date check_out, ReservationStatus booking_status, Tenant tenant) {
        lastBookingId++;
        this.booking_id = lastBookingId;
        this.listing = listing;
        this.submission_date = submission_date;
        this.check_in = check_in;
        this.check_out = check_out;
        this.booking_status = booking_status;
        this.tenant = tenant;
    }

    //Methodos gia thn ypovolh aithmatos
    public void submitReservationRequest() {}

    //Methodos gia akyrwsh
    public void cancelReservationRequest() {
    }

    // Methodos gia enhmerwsh tou status tou aithmatos krathshs
    public void updateStatus(ReservationStatus newStatus) {
    }

    public void returnDepositToRenter() {
    }


    // Methodos gia thn enhmerwsh tou idiokthth se periptwsh aithmatos krathshs
    public void notifyOwner() {}

    // Setters kai getters
    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public Date getSubmission_date() {
        return submission_date;
    }

    public void setSubmission_date(Date submission_date) {
        this.submission_date = submission_date;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public Date getCheck_out() {
        return check_out;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }

    public ReservationStatus getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(ReservationStatus booking_status) {
        this.booking_status = booking_status;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}