package com.example.readysethome.model;

import com.example.readysethome.MainActivity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

class Booking {
    private int id;
    private Date checkIn;
    private Date checkOut;
    private Tenant tenant;
    private Listing listing;


    private ReservationStatus booking_status;
    private boolean isCancelled = false;

    int apartmentRating=-1;
    public Booking(int id, Date checkIn, Date checkOut, Tenant tenant, Listing listing) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.tenant = tenant;
        this.listing = listing;
    }
    //methodos elegxou na yparxei energh krathsh gia mia xronikh periodo

    public boolean isActive() {
        Date currentDate = new Date(); //today's date
        return currentDate.after(checkIn) && currentDate.before(checkOut);
    }

    public void rateApartment(int rating) {
        if (isStayCompleted()) {
            apartmentRating = rating;
            System.out.println("Apartment rated with " + rating + " stars.");
        } else {
            System.out.println("Cannot rate the apartment before the stay is completed.");
        }
    }

    // Method to check if stay is completed
    private boolean isStayCompleted() {
        Date currentDate = new Date();
        return currentDate.after(checkOut);
    }

    public int getApartmentRating() {
        return apartmentRating;
    }

    public boolean isCancelled() {
        return isCancelled;
    }
    public void cancel() {
        if (!isCancelled) {
            isCancelled = true;
            this.booking_status = ReservationStatus.CANCELLED_BY_TENANT;


            double refundAmount = calculateRefundAmount();
            this.tenant.getCreditCard().refund(refundAmount);
            notifyUser(this.tenant, "Booking Canceled", "Your booking has been canceled successfully. A refund of $" + refundAmount + " has been processed.");

            this.listing.getCalendar().setAvailable(this.checkIn, this.checkOut);
        }
    }

    // Calculates the refund amount
    private double calculateRefundAmount() {
        long days_of_stay = daysBetween(this.checkIn, this.checkOut);
        double totalCost =(days_of_stay * this.listing.getPrice());
        double refundPercentage = (daysUntilCheckIn() > 30) ? 0.95 : 1.0;
        return totalCost * refundPercentage;
    }

    public ReservationStatus getBooking_status() {
        return booking_status;
    }
    // Methodos gia thn enhmerwsh
    private void notifyUser(User user, String Title, String Desc) {
        MainActivity.SYSTEM.getEmail().send(user.getEmail(), Title, Desc);
    }

    // count the days between two given dates
    public long daysBetween(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        // System.out.println ("Days: " + diff);
        return diff;
    }

    public long daysUntilCheckIn() {
        Date currentDate = new Date();
        long daysDifference = daysBetween(currentDate, this.checkIn);
        return daysDifference;
    }
// Setters kai getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public User getTenant() {
        return this.tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Listing getListing() {
        return this.listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }
}
