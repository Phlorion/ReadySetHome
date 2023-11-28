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

    public BookingRequest(Listing listing, Date submission_date, Date check_in, Date check_out, Tenant tenant) {
        lastBookingId++;
        this.booking_id = lastBookingId;
        this.listing = listing;
        this.submission_date = submission_date;
        this.check_in = check_in;
        this.check_out = check_out;
        this.tenant = tenant;
    }

    //Methodos gia thn ypovolh aithmatos
    public void submit() {
        this.booking_status = ReservationStatus.PENDING;
        notifyOwner(this.listing, "Booking Request", "Booking request from " + this.tenant + " for " + this.listing+ " for " + this.check_in + "-" + this.check_out);
    }

    // Methodos gia confirmation aithmatos
    public void confirm() {
        this.booking_status = ReservationStatus.CONFIRMED;
        // upfront payment
        int days_of_stay = daysBetween(this.check_in, this.check_out);
        double upfront = days_of_stay * 0.2;
        this.tenant.getCreditCard().makePayment(upfront);
        // notify tenant
        notifyTenant(this, "Booking Request Confirmed", "Your booking request " + this + " has been confirmed. An amount of " + upfront
        + " has been removed from your credit card");
        // update apartment availability
        this.listing.getCalendar().setUnavailable(this.check_in, this.check_out);
    }

    // Methodos gia decline aithmatos krathshs apo ton idiokthth
    public void declineRequest() {
        this.booking_status = ReservationStatus.DECLINED;
        notifyTenant(this, "Booking Request Declined", "Your booking request " + this + " has been declined.");
    }

    //Methodos gia akyrwsh aithmatos krathshs (dhladh prin ginei enoikiash, opou o enoikiasths den exei xrewthei tipota akoma)
    public void cancelRequest() {
        this.booking_status = ReservationStatus.DECLINED;
        notifyOwner(this.listing, "Booking Request Cancellation", "Booking request cancellation from " + this.tenant + " for " + this.listing+ " for " + this.check_in + "-" + this.check_out);
    }

    // Methodos gia thn enhmerwsh tou idiokthth se periptwsh aithmatos krathshs
    private void notifyOwner(Listing listing, String Title, String Desc) {
        Owner owner = listing.getOwner();
        MainActivity.SYSTEM.getEmail().send(owner.getEmail(), Title, Desc);
    }

    // Methodos gia enhmerwsh tou enoikiasth se periptwsh apanthshs tou idiokthth
    private void notifyTenant(BookingRequest booking_request, String Title, String Desc) {
        Tenant tenant = booking_request.getTenant();
        MainActivity.SYSTEM.getEmail().send(tenant.getEmail(), Title, Desc);
    }

    // count the days between two given dates
    private int daysBetween(Date start, Date end) {
        return 0;
    }

    // Setters and getters
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