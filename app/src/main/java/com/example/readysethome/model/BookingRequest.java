package com.example.readysethome.model;

import com.example.readysethome.MainActivity;
import com.example.readysethome.dao.Initializer;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.dao.UserDAO;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.memorydao.UserDAOMemory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BookingRequest {
     private static int lastBookingId = 0;
     private int booking_id;
    private Date submission_date;
    private Date check_in;
    private Date check_out;
    private ReservationStatus booking_status;
    private Listing listing;
    private Tenant tenant;

    Initializer init;

    UserDAO usersnull;
    User userDummie;
    public BookingRequest(Listing listing, Date submission_date, Date check_in, Date check_out, Tenant tenant) {
        lastBookingId++;
        this.booking_id = lastBookingId;
        this.listing = listing;
        this.submission_date = submission_date;
        this.check_in = check_in;
        this.check_out = check_out;
        this.tenant = tenant;
        usersnull= new  UserDAOMemory();
        userDummie=usersnull.findByID("t0");
    }

    //Methodos gia thn ypovolh aithmatos
    public boolean submit() {
        // check if tenant has sufficient amount in credit card
        long days_of_stay = daysBetween(this.check_in, this.check_out);
        double upfront = 0.2 * (days_of_stay * this.listing.getPrice());
        if (tenant.getCreditCard().getBalance() < upfront) return false;
        this.booking_status = ReservationStatus.PENDING;
        Owner owner = listing.getOwner();
        notifyUser(owner, "Booking Request", "Booking request from " + this.tenant + " for " + this.listing+ " for " + this.check_in + "-" + this.check_out);
        owner.addToPending(this);
        //tenant.getBookingRequests().add(this);
        return true;
    }

    //Methodos gia akyrwsh aithmatos krathshs (dhladh prin ginei enoikiash, opou o enoikiasths den exei xrewthei tipota akoma)
    public void cancelRequest() {
        this.booking_status = ReservationStatus.DECLINED;
        Owner owner = listing.getOwner();
        notifyUser(owner, "Booking Request Cancellation", "Booking request cancellation from " + this.tenant + " for " + this.listing+ " for " + this.check_in + "-" + this.check_out);
        owner.removeFromPending(this);
        tenant.getBookingRequests().remove(this);
    }

    // Methodos gia confirmation aithmatos
    public boolean confirm() {
        // upfront payment
        long days_of_stay = daysBetween(this.check_in, this.check_out);
        double upfront = 0.2 * (days_of_stay * this.listing.getPrice());
        // if something goes wrong with the payment
        if (!this.tenant.getCreditCard().makePayment(upfront)) {
            notifyUser(this.tenant, "Your booking request has been declined", "Admin: Insufficient funds / negative amount");
            notifyUser(this.listing.getOwner(), "This booking request has been declined", "Admin: Insufficient funds / negative amount");
            return false;
        }
        // owner payment
        Owner owner = this.listing.getOwner();
        owner.getCreditCard().refund(upfront);
        this.booking_status = ReservationStatus.CONFIRMED;
        // notify tenant, owner
        notifyUser(this.tenant, "Booking Request Confirmed", "Your booking request " + this + " has been confirmed. An amount of " + upfront
                + " has been removed from your credit card");
        notifyUser(owner, "Booking Request Confirmed", this.tenant + "'s booking request " + this + " has been confirmed. An amount of " + upfront
                + " has been added to your credit card");
        // update apartment availability
        this.listing.getCalendar().setUnavailable(this.check_in, this.check_out);
        // create new booking
        Booking new_booking = new Booking(this);
        this.tenant.addBooking(new_booking);
        this.tenant.getBookingRequests().remove(this);
        // Used for the statistics
        listing.calculateMonthlyIncome(check_in, days_of_stay * listing.getPrice());
        return true;
    }

    // Methodos gia decline aithmatos krathshs apo ton idiokthth
    public void declineRequest() {
        this.booking_status = ReservationStatus.DECLINED;
        notifyUser(this.tenant, "Booking Request Declined", "Your booking request " + this + " has been declined.");
        this.tenant.getBookingRequests().remove(this);
    }

    // Methodos gia thn enhmerwsh
    private void notifyUser(User user, String Title, String Desc) {
        if(user==null){
            user=userDummie;
        }

        user.getEmail().send(user.getEmail(),Title,Desc);
    }

    // count the days between two given dates
    private long daysBetween(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        // System.out.println ("Days: " + diff);
        return diff;
    }

    // Setters and getters
    public int getBooking_id() {
        return booking_id;
    }
    public void setBooking_id(int booking_id) {this.booking_id = booking_id;}

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